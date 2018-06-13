<%--
  Created by IntelliJ IDEA.
  User: 63284
  Date: 2018/6/9
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }
    </style>
</head>
<body>
<div class="container">

    <form class="form-signin" action="/register" method="post">
        <div class="form-group row">
            <h2 class="form-signin-heading col-md-offset-4 col-sm-5">注册</h2><hr />
        </div>
        <div class="form-group row">
            <div class="col-md-3 col-md-offset-4"> <input type="text" name="userId" class="form-control" placeholder="用户名" required autofocus></div>
        </div>
        <div class="form-group row">
            <div class="col-sm-3 col-md-offset-4"> <input type="password" name="password1" class="form-control" placeholder="密码" required></div>
        </div>
        <div class="form-group row">
            <div class="col-sm-3 col-md-offset-4"> <input type="password" name="password2" class="form-control" placeholder="重复密码" required></div>
        </div>
        <div class="form-group row ">
            <div class="col-md-offset-4 col-sm-3">
                <button class="btn btn-primary btn-block" type="submit">确认</button>
            </div>
        </div>
        <div class="form-group row ">
            <div class="col-md-offset-4 col-sm-3">
                <button class="btn btn-default btn-block" type="submit" onclick="window.location.href='/loginpage'">返回</button>
            </div>
        </div>
    </form>
</div>

<script src="js/bootstrap.min.js"></script>
</body>
</html>
