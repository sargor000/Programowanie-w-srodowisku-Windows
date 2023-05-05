import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        final String DB_URL = "jdbc:mysql://localhost/psw";
        final String USER = "root";
        final String PASS = "";

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected to the database");

                LoginOrRegisterView view = new LoginOrRegisterView(conn);
                view.setVisible(true);

                //conn.close();
            } catch (SQLException e) {
                System.out.println("Error connecting to the database");
                e.printStackTrace();
            }

        }
    }


