package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/joinform","/memberlist","/ranking","/memberjoin","/memberupdate","/updateform",})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

@Override
protected void service(HttpServletRequest req,
		HttpServletResponse resp)
				throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	String cmd = req.getServletPath();
	System.out.println("cmd="+cmd);

	//shopService 클래스(생략) --> shopDao 클래스
	ShopDao sDao = new ShopDao(req,resp); // 생성자에서 db 연결
	String path = null;
	switch (cmd) {
	case "/joinform": 
		path = sDao.joinform();
		break;
		
	case "/memberjoin" :
		path=sDao.memberjoin(); // DB에 회원 등록
		break;
		
	case "/memberlist" : 
		path=sDao.memberlist();
		break;
			
	case "/ranking" : 
		path=sDao.ranking();
		break;
		
	case "/updateform" :
		path=sDao.updateform(); //DB에서 회원정보 수정
		break;
	case "/memberupdate" : 
		path=sDao.memberupdate();
		break;
		
	}
	 sDao.close();//DB 연결 종료
		//포워딩
		req.getRequestDispatcher(path).forward(req, resp);
	} //service end
} //class end
