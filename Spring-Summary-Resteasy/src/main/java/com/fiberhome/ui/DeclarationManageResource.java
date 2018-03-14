package com.fiberhome.ui;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fiberhome.core.QueryFilter;
import com.fiberhome.entity.ResponVo;
import com.fiberhome.service.SelectDeclarationFromHbaseService;
import com.fiberhome.utils.QueryUtils;

/**
 * @Description:申报单管理Resource层
 * @author zsj
 * @date 2018年2月28日 上午10:35:37
 */
@Component
@Path("/goodsrisk/declaration")
@Produces("application/json")
@Consumes("application/json")
public class DeclarationManageResource{

	@Autowired
	public HttpServletRequest request;

	@Context
	protected UriInfo uriInfo;

	@Autowired
	public SelectDeclarationFromHbaseService declarationService;

	/**
	 * 第一页接口
	 * 
	 * 路径参数为pageSize,查询参数用Q=DECLARATIONNUM_S_LK进行模糊查询。
	 * http://localhost:8080/goodsrisk/declaration/first/5?Q=DECLARATIONNUM_S_LK
	 * =681550
	 * 
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Path(value = "/first/{pageSize}")
	public ResponVo selectFirstPage(@PathParam("pageSize") int pageSize) throws Exception {
		String tableName = "T_EDI_DECLARATION";
		ResponVo result = declarationService.selectFirstPage(this.getQueryFilter(), tableName, pageSize);
		return result;
	}

	/**
	 * 下一页接口
	 * 
	 * @param pageSize
	 * @param lastrowkey
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Path(value = "/next/{pageSize}/{lastrowkey}")
	public ResponVo selectAllByPage(@PathParam("pageSize") int pageSize, @PathParam("lastrowkey") String lastrowkey) throws Exception {
		String tableName = "T_EDI_DECLARATION";
		ResponVo result = declarationService.selectNextPage(this.getQueryFilter(), tableName, pageSize, lastrowkey);
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
	@GET
	@Path(value = "/front/{pageSize}/{startrowkey}")
	public ResponVo selectFrontPage(@PathParam("pageSize") int pageSize, @PathParam("startrowkey") String startrowkey)
			throws Exception {
		String tableName = "T_EDI_DECLARATION";
		ResponVo result = declarationService.selectFrontPage(this.getQueryFilter(), tableName, pageSize, startrowkey);
		return result;
	}

	/**
	 * 点击申报单进入详情页面
	 * 
	 * @param rowkey
	 * @return
	 * @throws BusinessAccessException
	 */
	@GET
	@Path(value = "/details/{rowkey}")
	public ResponVo getDeclarationDetailsByRowKey(@PathParam("rowkey") String rowkey) throws Exception {
		String tableName = "T_EDI_GOODS";
		ResponVo result = declarationService.getDeclarationDetailsByRowKey(tableName, rowkey);
		return result;
	}

	/**
	 * 获取动态查询参数列表
	 * 
	 * @return
	 * @throws Exception 
	 */
	protected QueryFilter getQueryFilter() throws Exception {
		return QueryUtils.parseMultiQuery(uriInfo.getQueryParameters());
	}

}
