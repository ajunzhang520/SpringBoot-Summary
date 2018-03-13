package com.fiberhome.utils;

/**
 * @Description:老吴推荐分页实现测试 HBase的常量值
 * @author zsj
 * @date 2018年3月1日 上午11:18:34
 */
public class CpConstants {
	public static String ROWKEY_FIRST = "01161219121200xxxx681550";
	public static String HBASE_TABLE_PROP_ROWKEY = "ROWKEY";
	public static String HBASE_TABLE_PROP_COLUMNFAMILY = "CF";

	/**
	 * 定义_DECLARATION_ROWKEY_SUBSTR_ARR数组用来查询表T_EDI_DECLARATION中的ROWKEY某段subString的值
	 * INANDOUTTYPE  ---- 進出口類型 位于11-12位  
	 * DECLARATIONFILETYPE --- 文件类型即申報單類別 位于 9-10 位
	 * DECLARATIONNUM --- 申報單編號 位于 15-24 位
	 * 
	 * */
	public static final String[] _DECLARATION_ROWKEY_SUBSTR_ARR = { "INANDOUTTYPE", "DECLARATIONFILETYPE",
			"DECLARATIONNUM" };

	/**
	 * 判断一个Str的元素是否在一个StrArr数组中。
	 * @param str
	 * @param strArr
	 * @return
	 */
	public static boolean isElementInArray(final String str,final String[] strArr){
		boolean flag = false;
		for(int i=0;i<strArr.length;i++){
			if(str.equals(strArr[i])){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
