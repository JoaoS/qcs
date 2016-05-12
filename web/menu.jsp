<%--
  Created by IntelliJ IDEA.
  User: Christophe
  Date: 10/05/2016
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <title>Insulin Calculator</title>
    </head>
    <body>
        <div class="login">
            <div class="heading">
                <h2>Select type of insulin dose calculation:</h2>
                <form action="./MenuServlet" method="POST">
                    <button type="submit"  name="action" value="Standard" class="float">Standard Insulin Sensitivity</button>
                    <button type="submit"  name="action" value="Personal" class="float">Personal Insulin Sensitivity</button>
                    <button type="submit"  name="action" value="Background" class="float">Background Insulin Dose</button>
                </form>
            </div>
        </div>
    </body>
</html>
