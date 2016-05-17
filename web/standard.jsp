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
                    <h2 style="float:right; color:red" id="totalInsulin">${totalInsulin}</h2>
                </div>
                <div style="float:left; width:55%">
                    <form action="./StandardServlet" method="POST" id="form">
                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Total grams of carbohydrates in the meal: </h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" value='${TextValue1}' id="mealCarbohydrates" type="number" name="mealCarbohydrates" placeholder="60g - 120g" min="60" max="120" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Total grams of carbohydrates processed by 1 unit of rapid acting insulin:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" id="unitCarbohydrates" type="number" name="unitCarbohydrates" placeholder="10g/unit - 15g/unit" min="10" max="15" value='${TextValue2}' required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Blood sugar level before the meal:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" value='${TextValue3}' id ="bloodLevel" type="number" name="bloodLevel" placeholder="120mg/dl - 250mg/dl" min="120" max="250" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Target blood sugar before the meal:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" value='${TextValue4}' id="bloodTarget" type="number" name="bloodTarget" placeholder="80mg/dl - 120mg/dl" min="80" max="120" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:75%">Individual sensitivity:</h3>
                            <button style="float:right; width:25%" type="submit" name="action" value="SensitivityCalculation" onClick="disableRequired()" class="float">Calculate New Sensitivity</button>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" id="sensitivity" type="number" name="sensitivity" placeholder="15mg/dl - 100mg/dl" min="15" max="100" value='${TextValue5}' required="required">
                        </div>

                        <button style="float:left; width:100%" id="calculation" type="submit" name="action" value="Calculation" class="float" onClick="enableRequired()">Calculate Insulin Dose</button>
                    </form>
                    <form action="./CancelServlet" method="POST">
                            <button style="float:left; width:100%" type="submit" name="action" value="Cancel" class="float">Cancel</button>
                    </form>
                </div>

                <form action="./DetailsServlet" method="POST" id="form2">
                    <div style="float:right; width:30%">
                        <button style="float:right; width:100%; margin-top: 1%" type="submit"  id="detailButton" name="action" value="Details" class="float" onClick="activate();">Show Technical Details</button>
                        <h3 style="color:white; margin:20% 0" id="details">${detailsInfo}</h3>
                    </div>
                </form>

            </div>
        </div>

        <script>
            function disableRequired() {
                document.getElementById("mealCarbohydrates").required = false;
                document.getElementById("unitCarbohydrates").required = false;
                document.getElementById("bloodLevel").required = false;
                document.getElementById("bloodTarget").required = false;
                document.getElementById("sensitivity").required = false;
            }

            function enableRequired() {
                document.getElementById("mealCarbohydrates").required = true;
                document.getElementById("unitCarbohydrates").required = true;
                document.getElementById("bloodLevel").required = true;
                document.getElementById("bloodTarget").required = true;
                document.getElementById("sensitivity").required = true;
            }


            function activate(){
                if(document.getElementsByTagName("h2").item(1).hasChildNodes()){
                    document.getElementById("detailButton").disabled = false;
                    sessionStorage.removeItem("totalInsulin");
                    document.getElementById('form2').onsubmit = function(){
                        return true;
                    }
                }
                else{
                    alert("Need to calculate something!");
                    document.getElementById("detailButton").disabled = true;
                    document.getElementById('form2').onsubmit = function(){
                        return false;
                    }
                }
            }
        </script>
    </body>
</html>
