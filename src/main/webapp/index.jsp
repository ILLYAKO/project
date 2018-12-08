<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Healthy Habits</title>
      <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
       <%-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">     --%>

       <link href="public/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div id="header" class="header">
            <jsp:include page="./views/layouts/header.jsp"/>
        </div>

        <div id="main" class="col-md-5 col-md-offset-5">
            We have all heard that having healthy habits such as eating well, staying active, and staying on top of our
            health screenings is really important. But have you ever really thought about why these things are so
            important, and how they all work together? Healthy habits include anything that you do to benefit your
            physical, mental, or emotional well-being. When put together, these habits help create a framework for
            a healthy life.
        </div>

        <div id="footer">
            <jsp:include page="./views/layouts/footer.jsp"/>
        </div>
    </body>
</html>