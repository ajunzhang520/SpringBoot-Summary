/**
 * 
 */
package com.fiberhome.utils;

import static com.fiberhome.utils.StringUtils.convertList;
import static com.fiberhome.utils.StringUtils.convertObject;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fiberhome.core.Command;
import com.fiberhome.core.Op;
import com.fiberhome.core.QueryFilter;
import com.fiberhome.entity.Pair;
import com.fiberhome.entity.Pair.Q;

/**
 * @author chenb
 *
 */
public class QueryUtils {
	private static final Logger logger = LoggerFactory.getLogger(QueryUtils.class);

//	public static Map<String, String> toSingleValueMap(Map<String, String[]> query) {
//		Map<String, String> map = new HashMap<>(query.size());
//		query.forEach((key, value) -> {
//			map.put(key, query.get(key));
//		});
//		return map;
//	}

	public static QueryFilter parseMultiQuery(Map<String, String[]> query) {
		QueryFilter queryFilter = new QueryFilter();

		query.forEach((key, value) -> {
			if ("Q".equals(key)) {
				for (String qq : value) {
					Pair p = Q.valueOf(qq);
					queryFilter.addCommand(parseCommand(p.getKey(), p.getValue()));
				}
			} else {
				queryFilter.addParams(key, StringUtils.trimWhitespace(value[0]));
			}
		});

		return queryFilter;
	}

	static Command parseCommand(String key, String value) {
		String[] fieldInfo = StringUtils.tokenizeToStringArray(key, "_");
		Object convertValue = null;
		if (fieldInfo.length == 3 || fieldInfo.length == 2) {
			String name = fieldInfo[0];
			String type = fieldInfo.length == 3 ? fieldInfo[1] : "S";
			Op op = Op.toOp(fieldInfo[fieldInfo.length - 1]);
			if (op.needValue()) {
				convertValue = op.isMultiple() ? convertList(type, value) : convertObject(type, value);
				if (ObjectUtils.isEmpty(convertValue)) {
					return null;
				}
				return new Command(name, op, convertValue);
			} else {
				return new Command(name, op);
			}
		} else {
			logger.error("Query param name [{}] is not right format[field(_T)?_OP].", key);
			return null;
		}
	}
}
