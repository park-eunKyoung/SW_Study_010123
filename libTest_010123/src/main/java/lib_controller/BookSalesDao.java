package lib_controller;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookSalesDao {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;

	HttpServletRequest req;
	HttpServletResponse resp;

	public BookSalesDao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("db 드라이버 연동실패");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.41/xe", "system", "1234");
			System.out.println("db 연결성공");
		} catch (SQLException e) {
			System.out.println("db 연결실패");
			e.printStackTrace();
		}
	}

	public String salesjoin() {
		String sql = "insert into salelist values(?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(req.getParameter("saleno")));
			stmt.setString(2, req.getParameter("saledate"));
			stmt.setInt(3, Integer.parseInt(req.getParameter("amount")));
			stmt.setString(4, req.getParameter("bcode"));
			stmt.executeUpdate();
			req.setAttribute("msg", "도서매출등록이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "./salesjoinform";
	}

	
	public String salesjoinform() {
		String sql = "select coalesce(max(saleno),100000)+1 as saleno from salelist";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int saleno = rs.getInt("saleno");
				req.setAttribute("saleno", saleno);
			}

		} catch (SQLException e) {
			System.out.println("sDao joinform 예외");
			e.printStackTrace();
		}

		sql = "select bcode, bname from book";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String, String>> bList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> bmap = new HashMap<>();
				bmap.put("bcode", rs.getString("bcode"));
				bmap.put("bname", rs.getString("bname"));
				bList.add(bmap);
			}
			req.setAttribute("bList", bList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "./salesjoinform.jsp";
	}

	public String totalsales() {
		String sql = """
				select salelist.saleno ,cb.comcode, cb.comname,salelist.saledate,cb.bcode ,cb.bname,salelist.amount ,cb.bcost * salelist.amount total
				from salelist
				join
				(select company.comcode, company.comname, book.bcode, book.bname, book.bcost
				from book
				join company
				on company.comcode=book.comcode) cb
				on cb.bcode=salelist.bcode
				order by saleno asc
					""";
		try {
			stmt= con.prepareStatement(sql);
			rs=stmt.executeQuery();
			ArrayList<HashMap<String,String>> tList = new ArrayList<>();
			while(rs.next()) {
			HashMap<String,String> tmap = new HashMap<>();
			tmap.put("saleno", rs.getString("saleno"));
			tmap.put("comcodename", rs.getString("comcode")+"-"+rs.getString("comname")) ;
			tmap.put("saledate", rs.getDate("saledate").toString());
			tmap.put("bcode", rs.getString("bcode"));
			tmap.put("bname", rs.getString("bname"));
			tmap.put("amount", rs.getString("amount"));
			tmap.put("total", rs.getString("total"));
			tList.add(tmap);
			}
			req.setAttribute("tList", tList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "totalsalesform.jsp";
	}

	
	public String companysalse() {
		String sql = """
				  select cb.comcode, cb.comname, to_char(sum(cb.bcost * salelist.amount), 'L999,999,999') as total
				            from salelist
				            join
				                (select company.comcode, company.comname, book.bcode, book.bcost
				                from book
				                join company
				                on company.comcode=book.comcode) cb
				            on cb.bcode=salelist.bcode
				            group by cb.comcode, cb.comname
				            order by cb.comcode asc
				""";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String, String>> cList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> cmap = new HashMap<>();
				cmap.put("comcode", rs.getString("comcode"));
				cmap.put("comname", rs.getString("comname"));
				cmap.put("total", rs.getString("total"));
				cList.add(cmap);
			}
			req.setAttribute("cList", cList);
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return "/company_total.jsp";
	}

	
	
	public String booksales() {
		String sql = """
				select book.bcode, book.bname, to_char(sum(salelist.amount*book.bcost),'L999,999,999') as total
				from book join salelist on book.bcode=salelist.bcode group by
				book.bcode,book.bname order by total desc
				""";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<HashMap<String, String>> bList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> bmap = new HashMap<>();
				bmap.put("bcode", rs.getString("bcode"));
				bmap.put("bname", rs.getString("bname"));
				bmap.put("total", rs.getString("total"));
				bList.add(bmap);
			}
			req.setAttribute("bList", bList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "booksalesform.jsp";
	}

}
