package smart_controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmartDao {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;

	HttpServletRequest req;
	HttpServletResponse resp;

	public SmartDao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("db 드라이버 연동 실패");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.41/xe", "system", "1234");
			System.out.println("db 연결 성공");
		} catch (SQLException e) {
			System.out.println("db연결 실패");
			e.printStackTrace();
		}
	}

	public String productlist() {
		String sql = "select * from product_tbl";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String, String>> pList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> pmap = new HashMap<>();
				pmap.put("p_code", rs.getString("p_code"));
				pmap.put("p_name", rs.getString("p_name"));
				pmap.put("p_size", rs.getString("p_size"));
				pmap.put("p_type", rs.getString("p_type"));
				pmap.put("p_price", rs.getString("p_price"));
				pList.add(pmap);
			}
			req.setAttribute("pList", pList);
		} catch (SQLException e) {
			System.out.println("pl 예외");
			e.printStackTrace();
		}

		return "/productlist.jsp";
	}

	public String processinsert() {
		String sql = "insert into process_tbl values (?,?,?,?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, req.getParameter("w_workno"));
			stmt.setString(2, req.getParameter("p_p1"));
			stmt.setString(3, req.getParameter("p_p2"));
			stmt.setString(4, req.getParameter("p_p3"));
			stmt.setString(5, req.getParameter("p_p4"));
			stmt.setString(6, req.getParameter("p_p5"));
			stmt.setString(7, req.getParameter("p_p6"));
			stmt.setString(8, req.getParameter("w_lastdate"));
			stmt.setString(9, req.getParameter("w_lasttime"));
			stmt.executeUpdate();
			
			req.setAttribute("msg", "작업공정 등록(수정)이 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("psinsert 예외");
			e.printStackTrace();
		}
		return "/processinsertform.jsp";
	}
	
	public String workupdate() {
		System.out.println(req.getParameter("w_workno"));
		String sql = "delete process_tbl where w_workno = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, req.getParameter("w_workno"));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return processinsert();
	}

	public String worklist() {
		String sql = """
				select worklist_tbl.w_workno, product_tbl.p_code, product_tbl.p_name,product_tbl.p_size, product_tbl.p_type, worklist_tbl.w_quantity, worklist_tbl.w_workdate
				from product_tbl 
				join worklist_tbl
				on worklist_tbl.p_code=product_tbl.p_code
				""";
		try {
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			ArrayList<HashMap<String,String>> pList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> pmap = new HashMap<>();
				pmap.put("w_workno", rs.getString("w_workno"));
				pmap.put("p_code", rs.getString("p_code"));
				pmap.put("p_name", rs.getString("p_name"));
				pmap.put("p_size", rs.getString("p_size"));
				pmap.put("p_type", rs.getString("p_type"));
				pmap.put("w_quantity", rs.getString("w_quantity"));
				pmap.put("w_workdate", rs.getString("w_workdate"));
				pList.add(pmap);
			}
			req.setAttribute("pList", pList);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return "/worklistform.jsp";
	}

	public String workprocess() {
		String sql = """
				 select wp.w_workno, wp.p_code, wp.p_name,process_tbl.p_p1,
				process_tbl.p_p2,process_tbl.p_p3,process_tbl.p_p4,process_tbl.p_p5,process_tbl.p_p6,wp.w_workdate,process_tbl.w_lasttime
				from process_tbl
                join 
				(select worklist_tbl.w_workno, product_tbl.p_code, product_tbl.p_name,product_tbl.p_size, product_tbl.p_type, 
				worklist_tbl.w_quantity, worklist_tbl.w_workdate
				from product_tbl 
				join worklist_tbl
				on worklist_tbl.p_code=product_tbl.p_code) wp
				on process_tbl.w_workno=wp.w_workno	
				""";
		try {
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			ArrayList<HashMap<String,String>> pList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> pmap = new HashMap<>();
				pmap.put("w_workno", rs.getString("w_workno"));
				pmap.put("p_code", rs.getString("p_code"));
				pmap.put("p_name", rs.getString("p_name"));
				pmap.put("p_p1", rs.getString("p_p1"));
				pmap.put("p_p2", rs.getString("p_p2"));
				pmap.put("p_p3", rs.getString("p_p3"));
				pmap.put("p_p4", rs.getString("p_p4"));
				pmap.put("p_p5", rs.getString("p_p5"));
				pmap.put("p_p6", rs.getString("p_p6"));
				pmap.put("w_workdate", rs.getString("w_workdate"));
				pmap.put("w_lasttime", rs.getString("w_lasttime"));
				pList.add(pmap);
			}
			req.setAttribute("pList", pList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/workprocessform.jsp";
	}

}
