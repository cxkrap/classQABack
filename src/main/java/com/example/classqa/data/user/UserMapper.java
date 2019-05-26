package com.example.classqa.data.user;

import com.example.classqa.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    /**
     * 根据用户id查找账号
     * @param id
     * @return
     */
    User getAccountById(@Param("id") int id);

    /**
     * 生成一个新的用户
     * @return
     */
    User register();
}
