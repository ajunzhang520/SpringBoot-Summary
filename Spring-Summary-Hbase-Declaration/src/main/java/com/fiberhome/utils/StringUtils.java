/**
 * 
 */
package com.fiberhome.utils;

import static org.springframework.util.StringUtils.cleanPath;
import static org.springframework.util.StringUtils.commaDelimitedListToSet;
import static org.springframework.util.StringUtils.isEmpty;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * @author chenb
 *
 */
public class StringUtils {
  private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);



  /**
   * 判断字符串是否是合法的java标识符
   * 
   * @param s 待判断的字符串
   */
  public static boolean isJavaIdentifier(String s) {
    // 如果字符串为空或者长度为0，返回false
    if (s == null || s.length() == 0) {
      return false;
    }
    // 满足首字符要求
    if (!Character.isJavaIdentifierStart(s.charAt(0))) {
      return false;
    }
    // 字符串中每一个字符都必须是java标识符的一部分
    for (int i = 1; i < s.length(); i++) {
      if (!Character.isJavaIdentifierPart(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }


  /**
   * java属性转换成数据字段，大写字前加下划线
   * 
   * convertPropertyNameToUnderscoreName -> convert_Property_Name_To_Underscore_Name
   * 
   * @param name
   * @return 返回转化后的变量
   */
  public static String convertPropertyNameToUnderscoreName(String name) {
    // 如果字符串为空或者长度为0，返回错误
    if (!isJavaIdentifier(name)) {
      throw new IllegalArgumentException("Illegal property name -> " + name);
    }
    return name.replaceAll("[A-Z]", "\\_$0");
  }

  public static List<Object> convertList(String type, String paramValues) {
    List<Object> list = new ArrayList<>(10);
    for (String paramValue : tokenizeToStringArray(paramValues, ",")) {
      list.add(convertObject(type, paramValue));
    }
    return list;
  }

  public static Object convertObject(String type, String paramValue) {
    if (isEmpty(paramValue)) {
      return null;
    }
    Object value = null;
    try {
      switch (type) {
        case "S":
          return paramValue;
        case "Z":
          return Boolean.valueOf(paramValue);
        case "B":
          return Byte.valueOf(paramValue);
        case "I":
          return Integer.valueOf(paramValue);
        case "L":
          return Long.valueOf(paramValue);
        case "F":
          return Float.valueOf(paramValue);
        case "J":
          return Double.valueOf(paramValue);
        case "D":
          return DateUtils.parseDate(paramValue, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");
        case "T":
          return DateUtils.parseDate(paramValue, "HH:mm:ss");
        default:
          logger.warn("the data type '{}' is not right for the query filed type[SZBILFJDT]", type);
          return null;
      }
    } catch (Exception ex) {
      logger.error("the data value '{}' is not right for the type {}", paramValue, type);
    }
    return value;
  }

  public static String processPath(String path) {
    boolean slash = false;
    for (int i = 0; i < path.length(); i++) {
      if (path.charAt(i) == '/') {
        slash = true;
      } else if (path.charAt(i) > ' ' && path.charAt(i) != 127) {
        if (i == 0 || (i == 1 && slash)) {
          return path;
        }
        path = slash ? "/" + path.substring(i) : path.substring(i);
        return path;
      }
    }
    return (slash ? "/" : "");
  }

  public static boolean isInvalidPath(String path) {
    logger.trace("Applying \"invalid path\" checks to path: {}," + path);
    if (path.contains("WEB-INF") || path.contains("META-INF")) {
      logger.trace("Path contains \"WEB-INF\" or \"META-INF\".");
      return true;
    }
    if (path.contains(":/")) {
      String relativePath = (path.charAt(0) == '/' ? path.substring(1) : path);
      if (ResourceUtils.isUrl(relativePath) || relativePath.startsWith("url:")) {
        logger.trace("Path represents URL or has \"url:\" prefix.");
        return true;
      }
    }
    if (path.contains("../")) {
      path = cleanPath(path);
      if (path.contains("../")) {
        logger.trace("Path contains \"../\" after call to StringUtils#cleanPath.");
        return true;
      }
    }
    return false;
  }


  /**
   * 逗号分隔字符串转化成对应的id列表
   * 
   * @param commas
   * @return
   */
  public static Long[] toLongIds(String commas) {
    Set<String> setIds = commaDelimitedListToSet(commas);
    return ArrayUtils.toObject(setIds.stream().mapToLong(Long::valueOf).toArray());
  }

  /*
   * public static void main(String[] args) {
   * System.out.println(convertPropertyNameToUnderscoreName("convertPropertyNameToUnderscoreName"));
   * }
   */
}
