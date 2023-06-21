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
		
//		try(
//			Connection con= DriverManager.getConnection(url, user, pass);
//			PreparedStatement st = con.prepareStatement(sql);){
//			
//			String mail =	person.getEmail();
//			String pass = person.getPassword();
//			st.setString(1, mail);
//			st.setString(2, pass);
//			
//			try(ResultSet rs = st.executeQuery();){
//				
//				if (rs.next()){
//					int user_id = rs.getInt("user_id");
//					String name = rs.getString("name");
//					person.setUser_id(user_id);
//					person.setPassword(name);
//				}
//				return person;
		
		
		try (Connection connection = DriverManager.getConnection(url, user, pass);
				 PreparedStatement statement = connection.prepareStatement(sql);) {
				statement.setString(1, person.getEmail());
				statement.setString(2, person.getPassword());
				try (ResultSet resultSet = statement.executeQuery();) {
					if (resultSet.next()) {
						person.setUser_id(resultSet.getInt("user_id"));
						person.setName(resultSet.getString("name"));
						return person;
					} 
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		return person;
	}
	
}
