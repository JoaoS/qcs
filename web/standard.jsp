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
                    <h2 style="float:left; width:75%">Standard Insulin Sensitivity</h2>
                    <h2 style="float:right; color:red">9U</h2>
                </div>
                <div style="float:left; width:55%">
                    <form action="./StandardServlet" method="POST">
                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Total grams of carbohydrates in the meal: </h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="mealCarbohydrates" placeholder="60g - 120g" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Total grams of carbohydrates processed by 1 unit of rapid acting insulin:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="unitCarbohydrates" placeholder="10g/unit - 15g/unit" value="12" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Blood sugar level before the meal:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="bloodLevel" placeholder="120mg/dl - 250mg/dl" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Target blood sugar before the meal:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="bloodTarget" placeholder="80mg/dl - 120mg/dl" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Individual sensitivity:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="sensitivity" placeholder="15mg/dl - 100mg/dl" value="50" required="required">
                        </div>

                        <button style="float:left; width:100%" type="submit" name="action" value="Calculation" class="float">Calculate Insulin Dose</button>
                    </form>
                    <form action="./CancelServlet" method="POST">
                            <button style="float:left; width:100%" type="submit" name="action" value="Cancel" class="float">Cancel</button>
                    </form>
                </div>

                <form action="./DetailsServlet" method="POST">
                    <div style="float:right; width:30%">
                        <button style="float:right; width:100%;  margin-top: 1%" type="submit"  name="action" value="Details" class="float" disabled>Show Technical Details</button>
                        <h3 style="color:white; margin:20% 0">Number of web services: 3</h3>
                    </div>
                </form>

            </div>
        </div>
    </body>
</html>
