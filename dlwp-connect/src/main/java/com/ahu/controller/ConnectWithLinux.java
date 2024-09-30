package com.ahu.controller;

import com.ahu.pojo.dto.Response;
import com.ahu.service.impl.ConnectWithLinuxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connect")
public class ConnectWithLinux {
    @Autowired
    ConnectWithLinuxServiceImpl service;
    @GetMapping("/LoginLinux")
    public Response<String> LoginLinux(@RequestParam String hostName , @RequestParam String userName, @RequestParam String password){
        String result = service.LoginLinux(hostName,userName,password);
        return new Response<String>().success("ok");
    }
}
