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

public class ViewNotesServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/writer");
		PrintWriter pw = response.getWriter();
		String notes = request.getParameter("notes");
		String tableName = request.getParameter("tableName");
		String selectedNotes = null,row = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes","root","");
			//getting the notes with new one
			System.out.println("tableName is: "+tableName);
			PreparedStatement pst = con.prepareStatement("SELECT * FROM `"+tableName+"`");
			ResultSet rst = pst.executeQuery();
			NotesBean bean = null;
			List<NotesBean> list = new ArrayList<>();
			while(rst.next()){
				bean = new NotesBean();
				bean.setNotes(rst.getString("note"));
				//setting selected notes
				if(rst.getString("note").contains(notes)){
					selectedNotes = rst.getString("note");
					row = rst.getString("id");
					System.out.println("View Note servlet row is "+row);
				}
				list.add(bean);
			}
			request.setAttribute("notes", list);
			System.out.println("selectedNotes is "+selectedNotes);
			request.setAttribute("rowNo", row);
			request.setAttribute("notesForTextarea", selectedNotes);
			RequestDispatcher rd = request.getRequestDispatcher("viewNotes.jsp");
			rd.forward(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
