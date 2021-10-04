<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
<!doctype html>
--%>
<html lang="en">
    <head>
       <title>Advice List</title>
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

        <div id="main" class="col-md-8 col-md-offset-2">

                    <h2>ADVICES:</h2>

                    <c:if test="${message != null}">
                        <div class="alert <c:out value='${message.type.reference}' />">
                          <strong><c:out value='${message.text}' /></strong>
                        </div>
                        <center>
                            <div>
                                <span></span>
                            <div>
                        </center>
                    </c:if>
                    <div class="ex">
                    <table class="table table-hover">
                        <caption><h2>List of Advices</h2></caption>
                        <tr>
                            <th>Advice </th>
                            <th>Advice part</th>
                            <th>Advice type</th>
                            <th>Advice description</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="advice" items="${listAdvice}">
                            <tr>
                                <td><c:out value="${advice.adviceName}" /></td>
                                <td><c:out value="${advice.advicePart}" /></td>
                                <td><c:out value="${advice.adviceType}" /></td>
                                 <td><c:out value="${advice.adviceDescription}" /></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/advice/edit?id=<c:out value='${advice.adviceId}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="${pageContext.request.contextPath}/advice/delete?id=<c:out value='${advice.adviceId}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="${pageContext.request.contextPath}/advice/add" role="button" class="btn btn-info btn-lg">Add Advice</a>
                </div>



        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>