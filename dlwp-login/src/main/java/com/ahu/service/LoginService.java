package com.ahu.service;

import com.ahu.pojo.entity.User;
import com.ahu.service.Impl.LoginServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImpl {

    @Override
    public Long Login(User user) {
        //从数据中查找，如果找不到就直接返回null
        return 1L;
    }
}
