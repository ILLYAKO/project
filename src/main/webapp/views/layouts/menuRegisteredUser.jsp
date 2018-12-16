<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}">HOME</a></li>
        <li><a href="${pageContext.request.contextPath}/user/askForLogin">LOGIN</a></li>
        <li><a href="${pageContext.request.contextPath}/user/logout">LOGOUT</a></li>
        <li><a href="${pageContext.request.contextPath}/complaint/listComplaintOfUser">COMPLAINTS</a></li>
        <li><a href="${pageContext.request.contextPath}/advice/listAdvice">ADVICE</a></li>
        <li><a href="${pageContext.request.contextPath}/schedule/userSchedule">SCHEDULE</a></li>
        <li><a href="${pageContext.request.contextPath}/progress/userProgress">PROGRESS</a></li>
        <li><a href="https://www.sunlife.ca/ca/Insurance/Health+insurance?vgnLocale=en_CA">SHOP</a></li>
    </ul>
  </div>
</nav>