package com.example.classqa.bl.management;

import com.example.classqa.vo.ResponseVO;
import com.example.classqa.vo.UserForm;

public interface ManageService {

    /**
     * 添加影院用户
     * @param userForm
     * @return
     */
    ResponseVO addMember(UserForm userForm);

    /**
     * 根据用户名删除用户
     * @param username
     * @return
     */
    ResponseVO deleteMember(String username);

    /**
     * 根据用户名查找用户,buildSuccess时返回一个UserVO
     * @param username
     * @return
     */
    ResponseVO findMember(String username);

    /**
     * 查找所有用户,buildSuccess时返回List<UserVO>
     * @return
     */
    ResponseVO findAllMember();

    /**
     * 修改成员信息，userForm是要修改的信息,buildSuccess时返回一个UserVO
     * @param id
     * @param userForm
     * @return
     */
    ResponseVO changeMemberInfo(int id,UserForm userForm);
}
