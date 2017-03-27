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
public class NotesUpdateAndDelete extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/writer");
		String notes = request.getParameter("notes");
		String button = request.getParameter("button");
		String tableName = request.getParameter("tableName");
		String row = request.getParameter("row");
		String selectedNotes = null;
		int x = 0;
	    System.out.println("Service on NotesUpdateAndDelete");
	    System.out.println("Note is:"+notes+" button is :"+button+" table is: "+tableName+" row is :"+row);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes","root","");
			PreparedStatement pst = null;
			if(button.equals("Update")){
				pst = con.prepareStatement("UPDATE `"+tableName+"` SET note = ? WHERE id = ?");
				pst.setString(1, notes);
				pst.setString(2, row);
				x = pst.executeUpdate();
			}else{
				pst = con.prepareStatement("DELETE FROM `"+tableName+"` WHERE id = ?");
				pst.setString(1, row);
				x = pst.executeUpdate();
			}
			if(x>0){
				pst = con.prepareStatement("SELECT * FROM `"+tableName+"`");
				ResultSet rst = pst.executeQuery();
				NotesBean bean = null;
				List<NotesBean> list = new ArrayList<>();
				while(rst.next()){
					bean = new NotesBean();
					bean.setNotes(rst.getString("note"));
					list.add(bean);
					if(rst.getString("note").contains(notes)){
						selectedNotes = rst.getString("note");
						row = rst.getString("id");
						System.out.println("View Note servlet row is "+row);
					}
				}
				request.setAttribute("notes", list);
				request.setAttribute("rowNo", row);
				request.setAttribute("notesForTextarea", selectedNotes);
				if(button.equals("Update")){
					RequestDispatcher rd = request.getRequestDispatcher("viewNotes.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
