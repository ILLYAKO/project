<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>User Registration</title>
      <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
       <%-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">     --%>

       <link href="../public/css/style.css" rel="stylesheet">

    </head>
        <style>
                   div.ex {
                    text-align: right width:300px;
                    padding: 10px;
                    color: #c8f2ff;
                    margin: 0px
                    }
                    div#main {
                        color: red;
                     }

        </style>
    <body>
            <div id="header" class="header">
                <jsp:include page="/views/layouts/header.jsp"/>
            </div>
            <div id="main" class="col-md-5 col-md-offset-5">
        <h1>Registration Form</h1>
        <div class="ex">
            <form action="insert" method="post" class="form-horizontal">
                <table style="with: 50%">
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="userfirstname"  class="form-control" placeholder="Enter your first name" /></td>
                    </tr>
                    <tr>
                        <td>Second Name</td>
                        <td><input type="text" name="usersecondname" class="form-control" placeholder="Enter your last name" /></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" class="form-control" placeholder="Enter your user name"/></td>
                    </tr>
                    <tr>
                        <td>User Type</td>
                        <td>
                              <input type="radio" name="usertype" value="CLIENT" checked> Client
                              <input type="radio" name="usertype" value="ADVISOR"> Advisor
                              <input type="radio" name="usertype" value="ADMIN"> Admin



                        <%--    <select name='type' class="form-control">
                                        <option value="${user.type}" selected>${user.type}</option>
                                        <c:forEach items="${types}" var="type">
                                            <c:if test="${type != selected}">
                                                <option value="${type}">${type}</option>
                                            </c:if>
                                        </c:forEach>
                            </select>
                        --%>

                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="userpassword" class="form-control"  placeholder="Enter password"/></td>
                    </tr>
                    <tr>
                        <td>Age</td>
                        <td><input type="text" name="userage" class="form-control" placeholder="How old are you?"/></td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td>
                          <input type="radio" name="usergender" value="male" checked> Male
                          <input type="radio" name="usergender" value="female"> Female
                          <input type="radio" name="usergender" value="other"> Other
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="useremail" class="form-control" placeholder="Enter your email"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="useraddress" class="form-control" placeholder="Enter your address" /></td>
                    </tr>
                </table>
                <input type="submit" value="Register" class="btn btn-default btn-lg" />
                <input type="reset" value="Clear" name="clear" class="btn btn-default btn-lg" />
            </form>
            <br>
        </div>
        </div>
        <div id="footer" class="footer text-center">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>