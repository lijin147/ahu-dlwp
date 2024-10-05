package com.ahu.controller;

import com.ahu.constant.JwtClaimsConstant;
import com.ahu.pojo.dto.Response;
import com.ahu.pojo.dto.UserLoginDTO;
import com.ahu.pojo.entity.JwtProperties;
import com.ahu.pojo.entity.User;
import com.ahu.service.Impl.LoginServiceImpl;
import com.ahu.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class Login {
    @Autowired
    private JwtProperties properties;
    @Autowired
    private LoginServiceImpl loginService;
    @PostMapping("/login")
    public Response<UserLoginDTO> Login(User user){
        Long id = loginService.Login(user);
        UserLoginDTO userLoginDTO = null;
        if(id == null){
            return new Response<UserLoginDTO>().error(401,userLoginDTO);
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,id);
        String token = JWTUtil.createJWT(properties.getSecretKey(), properties.getTtl(),claims);
        userLoginDTO = new UserLoginDTO();
        userLoginDTO.setId(id);
        userLoginDTO.setToken(token);
        return new Response<UserLoginDTO>().success(userLoginDTO);
    }
}
