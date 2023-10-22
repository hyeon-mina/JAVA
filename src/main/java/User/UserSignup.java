package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserSignup {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3307/movie_ticket_booking";
        String dbUser = "your_database_username";
        String dbPassword = "your_database_password";

        String username = "사용자 이름";
        String userID = "사용자 아이디";
        String password = "사용자 비밀번호";
        String email = "이메일 주소";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String sql = "INSERT INTO users (username, userID, password, email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userID);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("회원 가입이 성공적으로 완료되었습니다.");
            } else {
                System.out.println("회원 가입에 실패하였습니다.");
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
