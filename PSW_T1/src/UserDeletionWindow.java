import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;

public class UserDeletionWindow extends JFrame {
    private Connection conn;
    private JComboBox<User> userComboBox;

    public UserDeletionWindow(Connection c) {
        this.conn = c;

        try {

            Statement statement = this.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM logowanie");
            ArrayList<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id_konta");
                String username = resultSet.getString("login");
                String password = resultSet.getString("haslo");
                User user = new User(userId, username, password);
                userList.add(user);
            }

            this.userComboBox = new JComboBox<>(userList.toArray(new User[0]));
            this.add(this.userComboBox);

            // Dodanie przycisku
            JButton deleteButton = new JButton("Usuń użytkownika");
            deleteButton.addActionListener(e -> {
                User selectedUser = (User) userComboBox.getSelectedItem();
                int userId = selectedUser.getUserId();
                String query2 = "DELETE FROM logowanie WHERE id_konta = ?";
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(query2);
                    preparedStatement.setInt(1, userId);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Usunięto użytkownika: " + selectedUser.getUsername());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            this.add(deleteButton);

            // Konfiguracja okna
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLayout(new FlowLayout());
            this.setTitle("Usuwanie użytkownika");
            this.setSize(400, 100);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static class User {
        private int userId;
        private String username;
        private String password;

        public User(int userId, String username, String password) {
            this.userId = userId;
            this.username = username;
            this.password = password;
        }

        public int getUserId() {
            return this.userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String toString() {
            return this.username;
        }
    }
}
