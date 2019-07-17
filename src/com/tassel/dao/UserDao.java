package com.tassel.dao;

import com.tassel.pojo.User;

import java.util.List;

/**
 * @PackageName:com.tassel.dao
 * @Date:2019/7/13 13:12
 * @Author: zsy
 */
public interface UserDao {
    User checkUserLoginDao(String uname, String pwd);

    int userChangePwdDao(String newPwd, int uid);

    List<User> userShowDao();

    int userRegDao(User user);
}
