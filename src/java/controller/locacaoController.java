package controller;

import dao.ClientDAO;
import dao.LocacaoDAO;
import dao.LocadoraDAO;
import model.Cliente;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Container;
import model.Locacao;
import model.Locadora;

@WebServlet(name = "locacaoController", urlPatterns = {"/alugar"})
public class locacaoController extends HttpServlet {
    private ClientDAO dao;
    private LocadoraDAO locadoraDao;
    private LocacaoDAO locacaoDao;
    
    @Override
    public void init() {
        dao = new ClientDAO();
        locadoraDao = new LocadoraDAO();
        locacaoDao= new LocacaoDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action == null){
            action = "default";
        }
        
        switch(action){
            case "listar":
                listaLoc(request, response);
                break;
            default:
                processarGet(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //se cheguei aqui então preciso cadastrar uma locacao
        //recuperando dados do POST
        String cliente_id = request.getParameter("cliente");
        String locadora_id = request.getParameter("locadora");
        String data = request.getParameter("data");

        //registrando no banco de dados
        Locacao locacao = new Locacao(data, parseInt(cliente_id), parseInt(locadora_id));
        locacaoDao.insert(locacao);
        HttpSession session = request.getSession();
        response.sendRedirect("alugar?action=listar");
    }
    
    protected void processarGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String cliente_id = (String) session.getAttribute("cliente");

        //cliente logado
        if( cliente_id != null){           
            //tenho cliente_id na sessão e locadora_id no request
            Cliente cliente = dao.get(parseInt(cliente_id));
            request.setAttribute("cliente", cliente);
            
            String locadora_id = (String)session.getAttribute("locadora_id");
            session.removeAttribute("locadora_id");
            Locadora locadora = locadoraDao.get(parseInt(locadora_id));
            request.setAttribute("locadora", locadora);
            
            //tenho cliente  e locadora no request
            RequestDispatcher dispatcher = request.getRequestDispatcher("alugar.jsp");
            dispatcher.forward(request, response);
        }
        //cliente não logado
        else{
            //setando a locadora no request
            String locadora_id = request.getParameter("locadora");
            request.setAttribute("locadora_id", locadora_id);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request, response);
        }
    }
    protected void listaLoc(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //recuperando cliente da sessão
        HttpSession session = request.getSession();
        String cliente_id = (String)session.getAttribute("cliente"); //ultimo erro: cliente não e um atributo
        Cliente cliente = dao.get(parseInt(cliente_id));
        request.setAttribute("cliente", cliente);
        session.removeAttribute("cliente");
        List<Container> listContainer = new ArrayList<>();
        
        //consultando lsita de locações do cliente
        List<Locacao> listaLocacao= locacaoDao.getByClientID(cliente.getId());
        for(Locacao loc : listaLocacao){
            int id = loc.getLocadora_id();
            Locadora locadora = locadoraDao.get(id);
            Container container = new Container(locadora.getNome(), loc.getData());
            listContainer.add(container);
        }
        request.setAttribute("listContainer", listContainer);
        
        //foward
        RequestDispatcher dispatcher = request.getRequestDispatcher("listaLoc.jsp");
        dispatcher.forward(request, response);
    }
    
}

