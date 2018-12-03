<% System.out.println("The page of the registered User"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Test page</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <div id="header">
            <jsp:include page="/views/layouts/header.jsp"/>
        </div>

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



        <div id="footer">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>