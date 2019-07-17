<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <br/>
    <%
        //声明java代码块进行错误提示语的逻辑校验
        Object flag = request.getAttribute("flag");
        if (flag != null) {
    %>
    <div style="text-align: center;">
        <span style="font-size: 15px;color: darkred;font-weight: bold;">用户名或密码错误</span>
    </div>
    <% }
    %>

    <%
        //声明java代码块进行密码修改提示语的逻辑校验
        Object pwd = session.getAttribute("pwd");
        if (pwd != null) {
    %>
    <div style="text-align: center;">
        <span style="font-size: 15px;color: darkred;font-weight: bold;">密码修改成功</span>
    </div>
    <% }
        session.removeAttribute("pwd");
    %>

    <%
        //声明java代码块进行注测提示语的逻辑校验
        Object reg = session.getAttribute("reg");
        if (reg != null) {
    %>
    <div style="text-align: center;">
        <span style="font-size: 15px;color: darkred;font-weight: bold;">注册成功</span>
    </div>
    <% }
        session.removeAttribute("reg");
    %>

    <div class="loginbox loginbox1">
        <form action="user" method="post">
            <input type="hidden" name="oper" value="login"/>
            <ul>
                <li><input name="uname" type="text" class="loginuser" placeholder="用户名"/></li>
                <li><input name="pwd" type="password" class="loginpwd" placeholder="密码"/></li>
                <li class="yzm">
                <span><input name="" type="text" value="验证码"
                             onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite>
                </li>
                <li><input name="" type="submit" class="loginbtn" value="登录"
                           onclick="javascript:window.location='main.html'"/>
                    <label><a href="user/reg.jsp">注册</a></label>
                    <label><a href="#">忘记密码？</a></label></li>
            </ul>
        </form>

    </div>

</div>


<div class="loginbm">manage 2019.7.11 <a href="https://zsy.xyz">zsy.xyz</a> tassel test jsp and servlet.</div>


</body>

</html>

