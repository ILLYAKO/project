<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<strong>Healthy Habits</strong>
<hr/>


    <c:if test="${empty user}">

        <div id="menu">
            <jsp:include page="menu.jsp"/><br />
        </div>
    </c:if>


    <c:if test="${not empty user}">

        <div id="menu">
            <jsp:include page="menuRegisteredUser.jsp"/>
        </div>

        Registered User: ${user.getUserFirstName()}<br>

    </c:if>



<hr/>