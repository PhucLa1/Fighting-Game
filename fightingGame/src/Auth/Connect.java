package Auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect {
	private static Connection conn;
	
	public Connect(){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taikhoan", "root", "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Ket noi voi database ma minh da tao tu truoc do

	}
	
	//Kiem tra dang nhap
	public static boolean returnPlayer(String user,String pass) {
		boolean ok=false;  //Ban dau mac dinh rang no khong co tai khoan nay
		try {
            // Loading database ma JDBC 


            // Doan nay la doan ma SQL chuan bi cho viec ma nhan du lieu tu ben ngoai vao
            String sql = "SELECT * FROM player WHERE user = ? and pass = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Dau ? dau tien la user, dau ? thu 2 la bien pass
            stmt.setString(1, user);
            stmt.setString(2, pass);

            // Execute the query
            ResultSet rs = stmt.executeQuery();
            
            // Ket qua
            while (rs.next()) { //Neu tim thay dong trong SQL tuc la tai khoan va mat khau do dung
            	ok = true;
            }
            // Close the database resources
            rs.close();
            stmt.close();
        } 
		catch (Exception e) {
            e.printStackTrace();
        }
		return ok;
	}
	
	public boolean SignUp(String user,String pass) {
		boolean res=false;
		String sql = "INSERT INTO `player` (`id`, `user`, `pass`, `trang_thai`) VALUES (NULL, ?, ?, '1');";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
	        stmt.setString(1, user);
	        stmt.setString(2, pass);
	        
	        
	        int rowsInserted = stmt.executeUpdate();
	        /*Đoạn mã này có nghĩa là nếu số lượng bản ghi bị 
	         * ảnh hưởng bởi câu lệnh SQL được thực thi là lớn hơn 0, 
	         * tức là ít nhất một bản ghi đã được chèn thành công vào bảng users 
	         * trong cơ sở dữ liệu. Khi đó, một thông báo được hiển thị trên console
	         *  cho biết "Dang ki thanh cong" - một người dùng mới đã được chèn thành công.

				Nếu rowsInserted bằng 0, nghĩa là không có bản ghi nào đượ
				c chèn vào bảng users, có thể do câu lệnh SQL hoặc kết nối cơ sở dữ liệu gặp vấn đề.
	         * */
            if (rowsInserted > 0) {
                res = true;
            }
	        
            stmt.close();
		}
		catch (Exception e) {
            System.out.println("Loi khi ma lap lai user 2 lan");
        }
		return res;
	}
	
}
