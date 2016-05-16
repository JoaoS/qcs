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
@WebServlet("/BackgroundServlet")
public class BackgroundServlet extends HttpServlet {

    Controller controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("Calculation")){
            int bodyWeight = Integer.parseInt(request.getParameter("mealCarbohydrates"));

            controller = new Controller("backgroundInsulinDose");
            controller.setBodyWeight(bodyWeight);

            try {
                controller.caller();
                int units = controller.getResult();

                session.setAttribute("totalInsulin",units+"U");
                session.removeAttribute("detailsInfo");
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("background.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
