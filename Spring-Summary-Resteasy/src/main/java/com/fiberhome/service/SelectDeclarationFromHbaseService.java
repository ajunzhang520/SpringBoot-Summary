package com.fiberhome.service;

import com.fiberhome.core.QueryFilter;
import com.fiberhome.entity.ResponVo;

/**
 * @Description:定义从Hbase查询申报单接口
 * @author zsj
 * @date 2018年2月28日 下午4:54:04
 */
public interface SelectDeclarationFromHbaseService {

	/**
	 * 第一页接口。
	 * 
	 * @param tableName
	 * @param pageSize
	 * @return
	 */
	public ResponVo selectFirstPage(QueryFilter filter, String tableName, int pageSize);

	/**
	 * 下一页接口，lastrowKey为当前页的最后RowKey
	 * 
	 * @param tableName
	 * @param pageSize
	 * @param lastrowKey
	 * @return
	 */
	public ResponVo selectNextPage(QueryFilter filter, String tableName, int pageSize, String lastrowKey);

	/**
	 * 上一页接口，startrowKey为当前页的起始RowKey
	 * 
	 * @param tableName
	 * @param pageSize
	 * @param startrowKey
	 * @return
	 */
	public ResponVo selectFrontPage(QueryFilter filter, String tableName, int pageSize, String startrowKey);

	/**
	 * 点击申报单进入详情页面
	 * 
	 * @param rowkey
	 * @return
	 */
	public ResponVo getDeclarationDetailsByRowKey(String tableName, String rowkey);

}
