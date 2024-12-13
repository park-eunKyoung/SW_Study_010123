package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class shopDao {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	HttpServletRequest req;
	HttpServletResponse resp;
	
	public shopDao(HttpServletRequest req,HttpServletResponse resp) {
		this.req=req;
		this.resp=resp;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩실패");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//192.168.0.41/xe","system","1234");
			System.out.println("db 연결성공");
		} catch (SQLException e) {
			System.out.println("db 연결 실패");
			e.printStackTrace();
		}
	}
	public String joinform() {
		String sql = "select max(custno)+1 as custno from member_01";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				int custno = rs.getInt("custno");
				System.out.println("custno="+custno);
				req.setAttribute("custno", custno);
			}
			
		} catch (SQLException e) {
			System.out.println("sDao joinform 예외");
			e.printStackTrace();
		}
		
		return "./joinform.jsp";
	}
	public String memberjoin() {
		String sql = "insert into member_01 values (?,?,?,?,?,?,?)";
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(req.getParameter("custno")));
			stmt.setString(2, req.getParameter("custname"));
			stmt.setString(3, req.getParameter("phone"));
			stmt.setString(4, req.getParameter("address"));
			stmt.setString(5, req.getParameter("joindate"));
			stmt.setString(6, req.getParameter("grade"));
			stmt.setString(7, req.getParameter("city"));
			stmt.executeUpdate();
			req.setAttribute("msg", "회원 등록이 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("sDao memberjoin 예외");
			e.printStackTrace();
		}
		return "./joinform";
	}
	public String memberlist() {
		String sql = "select * from member_01";
		ArrayList <HashMap<String,Object>> mList = new ArrayList<>();
		try {
			stmt = con.prepareStatement(sql);
			rs =stmt.executeQuery();
			while(rs.next()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("custno", rs.getInt("custno"));
			map.put("custname",rs.getString("custname"));
			map.put("phone",rs.getString("phone"));
			map.put("address",rs.getString("address"));
			map.put("joindate",rs.getDate("joindate"));
			map.put("city",rs.getString("city"));
//			map.put("grade",rs.getString("grade"));
			
			if(rs.getString("grade").equals("A")) {
				map.put("grade","VIP");
			}else if(rs.getString("grade").equals("B")) {
				map.put("grade", "일반");
			}else {
				map.put("grade", "직원");
			}
			mList.add(map);
			}
			req.setAttribute("mList", mList);
		} catch (SQLException e) {
			System.out.println("mList 예외");
			e.printStackTrace();
		}
		return "memberlist.jsp";
	}
	
	public String updateform(){
		String sql = "select * from member_01 where custno=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, req.getParameter("custno"));
			rs=stmt.executeQuery();
			if(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("custno", rs.getInt("custno"));
				map.put("custname", rs.getString("custname"));
				map.put("phone", rs.getString("phone"));
				map.put("address", rs.getString("address"));
				map.put("joindate", rs.getDate("joindate").toString());
				map.put("grade", rs.getString("grade"));
				map.put("city", rs.getString("city"));
				req.setAttribute("member", map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return "updateform.jsp";
	}
	
	public String update() {
		String sql = """
				update member_01 
				set custname=?, phone=?, address=?, joindate=?, grade=?, city=?
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
			req.setAttribute("msg", "회원 정보 수정 완");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "updateform?custno="+req.getParameter("custno");
	}
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
