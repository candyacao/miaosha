package com.neo.web;

import com.neo.enums.UserSexEnum;
import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(Long id) {
        User user = userService.getOne(id);
        return user;
    }

    /**
     * 登录校验
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (null == username || null == password) {
            return "login";
        }
        // 根据用户名去数据查询盐值及存储密码，判断用户输入的"密码+盐"生成的MD5编码是否与数据库存储的相同
        // 相同，则通过验证
        User user = userService.getOne(username);
        if(user == null){
            return "login";
        }
        String halt = user.getSalt();
        String md5info = user.getHashPassword();
        String realPassword = MD5Util.md5(password+halt);
        if (!md5info.equals(realPassword)) {
            return "login";
        }
        // 校验通过时，在session里放入一个标识
        // 后续通过session里是否存在该标识来判断用户是否登录
        request.getSession().setAttribute("loginName", username);
        return "welcome";
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    /**
     * 新用户注册
     * @param request
     */
    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String passWd = request.getParameter("password");
        String userSex = request.getParameter("userSex");
        String nickName = request.getParameter("nickName");
        // TODO: 设置验证码
        /*String checkCode = request.getParameter("checkCode");
        System.out.println("request"+request);
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("randCheckCode"));*/

        User user = userService.getOne(username);
        if(user == null){
            return "register";
        }
        // TODO: 如何设计事务回滚
        userService.signUp(username, passWd, UserSexEnum.getSexEnum(Integer.parseInt(userSex)), nickName);
        return "login";
    }
    /**
     * 登录
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "login";
    }
    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}