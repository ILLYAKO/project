
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

       <!--<link href="/test14/public/css/style.css" rel="stylesheet">-->
        <link href="../static/css/style.css" rel="stylesheet">


    </head>
    <body>
        <div id="header" class="header">
            <jsp:include page="/views/layouts/header.jsp"/>
        </div>

        <div id="main" class="col-md-5 col-md-offset-5">

        <div class="title-container">
            <h1>
                <c:if test="${user!='null'}">
                    Hello ${user.getUserFirstName()}.<br>
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

        <div class="col-md-12">

            <h1>Your Progress</h1>

            <div class="progress">

                 <div class="progress-bar" style="width: 75%;">

                 <span class="">75% Complete</span>

                 </div>
             </div>

        </div>


        </div>



        <div id="footer" class="footer text-center">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>