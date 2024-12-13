package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopDao {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;

	HttpServletRequest req;
	HttpServletResponse resp;

	public ShopDao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.41:1521/xe", "system", "1234");
			System.out.println("DB 연결 성공");
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}

	}

	public String joinform() {
		String sql = "select max(CUSTNO)+1 as custno from MEMBER_TBL_02";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) { // custno //100007
				int custno = rs.getInt("custno");
				// int custno=rs.getInt("1");
				System.out.println("custno=" + custno);
				req.setAttribute("custno", custno);
			}
		} catch (SQLException e) {
			System.out.println("dao joinform 예외");
			e.printStackTrace();
		}
		return "joinform.jsp";
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String memberjoin() {
		String sql = "insert into member_tbl_02 values(?,?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(req.getParameter("custno")));
			stmt.setString(2, req.getParameter("custname"));
			stmt.setString(3, req.getParameter("phone"));
			stmt.setString(4, req.getParameter("address"));
			stmt.setString(5, req.getParameter("joindate"));
			stmt.setString(6, req.getParameter("grade"));
			stmt.setString(7, req.getParameter("city"));
			stmt.executeUpdate();
			req.setAttribute("msg", "회원등록이 성공되었습니다.");
			// int result = stmt.executeUpdate();
			// joinFrm.jsp 사전에 성공 메시지 출력한 경우 생략
//			if(result!=0) {
//				System.out.println("회원등록 성공");
//				req.getSession().setAttribute("msg", "회원등록이 성공되었습니다.");
//			}else {
//				System.out.println("회원등록 성공");
//			}
		} catch (SQLException e) {
			System.out.println("sDao memberjoin 예외");
			e.printStackTrace();
		}
		return "./joinform"; // db에 다시가려면 컨트롤러에 요청
	}

	public String memberlist() {
		ArrayList<HashMap<String, Object>> mList = new ArrayList<>();
		String sql = "select * from member_tbl_02";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) { // 내일 MemberDto mb=new MemberDto();
				HashMap<String, Object> map = new HashMap<>();
				map.put("custno", rs.getInt("custno"));
				map.put("custname", rs.getString("custname"));
				map.put("phone", rs.getString("phone"));
				map.put("address", rs.getString("address"));
				map.put("joindate", rs.getDate("joindate"));
				map.put("city", rs.getString("city"));
				// map.put("grade", rs.getString("grade"));
				if (rs.getString("grade").equals("A")) {
					map.put("grade", "VIP");
				} else if (rs.getString("grade").equals("B")) {
					map.put("grade", "일반");
				} else {
					map.put("grade", "직원");
				}
				mList.add(map);
			}
			req.setAttribute("mList", mList); // jstl
			// req.setAttribute("mListHtml", makelistHtml()); //
		} catch (SQLException e) {
			System.out.println("mList dao 예외");
			e.printStackTrace();
		}
		return "memberlist.jsp";

	}

	public String updateform() {
		String sql = "select * from member_tbl_02 where custno=?";
		int custno = Integer.parseInt(req.getParameter("custno"));
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, custno);
			rs = stmt.executeQuery();
			if (rs.next()) { // 내일 MemberDto mb=new MemberDto();
				HashMap<String, Object> map = new HashMap<>();
				map.put("custno", rs.getInt("custno"));
				map.put("custname", rs.getString("custname"));
				map.put("phone", rs.getString("phone"));
				map.put("address", rs.getString("address"));
				map.put("joindate", rs.getDate("joindate")); // YYYY년MM월 , to_char로 변환
				map.put("city", rs.getString("city"));
				map.put("grade", rs.getString("grade"));
				req.setAttribute("member", map); // jstl foreach???
			}
		} catch (SQLException e) {
			System.out.println("sDao updateform 예외");
			e.printStackTrace();
		}
		return "updateform.jsp";
	}

	public String memberupdate() {
		// text block """~~~~~"""
		String sql = """
				update member_tbl_02
				set custname=?,phone=?,address=?,joindate=?,grade=?,city=?
				where custno=?
				""";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, req.getParameter("custname"));
			stmt.setString(2, req.getParameter("phone"));
			stmt.setString(3, req.getParameter("address"));
			stmt.setString(4, req.getParameter("joindate"));
			stmt.setString(5, req.getParameter("grade"));
			stmt.setString(6, req.getParameter("city"));
			stmt.setString(7, req.getParameter("custno"));
			stmt.executeUpdate();
			// 회원수정 성공 메세지 jsp에서 출력해서 이하 생략
		} catch (SQLException e) {
			System.out.println("sDao memberUpdate 예외");
			e.printStackTrace();
		}
		return "./updateform?custno=" + req.getParameter("custno");
	}
	public String ranking() {
	String sql="""
			SELECT MB.CUSTNO,MB.CUSTNAME,MB.GRADE, to_char(SUM(MN.AMOUNT*MN.PCOST),'L999,999,999') TOTAL
			FROM MEMBER_TBL_02 MB
			JOIN MONEY_TBL_02 MN
			ON MB.CUSTNO=MN.CUSTNO
			GROUP BY MB.CUSTNO,MB.CUSTNAME,MB.GRADE
			ORDER BY TOTAL DESC
			""" ;
	try {
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		ArrayList<HashMap<String,String>> salesList=new ArrayList<>();
		while(rs.next()) {
			HashMap<String,String> mb=new HashMap<>();
			mb.put("custno", Integer.toString(rs.getInt("custno")));
			mb.put("custname",rs.getString("custname"));
						
			if (rs.getString("grade").equals("A")) {
				mb.put("grade", "VIP");
			} else if (rs.getString("grade").equals("B")) {
				mb.put("grade","일반");
			} else {
				mb.put("grade","직원");
			}
			mb.put("total",rs.getString("total"));
			salesList.add(mb);
		}
		req.setAttribute("salesList", salesList);  //foreach
	} catch (SQLException e) {
		System.out.println("dao ranking 예외");
		e.printStackTrace();
	}
	return "./ranking.jsp";
			

	}

}
