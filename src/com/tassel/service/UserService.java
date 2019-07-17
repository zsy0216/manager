package com.tassel.service;

import com.tassel.pojo.User;

import java.util.List;

/**
 * @PackageName:com.tassel.service
 * @Date:2019/7/13 13:09
 * @Author: zsy
 */
public interface UserService {
    /**
     * @Description 校验用户登录
     * @Param [uname, pwd]
     * @Return com.tassel.pojo.User
     * @Date 13:10 2019/7/13
     **/
    User checkUserLoginService(String uname,String pwd);

    /**
     * 修改用户密码
     * @param newPwd
     * @param uid
     * @return
     */
    int userChangePwdService(String newPwd, int uid);

    /**
     * 获取所有的用户信息
     * @return
     */
    List<User> userShowService();

    /**
     * 用户注册
     * @param user
     * @return
     */
    int userRegService(User user);
}
