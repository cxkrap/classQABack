package com.example.classqa.blImpl.management.manage;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.classqa.bl.management.ManageService;
import com.example.classqa.data.management.ManageMapper;
import com.example.classqa.vo.ResponseVO;
import com.example.classqa.vo.UserForm;
import com.example.classqa.po.User;

import java.util.List;

public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String ACCOUNT_NOT_EXIST = "账号不存在";
    private final static String NO_ACCOUNT_EXIST = "没有可查询用户";
    @Override
    //TODO:增加用户
    public ResponseVO addMember(UserForm userForm){
        try{
            String username = userForm.getUsername();
            String password = userForm.getPassword();
            manageMapper.insertUser(username,password);
            return ResponseVO.buildSuccess();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
    }

    @Override
    //TODO:删除用户
    public ResponseVO deleteMember(String username){
        try{
            manageMapper.deleteUser(username);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            return ResponseVO.buildFailure(ACCOUNT_NOT_EXIST);
        }
    }

    @Override
    //TODO:查询用户
    public ResponseVO findMember(String username){
        try{
            User user = manageMapper.selectUserByUsername(username);
            if(user!=null)return ResponseVO.buildSuccess(user);
            else return ResponseVO.buildFailure(ACCOUNT_NOT_EXIST);
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    //TODO:查询所有用户
    public ResponseVO findAllMember(){
        try{
            List<User>userList = manageMapper.selectAllUser();
            if(userList.size()!=0)return ResponseVO.buildSuccess(userList);
            else return ResponseVO.buildFailure(NO_ACCOUNT_EXIST);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    //TODO:修改成员信息
    public ResponseVO changeMemberInfo(int id,UserForm userForm){
        try{
            String username = userForm.getUsername();
            String password = userForm.getPassword();
            manageMapper.updateUserInfo(id,username,password);
            User newUser = manageMapper.selectUserByID(id);
            return ResponseVO.buildSuccess(newUser);
        }catch (Exception e){
            return ResponseVO.buildFailure(ACCOUNT_NOT_EXIST);
        }
    }
}
