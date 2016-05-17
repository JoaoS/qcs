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
                            <h3 style="float:left; width:75%">Today's physical activity level:</h3>
                            <input style="float:left; width:25%; height:35px; font-size:20px; margin-top: 1%" id="todayActivity" type="number" name="todayActivity" placeholder="0 - 10" min="0" max="10" required="required">
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:95%">Samples of physical activity level in a given day:</h3>
                            <input style="float:right; width:5%; height:50px;" type="button" name="action" value="+" class="float" onClick="addInputActivity('dynamicInputActivity');">
                            <div style="width:100%">
                                <div id="dynamicInputActivity">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" class="samplesActivity" type="number" name="samplesActivity[]" placeholder="0 - 10" min="0" max="10" required="required">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" class="samplesActivity" type="number" name="samplesActivity[]" placeholder="0 - 10" min="0" max="10" required="required">
                                </div>
                            </div>
                        </div>

                        <div style="width: 100%">
                            <h3 style="float:left; width:95%">Samples of drops in blood sugar:</h3>
                            <input style="float:right; width:5%; height:50px" type="button" name="action" value="+" class="float" onClick="addInputBlood('dynamicInputBlood');">
                            <div style="width:100%">
                                <div id="dynamicInputBlood">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" class="samplesBlood" type="number" name="samplesBlood[]" placeholder="15mg/dl - 100mg/dl" min="15" max="100" required="required">
                                    <br><input style="float:left; width:19%; height:35px; font-size:20px; margin:0.50%" class="samplesBlood" type="number" name="samplesBlood[]" placeholder="15mg/dl - 100mg/dl" min="15" max="100" required="required">
                                </div>
                            </div>
                        </div>

                        <button style="float:left; width:100%" id="calculation" type="submit" name="action" value="Calculation" class="float" onClick="verifyIfOdd();">Calculate Insulin Dose</button>
                    </form>
                    <form action="./CancelServlet" method="POST">
                        <button style="float:left; width:100%" type="submit" name="action" value="Cancel" class="float">Cancel</button>
                    </form>
                </div>
                <form action="./DetailsServlet" method="POST" id="form2">
                    <div style="float:right; width:30%">
                        <button style="float:right; width:100%; margin-top: 1%" type="submit"  id="detailButton" name="action" value="Details" class="float" onClick="activate();">Show Technical Details</button>
                        <h3 style="color:white; margin:20% 0">${detailsInfo}</h3>
                    </div>
                </form>
            </div>
        </div>
        <script>
            var activityCounter = 2;
            var bloodCounter = 2;
            var limit = 10;


            function activate(){
                if(document.getElementsByTagName("h2").item(1).hasChildNodes()){
                    document.getElementById("detailButton").disabled = false;
                    sessionStorage.removeItem("totalInsulin");
                    document.getElementById('form2').onsubmit = function(){
                        return true;
                    }
                }
                else{
                    alert("Need to calculate something!")
                    document.getElementById("detailButton").disabled = true;
                    document.getElementById('form2').onsubmit = function(){
                        return false;
                    }
                }
            }

            function addInputActivity(divName){
                if (activityCounter == limit)  {
                    alert("You have reached the limit of adding " + activityCounter + " inputs");
                }
                else {
                    var newdiv = document.createElement('div');
                    newdiv.innerHTML = "<input style='float:left; width:19%; height:35px; font-size:20px; margin:0.50%' class='samplesActivity' type='number' name='samplesActivity[]' placeholder='0 - 10' min='0' max='10' required='required'>";
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
                    newdiv.innerHTML = "<input style='float:left; width:19%; height:35px; font-size:20px; margin:0.50%' class='samplesBlood' type='number' name='samplesBlood[]' placeholder='15mg/dl - 100mg/dl' min='15' max='100' required='required'>";
                    document.getElementById(divName).appendChild(newdiv);
                    bloodCounter++;
                }
            }
            
            function allInputsFilled() {
                var fields = ["mealCarbohydrates", "unitCarbohydrates", "bloodLevel", "bloodTarget", "todayActivity", "samplesActivity[]", "samplesBlood[]"];
                for (var i=0; i< fields.length-2; i++){
                    if(document.getElementById(fields[i]).value==""){
                        return false;
                    }
                }

                var samplesA = document.getElementsByName("samplesActivity[]");
                var samplesB = document.getElementsByName("samplesBlood[]");
                for (var i=0; i<samplesA.length; i++){
                    if(samplesA[i].value=="" || samplesB[i].value==""){
                        return false;
                    }
                }
                return true;
            }

            function verifyIfOdd(){
                if(bloodCounter != activityCounter){
                    alert("Number of samples of physical activity and drops in blood sugar is odd: "+ activityCounter + " : " + bloodCounter);
                    document.getElementById('form').onsubmit = function(){
                        return false;
                    }
                }

                else{
                    if(allInputsFilled()){
                        document.getElementById('form').onsubmit = function(){
                            return true;
                        }
                    }
                }
            }
        </script>
    </body>
</html>
