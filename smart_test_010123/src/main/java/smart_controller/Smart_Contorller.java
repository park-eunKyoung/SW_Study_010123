package smart_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/productlist","/processinsert","/processupdate","/worklist", "/workprocess"})
public class Smart_Contorller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, 
    		HttpServletResponse resp) 
    				throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	String cmd = req.getServletPath();
    	System.out.println("cmd="+cmd);
    	String path = null;
    	SmartDao sDao = new SmartDao(req,resp);
    	
    	switch (cmd) {
		case "/productlist":
			path = sDao.productlist();
			break;
		case "/processinsert" :
			path = sDao.processinsert();
			break;
		case "/worklist" :
			path = sDao.worklist();
			break;
		case "/workprocess" :
			path = sDao.workprocess();
			break;
		case "/processupdate":
			path = sDao.workupdate();
			break;
		}

    req.getRequestDispatcher(path).forward(req, resp);
    }
}
