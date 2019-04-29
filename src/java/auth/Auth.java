package auth;

import dao.AuthDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/admin/*"})
public class Auth implements Filter{
    private AuthDAO dao;
      
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        dao = new AuthDAO();
    }
    
   @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httprequest = (HttpServletRequest) req;
        HttpSession session = httprequest.getSession(false);
        
        HttpServletResponse httpresponse = (HttpServletResponse)res;

        String user = (String) session.getAttribute("user");
        if(dao.exists_by_name(user)){
            chain.doFilter(req, res);
        }
        else{
            String path = httprequest.getContextPath();
            path += "/login";
            httpresponse.sendRedirect(path);
        }
    }
    
    @Override
    public void destroy() {
        Filter.super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

}
