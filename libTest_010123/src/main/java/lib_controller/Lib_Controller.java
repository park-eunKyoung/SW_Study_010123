package lib_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/salesjoin","/salesjoinform","/totalsales","/companysales","/booksales"})
public class Lib_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
protected void service(HttpServletRequest req,
		HttpServletResponse resp) 
				throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	String cmd=req.getServletPath();
	System.out.println("cmd= "+cmd);
	String path = null;
	BookSalesDao bsDao = new BookSalesDao(req,resp);
	
	switch (cmd) {
	case "/salesjoin" :
		path=bsDao.salesjoin();
		break;
	case "/salesjoinform" :
		path=bsDao.salesjoinform();
		break;
	case "/totalsales" :
		path=bsDao.totalsales();
		break;
	case "/companysales":
		path=bsDao.companysalse();
		break;
	case "/booksales" :
		path=bsDao.booksales();
		break;
	}
		
	
	req.getRequestDispatcher(path).forward(req, resp);
}

}
