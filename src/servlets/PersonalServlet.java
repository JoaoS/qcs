package servlets;

import server.Controller;

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
@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {

    Controller controller;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("Calculation")){
            int physicalActivityLevel = Integer.parseInt(request.getParameter("todayActivity"));

            String[] activitySamples = request.getParameterValues("samplesActivity[]");
            int physicalActivitySamples[] = new int[activitySamples.length];
            for (int i=0; i< activitySamples.length; i++){
                physicalActivitySamples[i]= Integer.parseInt(activitySamples[i]);
                System.out.println(physicalActivitySamples[i]);
            }

            String[] bloodSamples = request.getParameterValues("samplesBlood[]");
            int bloodSugarDropSamples[] = new int[bloodSamples.length];
            for (int i=0; i< bloodSamples.length; i++){
                bloodSugarDropSamples[i]= Integer.parseInt(bloodSamples[i]);
                System.out.println(bloodSugarDropSamples[i]);
            }

            controller = new Controller("personalSensitivityToInsulin");
            controller.setPhysicalActivityLevel(physicalActivityLevel);
            controller.setPhysicalActivitySamples(physicalActivitySamples);
            controller.setBloodSugarDropSamples(bloodSugarDropSamples);
            try {
                controller.caller();
                int units = controller.getResult();

                session.setAttribute("totalInsulin",units+"U");
                session.removeAttribute("detailsInfo");
            } catch (Exception e) {
                e.printStackTrace();
            }


            request.getRequestDispatcher("personal.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
