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
@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(request.getParameter("action").equals("Cancel")){
            if(session.getAttribute("comeFromStandard").equals("true")){
                session.setAttribute("comeFromStandard","");
                session.setAttribute("totalInsulin", "");
                request.getRequestDispatcher("standard.jsp").forward(request, response);
            }
            else {
                session.setAttribute("totalInsulin", "");
                session.setAttribute("detailsInfo", "");
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
