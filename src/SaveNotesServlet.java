import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveNotesServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/writer");
		PrintWriter pw = response.getWriter();
		String notes = request.getParameter("notes");
		String tableName = request.getParameter("tableName");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes","root","");
			PreparedStatement pst = con.prepareStatement("INSERT INTO `"+tableName+"` (note,date,time) values(?,DATE_FORMAT(CURDATE(),'%a %d-%b-%y'),TIME_FORMAT(CURTIME(),'%h:%i %p'))");
			pst.setString(1,notes);
			pst.executeUpdate();
			//getting the notes with new one
			pst = con.prepareStatement("SELECT * FROM `"+tableName+"`");
			ResultSet rst = pst.executeQuery();
			NotesBean bean = null;
			List<NotesBean> list = new ArrayList<>();
			while(rst.next()){
				bean = new NotesBean();
				bean.setNotes(rst.getString("note"));
				list.add(bean);
			}
			request.setAttribute("notes", list);
			RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
			rd.forward(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
