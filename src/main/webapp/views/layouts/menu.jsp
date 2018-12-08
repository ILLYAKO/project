<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}">HOME</a></li>
        <li><a href="${pageContext.request.contextPath}/user/askForLogin">LOG IN</a></li>
        <li><a href="${pageContext.request.contextPath}/user/add">REGISTRATION</a></li>
    </ul>
  </div>
</nav>