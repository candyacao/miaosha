package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.enums.UserSexEnum;
import com.github.candyacao.model.ResponseNormal;
import com.github.candyacao.model.User;
import com.github.candyacao.service.UserService;
import com.github.candyacao.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class UserController {

    private static final String SESSION_KEY = "SESSION_KEY";
    @Autowired
    private UserService userService;

    @RequestMapping("/get_user")
    public User getUser(Long id) {
        User user = userService.getOne(id);
        return user;
    }

    /**
     * 登录校验
     *
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public Result<ResponseNormal> login(HttpServletRequest request) {
        userService.signIn(null, null);
        log.error("unexpected");
        ResponseNormal response = new ResponseNormal();
        Result<ResponseNormal> result = Result.build();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.trace("attempt to login, username: {}, password: {}", username, password);
        if (null == username || null == password) {
            response.setMsg("username or password is null");
            result.setData(response);
            return result;
        }
        // 根据用户名去数据查询盐值及存储密码，判断用户输入的"密码+盐"生成的MD5编码是否与数据库存储的相同
        // 相同，则通过验证
        User user = userService.getOne(username);
        if (user == null) {
            String msg = "user not exists";
            log.error("user not exists: {}", username);
            response.setMsg(msg);
            result.setData(response);
            return result;
        }
        String salt = user.getSalt();
        String md5info = user.getHashPassword();
        String realPassword = MD5Util.md5(password + salt);
        if (!md5info.equals(realPassword)) {
            log.error("passwd unvalied, username: {}", username);
            response.setMsg("passwd unvalied");
            result.setData(response);
            return result;
        }
        // 校验通过时，在session里放入一个标识
        // 后续通过session里是否存在该标识来判断用户是否登录
        request.getSession().setAttribute(SESSION_KEY, user);
        response.setSuccess(true);
        response.setMsg("logined");
        log.trace("login success, userId: {}", user.getId());
        result.setData(response);
        return result;
    }

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public ResponseNormal loginOut(HttpServletRequest request) {
        ResponseNormal response = new ResponseNormal();
        request.getSession().invalidate();
        response.setSuccess(true);
        response.setMsg("logout");
        return response;
    }

    /**
     * 新用户注册
     *
     * @param request
     */
    @RequestMapping("/register")
    public ResponseNormal register(HttpServletRequest request) {
        ResponseNormal response = new ResponseNormal();
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
        if (user != null) {
            response.setMsg("username exists");
            return response;
        }
        // TODO: 如何设计事务回滚
        userService.signUp(username, passWd, UserSexEnum.getSexEnum(Integer.parseInt(userSex)), nickName);
        response.setSuccess(true);
        response.setMsg("singnup success");
        log.trace("register: user: {}", username);
        return response;
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    /**
     * 欢迎页
     *
     * @return
     */
    @GetMapping("/welcome")
    public ResponseNormal welcome(HttpSession session) {
        ResponseNormal response = new ResponseNormal();
        String currentUsername = "anonymous";
        User user = (User) session.getAttribute(SESSION_KEY);
        if (user != null) {
            currentUsername = user.getNickName();
        }
        response.setSuccess(true);
        response.setMsg("Hello " + currentUsername);
        return response;
    }

}
