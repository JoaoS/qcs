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
                    <h2 style="float:left; width:75%">Personal Insulin Sensitivity</h2>
                    <h2 style="float:right; color:red" id="totalInsulin">${totalInsulin}</h2>
                </div>

                <div style="float:left; width:55%">
                    <form action="./PersonalServlet" id="form" method="POST">
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
                            <h3 style="float:left; width:75%">Today's physical activity level:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" type="number" name="todayActivity" placeholder="0 - 10" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:95%">Samples of physical activity level in a given day:</h3>
                            <input style="float:right; width:5%; height:50px;" type="button" name="action" value="+" class="float" onClick="addInputActivity('dynamicInputActivity');">
                            <div style="width:100%">
                                <div id="dynamicInputActivity">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" type="number" name="samplesActivity[]" placeholder="0 - 10"  required="required">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" type="number" name="samplesActivity[]" placeholder="0 - 10"  required="required">
                                </div>
                            </div>
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:95%">Samples of drops in blood sugar:</h3>
                            <input style="float:right; width:5%; height:50px" type="button" name="action" value="+" class="float" onClick="addInputBlood('dynamicInputBlood');">
                            <div style="width:100%">
                                <div id="dynamicInputBlood">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" type="number" name="samplesBlood[]" placeholder="15mg/dl - 100mg/dl"  required="required">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" type="number" name="samplesBlood[]" placeholder="15mg/dl - 100mg/dl"  required="required">
                                </div>
                            </div>
                        </div>

                        <button style="float:left; width:100%" id="calculation" type="submit" name="action" value="Calculation" class="float" onClick="verifyIfOdd();">Calculate Insulin Dose</button>
                    </form>
                    <form action="./CancelServlet" method="POST">
                        <button style="float:left; width:100%" type="submit" name="action" value="Cancel" class="float">Cancel</button>
                    </form>
                </div>
                <form action="./DetailsServlet" method="POST">
                    <div style="float:right; width:30%">
                        <button style="float:right; width:100%; margin-top: 1%" type="submit"  id="detailButton" name="action" value="Details" class="float" disabled>Show Technical Details</button>
                        <h3 style="color:white; margin:20% 0">${detailsInfo}</h3>
                    </div>
                </form>
            </div>
        </div>
        <script>
            var activityCounter = 2;
            var bloodCounter = 2;
            var limit = 10;

            function addInputActivity(divName){
                if (activityCounter == limit)  {
                    alert("You have reached the limit of adding " + activityCounter + " inputs");
                }
                else {
                    var newdiv = document.createElement('div');
                    newdiv.innerHTML = "<input style='float:left; width:19%; height:35px; font-size:20px; margin:0.50%' type='number' name='samplesActivity[]' placeholder='0 - 10'  required='required'>";
                    document.getElementById(divName).appendChild(newdiv);
                    activityCounter++;
                }
            }

            function addInputBlood(divName){
                if (bloodCounter == limit)  {
                    alert("You have reached the limit of adding " + bloodCounter + " inputs");
                }
                else {
                    var newdiv = document.createElement('div');
                    newdiv.innerHTML = "<input style='float:left; width:19%; height:35px; font-size:20px; margin:0.50%' type='number' name='samplesBlood[]' placeholder='15mg/dl - 100mg/dl'  required='required'>";
                    document.getElementById(divName).appendChild(newdiv);
                    bloodCounter++;
                }
            }

            function verifyIfOdd(){
                if(bloodCounter != activityCounter){
                    alert("Number of samples of physical activity and drops in blood sugar is odd: "+ activityCounter + " : " + bloodCounter);
                    document.getElementById('form').onsubmit = function(){
                        return false;
                    }
                }

                else{
                    document.getElementById("detailButton").disabled = false;
                    document.getElementById('form').onsubmit = function(){
                        return true;
                    }
                }
            }
        </script>
    </body>
</html>
