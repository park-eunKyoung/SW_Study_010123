package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/joinform","/memberlist","/ranking","/memberjoin","/update","/updateform"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
protected void service(HttpServletRequest req, 
		HttpServletResponse resp) 
				throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	String cmd = req.getServletPath();
	System.out.println("cmd="+cmd);
	String path=null;
	shopDao sDao = new shopDao(req,resp);
	switch (cmd) {
	case "/joinform":
		path=sDao.joinform();
		break;
	case "/memberlist" :
		path=sDao.memberlist();
		break;
	case "/memberjoin" :
		path=sDao.memberjoin();
		break;
	case "/updateform" :
		path=sDao.updateform();
		break;
	case "/update" :
		path=sDao.update();
		break;
	}
	sDao.close();
	//포워딩
	   req.getRequestDispatcher(path).forward(req, resp);
   }
}
