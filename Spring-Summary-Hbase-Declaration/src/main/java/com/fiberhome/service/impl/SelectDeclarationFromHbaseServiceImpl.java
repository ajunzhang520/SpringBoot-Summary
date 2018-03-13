package com.fiberhome.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiberhome.core.Command;
import com.fiberhome.core.QueryFilter;
import com.fiberhome.entity.ResponVo;
import com.fiberhome.service.SelectDeclarationFromHbaseService;
import com.fiberhome.utils.CpConstants;

/**
 * @Description:从Hbase查询申报单service实现类
 * @author zsj
 * @date 2018年2月28日 下午4:54:52
 */
@Service
public class SelectDeclarationFromHbaseServiceImpl implements SelectDeclarationFromHbaseService {

	private Logger log = LoggerFactory.getLogger(SelectDeclarationFromHbaseServiceImpl.class);

	@Autowired
	private Configuration conf;

	/**
	 * 查第一页带查询条件。
	 */
	@Override
	public ResponVo selectFirstPage(QueryFilter filter, String tableName, int pageSize) {
		if (StringUtils.isBlank(tableName) || StringUtils.isBlank(pageSize + "")) {
			log.error("===Parameters tableName|pageSize|rowKey should not be blank,Please check!===");
			return ResponVo.fail().add(Collections.emptyList());
		}
		Connection conn = null;
		HTable table = null;
		ResultScanner scanner = null;
		Scan scan = null;
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();// 返回的结果
		try {
			conn = ConnectionFactory.createConnection(conf);
			table = (HTable) conn.getTable(TableName.valueOf(tableName));
			scan = new Scan();
			FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

			/** 添加查询条件过滤 **/
			SingleColumnValueFilter scvf = null;
			if (filter.getCommands().size() > 0) {
				for (Command command : filter.getCommands()) {
					String field = command.getField().replaceAll("_", "");// 字段field名称
					String value = command.getValue().toString();// value值

					// 如果是要正则匹配RowKey的情况
					if (CpConstants.isElementInArray(field.trim(), CpConstants._DECLARATION_ROWKEY_SUBSTR_ARR)) {
						String regix = regixStringMatch(field, value);
						Filter rowKeyFilter = new RowFilter(CompareFilter.CompareOp.EQUAL,
								new RegexStringComparator(regix));

						filterList.addFilter(rowKeyFilter);
						continue;// 结束这一次循环,不执行如下SingleColumnValueFilter
					}
					// 对column进行过滤
					scvf = new SingleColumnValueFilter(Bytes.toBytes("E"), Bytes.toBytes(field), CompareOp.EQUAL,
							new RegexStringComparator(".*" + value + ".*"));
					scvf.setFilterIfMissing(true);
					filterList.addFilter(scvf);
				}
			}

			Filter pageFilter = new PageFilter(pageSize);
			filterList.addFilter(pageFilter);
			scan.setFilter(filterList);
			scanner = table.getScanner(scan);
			for (Result result : scanner.next(pageSize)) {
				lists.add(getRowByResult(result));
			}
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.debug("SelectDeclarationFromHbaseServiceImpl类下面的selectFirstPage有异常。");
			return ResponVo.fail();
		} finally {
			if (null != scanner) {
				try {
					scanner.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (null != conn) {
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return ResponVo.success().add(lists);
	}

	/**
	 * 查下一页，带条件查询下一页。
	 */
	@Override
	public ResponVo selectNextPage(QueryFilter filter, String tableName, int pageSize, String lastrowKey) {
		if (StringUtils.isBlank(tableName) || StringUtils.isBlank(pageSize + "") || StringUtils.isBlank(lastrowKey)) {
			log.error("===Parameters tableName|pageSize|rowKey should not be blank,Please check!===");
			return ResponVo.fail().add(Collections.emptyList());
		}
		Connection conn = null;
		HTable table = null;
		ResultScanner scanner = null;
		Scan scan = null;
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();// 返回的结果
		try {
			conn = ConnectionFactory.createConnection(conf);
			table = (HTable) conn.getTable(TableName.valueOf(tableName));
			scan = new Scan();
			// scan.setStartRow(lastrowKey.getBytes());// 开始的key
			FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

			/** 添加查询条件过滤 **/
			SingleColumnValueFilter scvf = null;
			if (filter.getCommands().size() > 0) {
				for (Command command : filter.getCommands()) {
					String field = command.getField().replaceAll("_", "");// 字段field名称
					String value = command.getValue().toString();// value值

					// 如果是要正则匹配RowKey的情况
					if (CpConstants.isElementInArray(field.trim(), CpConstants._DECLARATION_ROWKEY_SUBSTR_ARR)) {
						String regix = regixStringMatch(field, value);
						Filter rowKeyFilter = new RowFilter(CompareFilter.CompareOp.EQUAL,
								new RegexStringComparator(regix));

						filterList.addFilter(rowKeyFilter);
						continue;// 结束这一次循环,不执行如下SingleColumnValueFilter
					}

					scvf = new SingleColumnValueFilter(Bytes.toBytes("E"), Bytes.toBytes(field), CompareOp.EQUAL,
							new RegexStringComparator(".*" + value + ".*"));
					scvf.setFilterIfMissing(true);
					filterList.addFilter(scvf);
				}
			}

			Filter pageFilter = new PageFilter(pageSize);
			String ROWKEY_FIRST = selectFirstResultRow(table);// 获取FirstRowKey
			if (!ROWKEY_FIRST.equals(lastrowKey)) {
				Filter rowFilter2 = new RowFilter(CompareFilter.CompareOp.GREATER,
						new BinaryComparator(Bytes.toBytes(lastrowKey)));
				filterList.addFilter(rowFilter2);
			}
			filterList.addFilter(pageFilter);
			scan.setFilter(filterList);
			scanner = table.getScanner(scan);
			for (Result result : scanner.next(pageSize)) {
				lists.add(getRowByResult(result));
			}
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("PageableTestServiceImpl类下面的selectAllByPage有异常。");
			return ResponVo.fail();
		} finally {
			if (null != scanner) {
				try {
					scanner.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (null != conn) {
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return ResponVo.success().add(lists);

	}

	/**
	 * 带条件查询上一页。
	 */
	@Override
	public ResponVo selectFrontPage(QueryFilter filter, String tableName, int pageSize, String startrowKey) {
		if (StringUtils.isBlank(tableName) || StringUtils.isBlank(pageSize + "") || StringUtils.isBlank(startrowKey)) {
			log.error("===Parameters tableName|pageSize|rowKey should not be blank,Please check!===");
			return ResponVo.fail().add(Collections.emptyList());
		}
		Connection conn = null;
		HTable table = null;
		ResultScanner scanner = null;
		Scan scan = null;
		List<Map<String, String>> alllists = new ArrayList<>();// 内存中存储结果
		List<Map<String, String>> subAlllists = null;
		try {
			conn = ConnectionFactory.createConnection(conf);
			table = (HTable) conn.getTable(TableName.valueOf(tableName));
			scan = new Scan();
			FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

			/** 添加查询条件过滤 **/
			SingleColumnValueFilter scvf = null;
			if (filter.getCommands().size() > 0) {
				for (Command command : filter.getCommands()) {
					String field = command.getField().replaceAll("_", "");// 字段field名称
					String value = command.getValue().toString();// value值

					// 如果是要正则匹配RowKey的情况
					if (CpConstants.isElementInArray(field.trim(), CpConstants._DECLARATION_ROWKEY_SUBSTR_ARR)) {
						String regix = regixStringMatch(field, value);
						Filter rowKeyFilter = new RowFilter(CompareFilter.CompareOp.EQUAL,
								new RegexStringComparator(regix));

						filterList.addFilter(rowKeyFilter);
						continue;// 结束这一次循环,不执行如下SingleColumnValueFilter
					}

					scvf = new SingleColumnValueFilter(Bytes.toBytes("E"), Bytes.toBytes(field), CompareOp.EQUAL,
							new RegexStringComparator(".*" + value + ".*"));
					scvf.setFilterIfMissing(true);
					filterList.addFilter(scvf);
				}
			}

			// Filter pageFilter = new PageFilter(pageSize);
			String ROWKEY_FIRST = selectFirstResultRow(table);// 获取FistRowKey
			if (!ROWKEY_FIRST.equals(startrowKey)) {
				Filter rowFilter2 = new RowFilter(CompareFilter.CompareOp.LESS,
						new BinaryComparator(Bytes.toBytes(startrowKey)));
				filterList.addFilter(rowFilter2);
			}
			// filterList.addFilter(pageFilter);
			scan.setFilter(filterList);
			scanner = table.getScanner(scan);
			for (Result result : scanner) {
				alllists.add(getRowByResult(result));
			}

			if (pageSize < alllists.size()) {
				subAlllists = alllists.subList(alllists.size() - pageSize, alllists.size());
			} else {
				subAlllists = alllists;
			}
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("PageableTestServiceImpl类下面的selectAllByPage有异常。");
			return ResponVo.fail();
		} finally {
			if (null != scanner) {
				try {
					scanner.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (null != conn) {
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return ResponVo.success().add(subAlllists);
	}

	/**
	 * 点击某一条申报单进入详情页面，执行的逻辑是根据传入的T_EDI_DECLARATION表中的RowKey值作为前缀，
	 * 在表T_EDI_GOODS中去匹配RowKey。
	 * 
	 */
	@Override
	public ResponVo getDeclarationDetailsByRowKey(String tableName, String rowkey) {
		if (StringUtils.isBlank(tableName) || StringUtils.isBlank(rowkey + "")) {
			log.error("===Parameters tableName|rowKey should not be blank,Please check!===");
			return ResponVo.fail().add(Collections.emptyList());
		}
		Connection conn = null;
		HTable table = null;
		ResultScanner scanner = null;
		Scan scan = null;
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();// 返回的结果
		try {
			conn = ConnectionFactory.createConnection(conf);
			table = (HTable) conn.getTable(TableName.valueOf(tableName));
			scan = new Scan();
			FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

			PrefixFilter prefixFilter = new PrefixFilter(Bytes.toBytes(rowkey.trim()));// 对RowKey用前缀过滤器来匹配
			filterList.addFilter(prefixFilter);
			scan.setFilter(filterList);
			scanner = table.getScanner(scan);
			for (Result result : scanner) {
				lists.add(getRowByResult(result));
			}
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.debug("SelectDeclarationFromHbaseServiceImpl类下面的getDeclarationDetailsByRowKey有异常。");
			return ResponVo.fail();
		} finally {
			if (null != scanner) {
				try {
					scanner.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (null != conn) {
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return ResponVo.success().add(lists);
	}

	/**
	 * 匹配查询条件然后拼接正则表达式,正则表达式设计的逻辑为 INANDOUTTYPE 对应是RowKey中的11-12位,所以前面十位先用占位符代替,
	 * 然后加上11-12位,后面剩下的12位也用占位符代替即可。 DECLARATIONFILETYPE 和 DECLARATIONNUM类似。 
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	private String regixStringMatch(String field, String value) {
		String regix = ".*";
		switch (field) {
		case "INANDOUTTYPE":// INANDOUTTYPE ---- 進出口類型 位于11-12位
			regix = "\\w{10}" + value.trim() + "\\w{12}";// \d{2}(?=[\d\D]{12}$)
			break;
		case "DECLARATIONFILETYPE":// DECLARATIONFILETYPE -- 文件类型即申報單類別 位于 9-10位
			regix = "\\w{8}" + value.trim() + "\\w{14}";
			break;
		case "DECLARATIONNUM":// DECLARATIONNUM --- 申報單編號 位于15-24 位
			regix = "\\w{14}.*" + value.trim();
			break;
		default:
			break;
		}
		return regix;
	}

	/**
	 * 获取同一个rowkey下的记录集合
	 * 
	 * @param result
	 *            结果集
	 * @return
	 */
	private Map<String, String> getRowByResult(Result result) {
		if (result == null) {
			return null;
		}
		Map<String, String> cellMap = new HashMap<String, String>();
		for (Cell cell : result.listCells()) {
			String rowkey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
			String cf = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
			String qf = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
			String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
			cellMap.put(CpConstants.HBASE_TABLE_PROP_ROWKEY, rowkey);
			cellMap.put(CpConstants.HBASE_TABLE_PROP_COLUMNFAMILY, cf);
			cellMap.put(qf, value);
		}
		return cellMap;
	}

	/**
	 * 检索指定表的第一行记录。<br>
	 * 
	 * @param tableName
	 *            表名称(*)。
	 * @param filterList
	 *            过滤器集合，可以为null。
	 * @return
	 */
	private String selectFirstResultRow(HTable table) {
		try {
			Scan scan = new Scan();
			ResultScanner scanner = table.getScanner(scan);
			Iterator<Result> iterator = scanner.iterator();
			int index = 0;
			while (iterator.hasNext()) {
				Result rs = iterator.next();
				if (index == 0) {
					scanner.close();
					String rowkey = null;
					for (Cell cell : rs.listCells()) {
						rowkey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
					}
					return rowkey;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
