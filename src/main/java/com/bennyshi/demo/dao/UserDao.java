package com.bennyshi.demo.dao;

import com.bennyshi.demo.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * t_user 操作：演示两种方式
 * <p>第一种是基于mybatis3.x版本后提供的注解方式<p/>
 * <p>第二种是早期写法，将SQL写在 XML 中<p/>
 *
 */
@Repository
//这个注解代表这是一个mybatis的操作数据库的类
@Mapper
public interface UserDao {

    /**
     * 用户注册，插入用户信息
     *
     * @param username 用户名; password 密码（经过加密）;
     * @return 插入状态
     */
    @Insert("INSERT INTO user(user_name, password, class_id," +
            "create_time, update_time, image) VALUE ( #{username}, #{password}, #{classId},  #{create_time}, #{update_time}, #{image}) ")
    Boolean  insertByUser(@Param("username") String username, @Param("password") String password, @Param("classId") String classId,
                     @Param("create_time")Date createTime, @Param("update_time") Date updateTime, @Param("image") String image);

    @Delete({"delete from user where id=#{id}"})
    Boolean deleteById(int id);

    /**
     * 用户修改密码
     *
     * @param id; password 密码
     * @return 修改状态
     */

    @Update("UPDATE Person SET password = #{password} WHERE id = #{id} ")
    Boolean UpdataById(int id, String password);


    /**
     * 根据用户名查询用户结果集
     *
     * @param username 用户名
     * @return 查询结果
     */
    @Select("SELECT * FROM user WHERE user_name = #{username}")
    List<User> findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    List<User> findByID(@Param("id") int id);

}
