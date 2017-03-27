import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
public class SignupServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		response.setContentType("text/writer");
		PrintWriter pw = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		//database connection and query
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes","root","");
			//inserting data to usersInfo table
			PreparedStatement pst = con.prepareStatement("INSERT INTO usersInfo(userName,password,email) values(?,?,?)");
			pst.setString(1, userName);
			pst.setString(2, password);
			pst.setString(3, email);
			int insert = pst.executeUpdate();
			if(insert>0){
				//query for getting id information to create user table
				pst = con.prepareStatement("SELECT * FROM usersInfo WHERE userName=? AND password=?");
				pst.setString(1, userName);
				pst.setString(2, password);
				ResultSet rslt = pst.executeQuery();
				rslt.next();
				int id = rslt.getInt("id");
				//create table query
				pst = con.prepareStatement("CREATE TABLE `"+id+"` (id int(20) AUTO_INCREMENT,note varchar(200),date varchar(15),time varchar(15),PRIMARY KEY(id))");
				int rst = pst.executeUpdate();
				pw.print("Data Inserted and Table created Successfully");
				if(rst>0){
					System.out.println("inside rst>0");
					pw.print("Data Inserted and Table created Successfully");
				}
			}else{
				pw.print("Connection Error");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}