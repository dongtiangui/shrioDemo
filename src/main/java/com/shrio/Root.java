package com.shrio;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;

public class Root {
    @Test
    public void test(){

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiroRealm.ini");
        //创建管理器
        SecurityManager securityManager = factory.getInstance();
        //绑定管理器
        SecurityUtils.setSecurityManager(securityManager);
        //登陆主体
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("root","123456");

        try {
            subject.login(token);
        }catch (Exception e){
           e.printStackTrace();
        }
        System.out.println("subject = " + subject.isAuthenticated());
    }

    public static void main(String[] args) {
        String name = "董天贵";
        Sha1Hash sha1Hash = new Sha1Hash(name);
        System.out.println("sha1Hash = " + sha1Hash.getBytes());
    }
}
