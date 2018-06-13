package com.shrio;
import com.EntiryPackage.userEntiry;
import com.Service.LoginUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    private LoginUser loginUser;

    @Override
    public String getName() {
        return "MyRealm";
    }
    //授权or

    //认证en
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) Token;
        String name =(String) token.getPrincipal();
        userEntiry e = loginUser.getname(name);
        System.out.println("e = " + e.getName()+"\n"+e.getPass());
        try {
            if (e.getName()!=null&&e.getPass()!=null){
                //第一个是用户名、第二个是密码、第三个是realm类名
                //SimpleAuthenticationInfo(e.getName(),e.getPass(),getName());
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(e.getName(),e.getPass(),getName());
                System.out.println("info = " + info);
                return info;
            }
        }catch (Exception e1){
            e1.printStackTrace();
            throw new AuthenticationException();
        }

        return null;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        userEntiry userEntiry = (userEntiry) principalCollection.getPrimaryPrincipal();
        List<String> permissions = new ArrayList<String>();
        userEntiry e = loginUser.getname(userEntiry.getName());
        if ("user".equals(userEntiry.getName())) {
            permissions.add("*:*");
//        }else if (name.equals("dong")){
//            permissions.add("*:*");
//        }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

//    @Test
//    public void Text(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/education_system?characterEncoding=utf8","root","123456");
//            PreparedStatement ps = con.prepareStatement("select * from test where Id=?");
//            ps.setLong(1,2);
//            ResultSet re = ps.executeQuery();
//            while (re.next()){
//
//                System.out.println("re = " + re.getString("name"));
//
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

}
