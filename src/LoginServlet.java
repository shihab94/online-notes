import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		response.setContentType("text/writer");
		//PrintWriter pw = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//database connection
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes","root","");
			PreparedStatement pst = con.prepareStatement("SELECT * FROM usersInfo WHERE userName=? and password=?");
			pst.setString(1,userName);
			pst.setString(2,password);
			ResultSet rst = pst.executeQuery();

			if(rst.next()){
				String id = String.valueOf(rst.getInt("id"));
				HttpSession session = request.getSession(true);
				session.setAttribute("user",id);
				int count = 0;
				List<NotesBean> list = new ArrayList<>();
				NotesBean bean = null;
				pst = con.prepareStatement("SELECT * FROM `"+id+"`");
				rst = pst.executeQuery();
				//getting the notes from database
				while(rst.next() && count != 4){
					bean = new NotesBean();
					bean.setNotes(rst.getString("note"));
					list.add(bean);
					count++;
				}
				request.setAttribute("notes", list);
				RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
				rd.forward(request,response);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}