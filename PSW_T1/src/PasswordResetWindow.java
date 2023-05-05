import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PasswordResetWindow extends JFrame {
    private Connection conn;
    private JComboBox<String> userComboBox;
    private JTextField passwordTextField;

    public PasswordResetWindow(Connection c) {
        this.conn = c;
        try {
            // Pobranie listy użytkowników z bazy danych
            String query = "SELECT login FROM logowanie";
            Statement statement = this.conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Dodanie użytkowników do ComboBoxa
            this.userComboBox = new JComboBox();
            while (resultSet.next()) {
                String username = resultSet.getString("login");
                this.userComboBox.addItem(username);
            }
            this.setLayout(new GridLayout(3,2));
            this.add(new JLabel("Wybierz użytkownika:"));
            this.add(this.userComboBox);

            // Pole tekstowe do wprowadzania nowego hasła
            this.passwordTextField = new JTextField(20);
            this.add(new JLabel("Nowe hasło:"));
            this.add(this.passwordTextField);

            // Dodanie przycisku do resetowania hasła
            JButton resetButton = new JButton("Resetuj hasło");
            resetButton.addActionListener(e -> {
                String selectedUser = (String) this.userComboBox.getSelectedItem();
                String newPassword = this.passwordTextField.getText();
                try {
                    // Aktualizacja hasła użytkownika w bazie danych
                    String updateQuery = "UPDATE logowanie SET haslo = ? WHERE login = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newPassword);
                    preparedStatement.setString(2, selectedUser);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Hasło użytkownika " + selectedUser + " zostało zresetowane.");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            this.add(resetButton);

            this.pack();
            this.setVisible(true);
        } catch (SQLException var11) {
            var11.printStackTrace();
        }
    }
}
