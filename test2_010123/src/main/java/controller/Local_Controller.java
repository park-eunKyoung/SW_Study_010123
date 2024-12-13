package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/memberlist", "/insertvote", "/insertvoteform","/vokelist" })
public class Local_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getServletPath();
		System.out.println("cmd= " + cmd);

		String path = null;

		LocalDao lDao = new LocalDao(req, resp);

		switch (cmd) {
		case "/memberlist":
			path = lDao.memberlist();
			break;
		case "/insertvote":
			path = lDao.insertvote();
			break;
		case "/insertvoteform":
			path = lDao.insertvoteform();
			break;
		case "/vokelist" : 
			path = lDao.vokelist();
			break;
		}
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
