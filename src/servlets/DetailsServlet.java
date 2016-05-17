package src.servlets;

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
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(request.getParameter("action").equals("Cancel")){
            request.getRequestDispatcher("menu.jsp").forward(request,response);
        }
        else if(request.getParameter("action").equals("Details")){
            String detailResponse = "";
            int N_WebServices = 3;
            int majorityResult = 9;
            int[] values  = {9,8,9};

            detailResponse += "Number of web services: " + N_WebServices + "<br>";
            for(int i=0; i <N_WebServices; i++){
                detailResponse += "&emsp;&emsp;Web service [" + (i+1) + "]: " + values[i] + "<br>";
            }
            detailResponse += "<br>Majority Result: " + majorityResult;
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
