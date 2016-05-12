package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Christophe on 10/05/2016.
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("Standard")){
            request.getRequestDispatcher("standard.jsp").forward(request,response);
        }
        else if(request.getParameter("action").equals("Personal")){
            request.getRequestDispatcher("personal.jsp").forward(request,response);
        }

        else if(request.getParameter("action").equals("Background")){
            request.getRequestDispatcher("background.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
