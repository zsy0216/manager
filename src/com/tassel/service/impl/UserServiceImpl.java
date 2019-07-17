package com.tassel.service.impl;

import com.tassel.dao.UserDao;
import com.tassel.dao.impl.UserDaoImpl;
import com.tassel.pojo.User;
import com.tassel.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * @PackageName:com.tassel.service.impl
 * @Date:2019/7/13 13:11
 * @Author: zsy
 */
public class UserServiceImpl implements UserService {

    /**
     * 声明日志对象
     */
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    UserDao userDao = new UserDaoImpl();

    @Override
    public User checkUserLoginService(String uname, String pwd) {

        logger.debug(uname + "发起登陆请求");
        User user = userDao.checkUserLoginDao(uname, pwd);
        if (user != null) {
            logger.debug(uname + "登录成功");
        } else {
            logger.debug(uname + "登录失败");
        }
        return user;
    }

    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + ":发起密码修改请求");
        int index = userDao.userChangePwdDao(newPwd, uid);
        if (index > 0) {
            logger.debug(uid + ":密码修改成功！");
        } else {
            logger.debug(uid + ":密码修改失败！");
        }
        return index;
    }

    @Override
    public List<User> userShowService() {
        List<User> userList = userDao.userShowDao();
        logger.debug("显示所有用户信息：" + userList);
        return userList;
    }

    @Override
    public int userRegService(User user) {
        return userDao.userRegDao(user);
    }
}
