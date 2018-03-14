/**
 * 
 */
package com.fiberhome.core;

import static org.springframework.util.ObjectUtils.nullSafeEquals;
import static org.springframework.util.ObjectUtils.nullSafeHashCode;

import java.io.Serializable;

import com.fiberhome.utils.StringUtils;

/**
 * @author chenb
 *
 */
public class Command implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -105312395358927876L;
  final String field;
  final Op operator;
  final Object value;


  public Command(String field, Op operator) {
    this(field, operator, null);
  }

  public Command(String field, Op operator, Object value) {
    this.field = StringUtils.convertPropertyNameToUnderscoreName(field);
    this.operator = operator;
    this.value = escape(value);
  }

  private Object escape(Object value) {
    switch (this.operator) {
      case LK:
      case ST:
      case ED:
        if (value != null) {
          return value.toString().replaceAll("[%_!]", "!$0");
        }
        break;
      default:
        break;
    }
    return value;
  }

  /**
   * @return the field
   */
  public String getField() {
    return field;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return value;
  }
  
  public Object getLike() {
    return "%" + value + "%";
  }
  
  public Object getStart() {
    return value + "%";
  }
  
  public Object getEnd() {
    return "%" + value;
  }

  public String toSql(String filterName, int i) {
    return operator.toSql(filterName, this, i);
  }

  public int hashCode() {
    int hash = field.hashCode() << 16;
    hash += (operator.hashCode() << 8);
    hash += nullSafeHashCode(value);
    return hash;
  }

  public boolean equals(Object anObject) {
    if (this == anObject) {
      return true;
    }
    if (anObject instanceof Command) {
      Command anotherCommand = (Command) anObject;
      return field.equals(anotherCommand.field) && operator.equals(anotherCommand.operator)
          && nullSafeEquals(value, anotherCommand.field);
    }
    return false;
  }
}
