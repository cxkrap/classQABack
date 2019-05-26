package com.example.classqa.bl.user;

import com.example.classqa.vo.ResponseVO;
public interface Userbl {

    /**
     * 对新机适配的用户，在数据库里添加对象,buildSuccess时返回一个UserVO
     * @return
     */
    ResponseVO register();

    /**
     * @param id
     * 对已经适配的用户，在数据库里查找对象,buildSuccess时返回一个UserVO
     * @return
     */
    ResponseVO signUP(int id);
}
