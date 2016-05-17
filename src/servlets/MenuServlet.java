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
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("comeFromStandard","");

        Controller n =Controller.getInstance();

        if(request.getParameter("action").equals("Standard")){
            session.setAttribute("TextValue1","");
            session.setAttribute("TextValue2","12");
            session.setAttribute("TextValue3","");
            session.setAttribute("TextValue4","");
            session.setAttribute("TextValue5","50");
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
