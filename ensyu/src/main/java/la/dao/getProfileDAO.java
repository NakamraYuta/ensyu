package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.ProfileBean;

public class getProfileDAO {
	String url = "jdbc:postgresql:sample";
	String user = "student";
	String pass = "himitu";
	
	String sql = "select * from profile where email = ? and password =?";
	
	public ProfileBean getProfile(ProfileBean person) throws DAOException{
		String sql = "select * from profile where email = ? and password =?";
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました");
		}
		
		try(
			Connection con= DriverManager.getConnection(url, user, pass);
			PreparedStatement st = con.prepareStatement(sql);){
			st.setString(1, person.getName());
			st.setString(2, person.getPassword());
			
			try(ResultSet rs = st.executeQuery();){
				
				if (rs.next()){
					int user_id = rs.getInt("user_id");
					String name = rs.getString("name");
					person.setUser_id(user_id);
					person.setPassword(name);
				}
				return person;
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
}
