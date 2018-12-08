<% System.out.println("page Ask for Login."); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <title>Healthy Habits</title>
      <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
       <%-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">     --%>

       <link href="../public/css/style.css" rel="stylesheet">
       <title>User Login</title>
    </head>
    <body>
        <div id="header">
            <jsp:include page="/views/layouts/header.jsp"/>
        </div>

        <div class="title-container">
            <h1>
                <c:if test="${isWrong}">
                    Wrong combination email & password.<br>
                    Sorry.Try again!<br>
                </c:if>
                <c:if test="${isEdit}">
                    Edit User
                </c:if>
                <c:if test="${isNew}">
                    Add New User
                </c:if>
            </h1>
        </div>

        <h2>Login. User Details</h2>
        <form action="${pageContext.request.contextPath}/user/login" method="post">
            Enter Email : <input type="text" name="userEmail"><br/>
            Enter Password : <input type="password" name ="password"><br/>
            <input type ="submit" value="Login">
        </form>
        <div id="footer">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>