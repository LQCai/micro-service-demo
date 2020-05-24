package com.luoqincai.service;

import com.luoqincai.client.AuthServiceClient;
import com.luoqincai.dao.UserDao;
import com.luoqincai.dto.LoginDTO;
import com.luoqincai.dto.RespDTO;
import com.luoqincai.entity.JWT;
import com.luoqincai.entity.User;
import com.luoqincai.exception.CommonException;
import com.luoqincai.exception.ErrorCode;
import com.luoqincai.util.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by fangzhipeng on 2017/7/10.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Qualifier("authServiceHystrix")
    @Autowired
    AuthServiceClient authServiceClient;

    public User createUser(User user){
      return  userDao.save(user);
    }

    public User getUserInfo(String username){
        return userDao.findByUsername(username);
    }
    public RespDTO login(String username , String password){
       User user= userDao.findByUsername(username);
       if(null==user){
           throw new CommonException(ErrorCode.USER_NOT_FOUND);
       }
       if(!BPwdEncoderUtils.matches(password,user.getPassword())){
           throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
       }

        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", username, password);
        // 获得用户菜单
        if(null==jwt){
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        return RespDTO.onSuc(loginDTO);
    }
}
