package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LocalDao {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	HttpServletRequest req;
	HttpServletResponse resp;
	public LocalDao(HttpServletRequest req, HttpServletResponse resp) {
		this.req=req;
		this.resp=resp;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("db 드라이버 연동 실패");
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.41:1521/xe", "system", "1234");
			System.out.println("db 연결 성공");
		} catch (SQLException e) {
			System.out.println("db 연결 실패");
			
			e.printStackTrace();
		}
	}
	public String memberlist() {
		String sql = """
				select m.m_no, m.m_name, p.p_name, m.p_school, m.m_jumin, m.m_city, p.p_tel1, p.p_tel2, p.p_tel3
				from tbl_member m
				join tbl_party p
				on m.p_code=p.p_code
				""";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String,Object>> mList = new ArrayList<>();
			while(rs.next()) {
				String tmp = rs.getString("m_jumin");
				HashMap<String,Object> mmap = new HashMap<>();
				mmap.put("m_no", rs.getString("m_no"));
				mmap.put("m_name", rs.getString("m_name"));
				mmap.put("p_name", rs.getString("p_name"));
				mmap.put("p_school", rs.getString("p_school"));
				mmap.put("m_jumin", tmp.substring(0,6)+"-"+tmp.substring(6,13));
				mmap.put("m_city", rs.getString("m_city"));
				mmap.put("p_tel", rs.getString("p_tel1")+"-"+rs.getString("p_tel2")+"-"+rs.getString("p_tel3"));
				
				if(rs.getString("p_school").equals("1")) {
					mmap.put("p_school", "고졸");
				}else if(rs.getString("p_school").equals("2")){
					mmap.put("p_school", "학사");
				}else if(rs.getString("p_school").equals("3")){
					mmap.put("p_school", "석사");
				}else {
					mmap.put("p_school", "박사");
				}
				mList.add(mmap);
			}
			req.setAttribute("mList", mList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "memberlist.jsp";
	}
	public String insertvote() {
		String sql = "insert into tbl_vote values (?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,req.getParameter("v_jumin"));
			stmt.setString(2,req.getParameter("v_name"));
			stmt.setString(3,req.getParameter("m_no"));
			stmt.setString(4,req.getParameter("v_time"));
			stmt.setString(5,req.getParameter("v_area"));
			stmt.setString(6,req.getParameter("v_confirm"));
			stmt.executeUpdate();
			req.setAttribute("msg", " 투표하기정보가 정상적으로 등록되었습니다!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/index.jsp";
	}
	public String insertvoteform() {
		String sql = "select m_no, m_name from tbl_member";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String, String>> vList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String,String> vmap = new HashMap<>();
				vmap.put("m_no", rs.getString("m_no"));
				vmap.put("m_name", rs.getString("m_name"));
				vList.add(vmap);
			}
			req.setAttribute("vList", vList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/insertvoteform.jsp";
	}
	public String vokelist() {
		String sql = "select * from tbl_vote";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String, Object>> vList = new ArrayList<>();
			while (rs.next()) {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			String tmp = rs.getString("v_jumin");
			String time = rs.getString("v_time");
			HashMap<String, Object> vmap = new HashMap<>();
			vmap.put("v_name",rs.getString("v_name"));
			vmap.put("v_jumin","19"+tmp.substring(0,2)+"년"+tmp.substring(2,4)+"월"+tmp.substring(4,6)+"일");
	        int birthYear = Integer.parseInt("19"+tmp.substring(0, 2));
	        int age = year - birthYear;
			vmap.put("v_age","만"+String.valueOf(age)+"세");
			int n = Integer.parseInt(tmp.substring(7,8));
			String gender=null;
			if(n%2==0) {
				gender = "여자";
			}else {
				gender="남자";
			}
			vmap.put("v_gender",gender);
			vmap.put("m_no",rs.getString("m_no"));
			vmap.put("v_time",time.substring(0,2)+":"+time.substring(2,4));
			vmap.put("v_confirm",rs.getString("v_confirm"));
			if(rs.getString("v_confirm").equals("Y")) {
				vmap.put("v_confirm", "확인");
			}else {
				vmap.put("v_confirm", "미확인");
			}
			vList.add(vmap);
			}
			req.setAttribute("vList", vList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/vokelist.jsp";
	}

	

}
