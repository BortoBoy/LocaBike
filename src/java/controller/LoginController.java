package controller;

import dao.ClientDAO;
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
    private AuthDAO admindao;
    private ClientDAO clientedao;
    
    @Override
    public void init() {
        admindao = new AuthDAO();
        clientedao = new ClientDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //preciso pegar usuário e senha e authenticar
        String user = request.getParameter("user");
        String passwd = request.getParameter("passwd");
        String locadora_id = request.getParameter("locadora");
        int id = clientedao.exists(user, passwd);
        
        //se ele existe então registre-o na seção com o parametro user
        if(admindao.exists(user,passwd)){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("");
        }
        //se o cliente existre registre na seçao o id do cleinte com o parametro cliente
        else if(id != -1){
            HttpSession session = request.getSession();
            session.setAttribute("cliente", Integer.toString(id));
            session.setAttribute("locadora_id", locadora_id);
            response.sendRedirect("alugar");
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
        else if(admindao.exists_by_name(user)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/index.jsp");
            dispatcher.forward(request, response); //view
        }
        //else go to login page
        else{
            if(request.getParameter("error") != null){
                request.setAttribute("message", "invalid user");
            }            
            RequestDispatcher dispatcher = request.getRequestDispatcher("login/formulario.jsp");
            dispatcher.forward(request, response);
        }
    }
}
