<%--
  Created by IntelliJ IDEA.
  User: 63284
  Date: 2018/6/4
  Time: 14:57
  To change this template use File | Settings | File Templates.

为保障该页面表格更新及时，该页面只能经由RecordServlet跳转
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>主页</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/fk.css">
</head>
<body background="img/dust_scratches.png">
<!--<div style="background-color: #8E8C84">-->
<!--<div class="container">-->
<!--<h3 style="color: white">-->
<!--饭卡管理系统-->
<!--</h3>-->
<!--</div>-->
<!--</div>-->

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
      <a class="navbar-brand" href="#">首页</a>
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


<div class="container">
  <div class="col-md-8">
    <h1>
      饭卡管理
    </h1>
  </div>
  <h1></h1>
  <div class="col-md-4">

    <ul class="nav nav-pills">
      <li class="active"><a href="#">我的饭卡</a></li>
      <li><a href="/report">挂失</a></li>
        <c:if test="${user.power eq 1}">
        <li><a href="/selectpage">管理</a></li>
        </c:if>
        <c:if test="${user.power ne 1}">
            <li class="disabled"><a href="/selectpage">管理</a></li>
        </c:if>
      <li><a href="delete">销号</a></li>
    </ul>

  </div>
  <div class="clearfix"></div>
  <HR align=center width=300 color=#987cb9 SIZE=1>
  <div class="jumbotron" style="background-color: #f2efe6 ">
    <div class="row">
      <h2 class="col-md-6">账户余额：${user.balance}元</h2>
      <p class="text-right"><a class="btn btn-danger btn-lg bigfont" data-toggle="modal" data-target="#save">立即充值</a></p>
    </div>
  </div>


  <div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><div class="col-md-3"><h5>最近消费记录</h5></div><div class="text-right"><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#add">模拟消费</button></div></div>

    <!-- Table -->
    <table class="table table-hover">
      <tr>
        <th>时间</th>
        <th>消费事项</th>
        <th>金额</th>
      </tr>

        <c:forEach items="${records}" var="Record">
            <tr>
                <td>${Record.date}</td>
                <td>${Record.message}</td>
                <td>-${Record.pay}</td>

            </tr>
        </c:forEach>

    </table>
  </div>

</div>
<%--模拟充值面板--%>
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog " role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">模拟消费</h4>
      </div>

      <div class="modal-body">
        <form class="form-horizontal center-block" action="/pay" method="post" id="addform">
          <div class="row form-group">
            <div class="col-md-2 text-right col-md-offset-2">
              <label class="control-label">日期</label>
            </div>
            <div class="col-md-4">
              <input class="input form-control" type="date" name="date" required>
            </div>
          </div>
          <div class="row form-group">
            <div class="col-md-2 text-right col-md-offset-2" >
              <label class="control-label">信息</label>
            </div>
            <div class="col-md-4">
              <input class="input form-control" type="text" name="message" required>
            </div>
          </div>
          <div class="row form-group">
            <div class="col-md-2 text-right col-md-offset-2">
              <label class="control-label">金额</label>
            </div>
            <div class="col-md-4">
              <input class="input form-control" type="text" name="pay">
            </div>
          </div>
        </form>

      </div>



      <div class="modal-footer">

        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary" form="addform">添加</button>

      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="save" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <
  <div class="modal-dialog " role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">充值</h4>
      </div>

      <div class="modal-body">
        <form class="form-horizontal center-block" action="/save" method="post" ID="saveform">

          <div class="row form-group">
            <div class="col-md-2 text-right col-md-offset-2">
              <label class="control-label">金额</label>
            </div>
            <div class="col-md-4">
              <input class="input form-control" type="text" name="save" required>
            </div>
          </div>
        </form>

      </div>



      <div class="modal-footer">

        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary" form="saveform">充值</button>

      </div>
    </div>
  </div>
</div>
</body>
<footer>
  <div class="html-top container-fluid" id="footer" >

  </div>
</footer>


<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
