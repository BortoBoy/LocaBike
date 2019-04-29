package controller;

import dao.LocadoraDAO;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Locadora;

@WebServlet(name = "InitialController", urlPatterns = {"/"})
public class InitialController extends HttpServlet {
    private LocadoraDAO dao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        String cidade = request.getParameter("cidade");
        List<Locadora> locadoraList;
        if(cidade != null){
            locadoraList = dao.get_by_cidade(cidade);
        }
        else{
            locadoraList = dao.getAll();
        }
        List<String> cidadesList = dao.getCidades();        
        request.setAttribute("list", locadoraList);
        request.setAttribute("listCidades", cidadesList);
        String path = request.getContextPath();
        request.setAttribute("path", path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
