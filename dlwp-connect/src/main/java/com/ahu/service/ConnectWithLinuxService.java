package com.ahu.service;

import com.ahu.service.impl.ConnectWithLinuxServiceImpl;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ConnectWithLinuxService implements ConnectWithLinuxServiceImpl {

    @Override
    public String LoginLinux(String hostName, String userName, String password) {
        Session session = null;
        ChannelExec exec = null;
        StringBuilder output = new StringBuilder();
        try {
            //实现ssh通信的包
            JSch jSch = new JSch();
            //获得session
            session = jSch.getSession(userName, hostName);
            //输入密码
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no"); // 忽略主机密钥检查
            //进行连接
            session.connect();
            //连接成功以后创建执行channel
            exec = (ChannelExec) session.openChannel("exec");
            //想要执行的命令
            exec.setCommand("ls");
            //结果发送到输入流中
            InputStream inputStream = exec.getInputStream();
            //开始执行
            exec.connect();
            //开始从输入流中读取数据
            byte[] buf = new byte[1024];
            while (true) {
                int i = inputStream.read(buf, 0, 1024);
                //判断是否还有数据
                if (i < 0) break;
                output.append(new String(buf, 0, i));
                //判断数据是否全部都去完成，读取完成exec这个channel会被关闭
                if (exec.isClosed()) break;
            }
            System.out.println(new String(output));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null)
                session.disconnect();
            if(exec != null)
                exec.disconnect();
        }
        return null;
    }
}
