package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AuthDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
    private AuthDAO dao;
    
    @Override
    public void init() {
        dao = new AuthDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //preciso pegar usuário e senha e authenticar
        String user = request.getParameter("user");
        String passwd = request.getParameter("passwd");
        boolean is_permited = dao.exists(user,passwd);
        
        //if user admin is registred log it
        if(is_permited){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("");
        }
        //else set a error message in the GET attributes
        else{
            response.sendRedirect("?error=invalid-user");
        }
        
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session =  request.getSession();
        String user = (String)session.getAttribute("user");        
        
        //if exists parameter logout=true logout
        if("true".equals(request.getParameter("logout"))){
            session.removeAttribute("user");
            String path = request.getContextPath();
            response.sendRedirect(path);
        }
        
        //if user is an admin go to manager pages
        else if(dao.exists_by_name(user)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/index.jsp");
            dispatcher.forward(request, response); //view
        }
        //else go to login page
        else{
            if(request.getParameter("error") != null){
                request.setAttribute("message", "invalid user");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("login/formulario.jsp");
            dispatcher.forward(request, response); //view
        }
    }
}
