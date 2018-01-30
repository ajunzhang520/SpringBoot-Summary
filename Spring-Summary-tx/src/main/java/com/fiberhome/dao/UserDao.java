package com.fiberhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fiberhome.entity.User;

/**
 * Description:
 * @author sjZhang
 * @date 2018年1月29日下午3:30:02
 */
@Mapper
public interface UserDao {
	/**
	 * 对应XML的getUser标签
	 * @return
	 */
	List<User> getUser();
	
	/**
	 * 用SELECT标签直接写SQL
	 * @param id
	 * @return
	 */
	@Select("SELECT T.id,T.address,T.name,T.createUserId,T.port,T.state FROM [dbo].[USER1] T WHERE T.id = #{id};")
	User getUserById(@Param("id") Long id);
	
	Long insert(User user);
}
