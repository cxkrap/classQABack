package com.example.classqa.blImpl.user;

import com.example.classqa.bl.user.Userbl;
import com.example.classqa.data.user.UserMapper;
import com.example.classqa.vo.ResponseVO;
import com.example.classqa.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.classqa.po.User;
@Service
public class UserImpl implements Userbl {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseVO register(){
        try {
            User user = userMapper.register();
            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            return ResponseVO.buildSuccess(userVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }
    @Override
    public ResponseVO signUP(int id){
        try {
            User user = userMapper.getAccountById(id);
            return ResponseVO.buildSuccess(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("用户不存在");
        }
    }
}
