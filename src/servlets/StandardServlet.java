package servlets;

import methods.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Christophe on 10/05/2016.
 */
@WebServlet("/StandardServlet")
public class StandardServlet extends HttpServlet {

    Controller controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("SensitivityCalculation")){
            session.setAttribute("comeFromStandard","true");
            session.setAttribute("TextValue1",request.getParameter("mealCarbohydrates"));
            session.setAttribute("TextValue2",request.getParameter("unitCarbohydrates"));
            session.setAttribute("TextValue3",request.getParameter("bloodLevel"));
            session.setAttribute("TextValue4",request.getParameter("bloodTarget"));
            session.setAttribute("TextValue5",request.getParameter("sensitivity"));
            request.getRequestDispatcher("personal.jsp").forward(request,response);
        }

        if(request.getParameter("action").equals("Calculation")){
            int carbohydrateAmount = Integer.parseInt(request.getParameter("mealCarbohydrates"));
            int carbohydrateToInsulinRatio = Integer.parseInt(request.getParameter("unitCarbohydrates"));
            int preMealBloodSugar = Integer.parseInt(request.getParameter("bloodLevel"));
            int targetBloodSugar = Integer.parseInt(request.getParameter("bloodTarget"));
            int personalSensitivity = Integer.parseInt(request.getParameter("sensitivity"));


            controller = new Controller("mealtimeInsulinDose");
            controller.setCarbohydrateAmount(carbohydrateAmount);
            controller.setCarbohydrateToInsulinRatio(carbohydrateToInsulinRatio);
            controller.setPreMealBloodSugar(preMealBloodSugar);
            controller.setTargetBloodSugar(targetBloodSugar);
            controller.setPersonalSensitivity(personalSensitivity);

            try {
                controller.caller();
                int units = controller.getResult();

                session.setAttribute("totalInsulin",units+"U");
                session.removeAttribute("detailsInfo");
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("standard.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
