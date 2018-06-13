<%--
  Created by IntelliJ IDEA.
  User: 63284
  Date: 2018/6/13
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fk.css">
</head>
<body background="img/dust_scratches.png">
<nav class="navbar navbar-default navbar-fixed-top" >
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">首页</a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <!--<li><a href="#">Link</a></li>-->
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.userId}<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">修改密码</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="/logout">注销</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

    <div class="container" style="margin-top: 1em">
        <div class="row">
        <form action="/select">
            <div class="form-group">
            <div class="col-md-offset-6 col-md-4"><input class="form-control" type="text" placeholder="用户名" name="userId"></div>
            <div class="col-md-2"><button type="submit" class="btn btn-default">搜索</button></div>
            </div>
        </form>
        </div>
        <div class="row"style="margin-top: 3em">
        <div class="panel panel-default" >
            <!-- Default panel contents -->
            <div class="panel-heading"><div class="col-md-3"><h5>用户查询</h5></div><div class="text-right"><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#add">模拟消费</button></div></div>

            <!-- Table -->
            <table class="table table-hover">
                <tr>
                    <th>用户名</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <c:if test="${user.power eq -1}">
                            <td><button class="btn btn-primary btn-sm" onclick="window.location.href='/admrep?Id=${user.userId}'">解除挂失</button></td>
                        </c:if>
                        <c:if test="${user.power eq 0}">
                            <td>正常</td>
                        </c:if>
                        <c:if test="${user.power eq 1}">
                            <td>管理</td>
                        </c:if>

                        <td><button class="btn btn-danger btn-sm" onclick="window.location.href='/admdel?Id=${user.userId}'">删除用户</button></td>

                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    </div>

</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
