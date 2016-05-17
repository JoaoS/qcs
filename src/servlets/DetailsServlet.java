package servlets;

import server.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Christophe on 10/05/2016.
 */
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller controller = Controller.getInstance();
        if(request.getParameter("action").equals("Cancel")){
            request.getRequestDispatcher("menu.jsp").forward(request,response);
        }
        else if(request.getParameter("action").equals("Details")){
            String detailResponse = "";
            int N_WebServices = 0;
            int majorityResult = 0;
            int i = 0;
            ArrayList<Integer> values;

            values = controller.getOutput();
            System.out.println("values="+values);
            for(i=0; i<values.size();i++){

                //esta activo
                if (values.get(i)!=-1){
                    N_WebServices++;
                }


            }
            detailResponse += "Number of web services: " + N_WebServices + "<br>";
            for(i=0; i <N_WebServices; i++){
                detailResponse += "&emsp;&emsp;Web service [" + (i+1) + "]: " + values.get(i) + "<br>";
            }
            detailResponse += "<br>Majority Result: " + controller.getVoterValue();
            session.setAttribute("detailsInfo",detailResponse);
            session.setAttribute("totalInsulin","");


            String servletName = request.getHeader("referer");
            if(servletName.contains("PersonalServlet")) {
                request.getRequestDispatcher("personal.jsp").forward(request, response);
            }
            else if(servletName.contains("StandardServlet")) {
                request.getRequestDispatcher("standard.jsp").forward(request, response);
            }
            else if(servletName.contains("BackgroundServlet")) {
                request.getRequestDispatcher("background.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
