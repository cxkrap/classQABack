package com.example.classqa.data.management;

import com.example.classqa.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ManageMapper {
    /**
     * 根据用户名和密码添加新用户
     * @param username
     * @param password
     * @return
     */
    int insertUser(@Param("username") String username,@Param("password") String password);

    /**
     * 根据用户名删除用户
     * @param userName
     * @return
     */
    int deleteUser (@Param("username") String userName);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User selectUserByUsername(@Param("username")String username);

    /**
     * 根据ID查找用户
     * @param id
     * @return
     */
    User selectUserByID(@Param("id")int id);

    /**
     * 查找所有用户
     * @return
     */
    List<User> selectAllUser();

    /**
     * 根据ID查找用户并修改其用户名和密码
     * @param id
     * @param username
     * @param password
     */
    void updateUserInfo(@Param("id")int id,@Param("username") String username,@Param("password") String password);
}
