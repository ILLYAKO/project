<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.logging.Logger" %>

<%--
<!doctype html>
--%>
<html lang="en">
    <head>
       <title>Complaint List</title>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
       <!--<link href="/test14/public/css/style.css" rel="stylesheet">-->
        <link href="../static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div id="header" class="header">
            <jsp:include page="/views/layouts/header.jsp"/>
        </div>
        <div id="main" class="col-md-8 col-md-offset-2">
                    <h2>COMPLAINTS:</h2>
                    <c:if test="${message != null}">
                      <div class="alert <c:out value='${message.type.reference}' />">
                        <div class="alert">
                          <strong><c:out value='${message.text}' /></strong>
                        </div>
                      </div>
                    </c:if>
                    <div class="ex">
                    <table class="table table-hover">
                        <caption><h2>List of Complaints</h2></caption>
                        <tr>
                            <th>Complaint type</th>
                            <th>Complaint part</th>
                            <th>Complaint description</th>
                            <th>Advice</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="complaint" items="${listComplaint}">
                            <tr>
                                <td><c:out value="${complaint.complaintType.complaintTypeShortName}" /></td>
                                <td><c:out value="${complaint.complaintPart.getComplaintPartName()}" /></td>
                                <td><c:out value="${complaint.complaintPart.getComplaintPartDescription()}" /></td>

                                    <c:forEach var="advice" items="${listAdvice}">
                                        <c:if test="${advice.getAdvicePart() == complaint.complaintPart.getComplaintPartName()}">
                                            <td><c:out value="${advice.adviceName}" /></td>
                                        </c:if>
                                    </c:forEach>

                                <td>
                                    <a href="${pageContext.request.contextPath}/complaint/edit?id=<c:out value='${complaint.complaintId}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="${pageContext.request.contextPath}/complaint/delete?id=<c:out value='${complaint.complaintId}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="${pageContext.request.contextPath}/complaint/add" role="button" class="btn btn-info btn-lg">Add Complaint</a>
                </div>



        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        </div>
        <div id="footer" class="footer text-center">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>