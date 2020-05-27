package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.enums.UserSexEnum;
import com.github.candyacao.model.ResponseNormal;
import com.github.candyacao.model.User;
import com.github.candyacao.model.UserView;
import com.github.candyacao.service.UserService;
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

    public static String getSessionKey() {
        return SESSION_KEY;
    }

    /**
     * 新用户注册
     *
     * @param request
     */
    @RequestMapping("/register")
    public Result<String> register(HttpServletRequest request) {
        Result<String> result = Result.build();
        String username = request.getParameter("username");
        String passWd = request.getParameter("password");
        String userSex = request.getParameter("userSex");
        String nickName = request.getParameter("nickName");
        // TODO: 设置验证码
        /*String checkCode = request.getParameter("checkCode");
        System.out.println("request"+request);
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("randCheckCode"));*/
        // TODO: 如何设计事务回滚
        User insertUser = userService.signUp(username, passWd, UserSexEnum.getSexEnum(Integer.parseInt(userSex)), nickName);
        log.trace("register: user: {}", username);
        result.setData(insertUser.getId()+"");
        return result;
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/get_user")
    public User getUser(Long id) {
        User user = userService.getOne(id);
        return user;
    }

    @RequestMapping("/me")
    public UserView me(HttpSession session) {
        User me = (User) session.getAttribute(SESSION_KEY);
        UserView ret = null;
        if (me != null) {
            ret = new UserView(me);
        }
        return ret;
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
     * 登录校验
     *
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public Result<UserView> login(HttpServletRequest request) {
        Result<UserView> result = Result.build();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.signIn(username, password);
        log.trace("attempt to login, username: {}, password: {}", username, password);
        // 校验通过时，在session里放入一个标识
        // 后续通过session里是否存在该标识来判断用户是否登录
        request.getSession().setAttribute(SESSION_KEY, user);
        log.trace("login success, userId: {}", user.getId());
        UserView retUser = new UserView(user);
        result.setData(retUser);
        return result;
    }

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public Result<String> loginOut(HttpServletRequest request) {
        Result<String> result = Result.build();
        request.getSession().invalidate();
        result.setData("注销成功");
        return result;
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
