package com.example.classqa.blImpl.user;

import com.example.classqa.bl.user.AccountService;
import com.example.classqa.data.user.AccountMapper;
import com.example.classqa.po.User;
import com.example.classqa.vo.UserForm;
import com.example.classqa.vo.ResponseVO;
import com.example.classqa.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Service
public class AccountServiceImpl implements AccountService,AccountServiceForBl {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserForm userForm) {
        try {
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword());
        } catch (Exception e) {
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getUsername());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return new UserVO(user);
    }


    @Override
    public boolean isExist(int userId){
        try {
            User user=accountMapper.getAccountById(userId);
            if(user==null)return false;
            else return true;
        }catch (Exception e){
            return false;
        }
    }
}
