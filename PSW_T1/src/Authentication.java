import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public Authentication(Connection c) {
        this.connection = c;
    }
    // Sprawdź uprawnienia dla podanego loginu, czy jest adminem
    public String authenticateWhatYoursPermissions (String login) {
        String role = null;
        try {
            String query = "SELECT uprawnienia FROM logowanie WHERE login = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("uprawnienia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    //Sprawdz login i haslo w bazie danych
    public int authenticate(String login, String password) {
        try {
            String query = "SELECT id_konta FROM logowanie WHERE login=? AND haslo=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_konta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
