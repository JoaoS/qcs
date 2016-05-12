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
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Insulin Calculator</title>
    </head>
    <body>
        <div class="login">
            <div class="heading">
                <div style="float:left; width:55%">
                    <h2 style="float:left; width:75%">Background Insulin Dose</h2>
                    <h2 style="float:right; color:red">9U</h2>
                </div>
                <div style="float:left; width:55%">
                    <form action="./BackgroundServlet" method="POST">
                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Weight in kilograms: </h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="mealCarbohydrates" placeholder="40kg - 130kg" required="required">
                        </div>
                        <button style="float:left; width:100%" type="submit" name="action" value="Calculation" class="float" onClick="enableButton()">Calculate Insulin Dose</button>
                    </form>
                    <form action="./CancelServlet" method="POST">
                        <button style="float:left; width:100%" type="submit" name="action" value="Cancel" class="float">Cancel</button>
                    </form>
                </div>

                <form action="./DetailsServlet" method="POST">
                    <div style="float:right; width:30%">
                        <button style="float:right; width:100%;  margin-top: 1%" type="submit"  name="action" value="Details" class="float" id="detailButton" disabled>Show Technical Details</button>
                        <h3 style="color:white; margin:20% 0">Number of web services: 3</h3>
                    </div>
                </form>

            </div>
        </div>
        <script>
            function enableButton() {
                document.getElementById("detailButton").disabled = false;
            }
        </script>
    </body>
</html>
