<% System.out.println("The page of the registered User"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
<!doctype html>
--%>
<html lang="en">
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <%-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">     --%>

       <link href="/test14/public/css/style.css" rel="stylesheet">


    </head>
    <body>
        <div id="header" class="header">
            <jsp:include page="/views/layouts/header.jsp"/>
        </div>

        <div id="main" class="col-md-5 col-md-offset-5">

        <div class="title-container">
            <h1>
                <c:if test="${user!='null'}">
                    Registered User<br>
                </c:if>
                <c:if test="${isWrong}">
                    Wrong combination email & password. OR<br>
                </c:if>
                <c:if test="${isEdit}">
                    Edit User
                </c:if>
                <c:if test="${isNew}">
                    Add New User
                </c:if>
            </h1>
        </div>

        <h2>User Details</h2>
        </div>



        <div id="footer" class="footer text-center">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>