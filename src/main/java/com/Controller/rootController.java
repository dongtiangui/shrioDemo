package com.Controller;

import com.EntiryPackage.userEntiry;
import com.Service.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class rootController {

    private final LoginUser loginUser;

    @Autowired
    public rootController(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @RequestMapping("/login")
    public String root(@RequestParam("name") String name,@RequestParam("pass") String pass){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("name = " + name);
        userEntiry  userEntiry = new userEntiry();
        userEntiry.setName(name);
        userEntiry.setPass(pass);
        boolean inuset = loginUser.inuset(userEntiry);
        System.out.println("inuset = " + inuset);
        if (!subject.isAuthenticated()){
            UsernamePasswordToken Token = new UsernamePasswordToken(name,pass);
            System.out.println("Token = " + Token);
            try {
                subject.login(Token);
                return "forward:/login.jsp";
            }catch (Exception e){
                e.printStackTrace();
                return "forward:/error.jsp";
            }
        }
        return "forward:/login.jsp";
    }
}
