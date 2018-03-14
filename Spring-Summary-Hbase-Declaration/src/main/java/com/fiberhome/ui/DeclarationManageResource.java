package com.fiberhome.ui;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiberhome.core.QueryFilter;
import com.fiberhome.entity.ResponVo;
import com.fiberhome.service.SelectDeclarationFromHbaseService;
import com.fiberhome.utils.QueryUtils;

/**
 * @Description:申报单管理Resource层
 * @author zsj
 * @date 2018年2月28日 上午10:35:37
 */
@RestController
@RequestMapping("/goodsrisk/declaration")
public class DeclarationManageResource {
	
	@Autowired
	public HttpServletRequest request;

	@Autowired
	public SelectDeclarationFromHbaseService declarationService;

	/**
	 * 第一页接口
	 * 
	 * 路径参数为pageSize,查询参数用Q=DECLARATIONNUM_S_LK进行模糊查询。
	 * http://localhost:8080/goodsrisk/declaration/first/5?Q=DECLARATIONNUM_S_LK=681550
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/first/{pageSize}",method=RequestMethod.GET)
	public ResponVo selectFirstPage(@PathVariable("pageSize") int pageSize) {
		String tableName = "T_EDI_DECLARATION";
		ResponVo result = declarationService.selectFirstPage(this.getQueryFilter(),tableName, pageSize);
		return result;
	}

	/**
	 * 下一页接口
	 * 
	 * @param pageSize
	 * @param lastrowkey
	 * @return
	 */
	@RequestMapping(value = "/next/{pageSize}/{lastrowkey}",method=RequestMethod.GET)
	public ResponVo selectAllByPage(@PathVariable("pageSize") int pageSize,
			@PathVariable("lastrowkey") String lastrowkey) {
		String tableName = "T_EDI_DECLARATION";
		ResponVo result = declarationService.selectNextPage(this.getQueryFilter(),tableName, pageSize, lastrowkey);
		return result;
	}

	/**
	 * 上一页接口
	 * 
	 * @param pageSize
	 * @param startrowkey
	 * @return
	 * @throws BusinessAccessException
	 */
	@RequestMapping(value = "/front/{pageSize}/{startrowkey}",method=RequestMethod.GET)
	public ResponVo selectFrontPage(@PathVariable("pageSize") int pageSize,
			@PathVariable("startrowkey") String startrowkey) throws Exception {
		String tableName = "T_EDI_DECLARATION";
		ResponVo result = declarationService.selectFrontPage(this.getQueryFilter(),tableName, pageSize, startrowkey);
		return result;
	}
	
	/**
	 * 点击申报单进入详情页面
	 * @param rowkey
	 * @return
	 * @throws BusinessAccessException
	 */
	@RequestMapping(value = "/details/{rowkey}",method=RequestMethod.GET)
	public ResponVo getDeclarationDetailsByRowKey(@PathVariable("rowkey") String rowkey)
			throws Exception {
		String tableName = "T_EDI_GOODS";
		ResponVo result = declarationService.getDeclarationDetailsByRowKey(tableName, rowkey);
		return result;
	}
	
	protected QueryFilter getQueryFilter() {
	    return QueryUtils.parseMultiQuery(request.getParameterMap());
	  }

}
