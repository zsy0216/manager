package com.tassel.servlet;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.tassel.pojo.User;
import com.tassel.service.UserService;
import com.tassel.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @PackageName:${PACKAGE_NAME}
 * @Date:2019/7/13 12:54
 * @Author: zsy
 */
@WebServlet(name = "user", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    /**
     * 声明日志对象
     */
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    //获取service层对象
    UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        //获取操作符
        String oper = req.getParameter("oper");
        if ("login".equals(oper)) {
            //调用登录处理方法
            checkUserLogin(req, resp);
        } else if ("reg".equals(oper)) {
            //调用注册功能
            userReg(req, resp);
        } else if ("out".equals(oper)) {
            //调用退出功能
            userOut(req, resp);
        } else if ("pwd".equals(oper)) {
            //调用密码修改功能
            userChangePwd(req, resp);
        } else if ("show".equals(oper)) {
            //调用查看所有用户信息功能
            userShow(req, resp);
        } else {
            logger.debug("没有找到对应的操作符：" + oper);
        }
    }

    /**
     * 注册用户
     */
    private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        int age = req.getParameter("age") != "" ? Integer.parseInt(req.getParameter("age")) : 0;
        String birth = req.getParameter("birth");
        String[] bs = null;
        if (birth != "") {
            bs = birth.split("/");
            birth = bs[2] + "-" + bs[0] + "-" + bs[1];
        }
        User user = new User(0, uname, pwd, sex, age, birth);
        //处理请求信息
        //调用Service层处理
        int index = userService.userRegService(user);
        System.out.println(index);
        //响应处理结果
        if (index > 0) {
            //获取session
            HttpSession session = req.getSession();
            session.setAttribute("reg", "true");
            //请求重定向
            resp.sendRedirect("/Manage/login.jsp");
        }
    }

    /**
     * 显示所有用户信息
     */
    private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求，调用service
        List<User> userList = userService.userShowService();
        if (userList != null) {
            //将查询的用户数据存储到request对象
            req.setAttribute("lu", userList);
            //请求转发
            req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
        }
    }

    /**
     * 用户修改密码
     */
    private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取新密码数据
        String newPwd = req.getParameter("newPwd");
        //从session中获取用户信息
        User u = (User) req.getSession().getAttribute("user");
        int uid = u.getUid();
        /**
         * 处理请求
         * 调用service处理
         */
        int index = userService.userChangePwdService(newPwd, uid);
        if (index > 0) {
            //获取session对象
            HttpSession session = req.getSession();
            session.setAttribute("pwd", "true");
            //重定向到登录页面
            resp.sendRedirect("/Manage/login.jsp");
        }
    }

    /**
     * 用户退出
     */
    private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session对象
        HttpSession session = req.getSession();
        //强制销毁session
        session.invalidate();
        //重定向到登录页面
        resp.sendRedirect("/Manage/login.jsp");
    }

    /**
     * 处理登录
     */
    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        //处理请求信息
        //校验
        User u = userService.checkUserLoginService(uname, pwd);
        if (u != null) {
            //获取session对象
            HttpSession session = req.getSession();
            //将用户数据存储到session中
            session.setAttribute("user", u);
            //重定向
            resp.sendRedirect("/Manage/main/main.jsp");
        } else {
            //请求转发
            req.setAttribute("flag", 0);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        //响应处理结果
        //直接响应
        //请求转发
    }
}
