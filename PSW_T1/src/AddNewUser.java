import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewUser extends JFrame implements ActionListener {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private Connection conn;

    public AddNewUser(Connection c) {

        this.conn = c;

        // Ustawienie tytułu okna
        setTitle("Dodaj nowego użytkowanika");

        // Ustawienie rozmiaru okna
        setSize(400, 250);

        // Tworzenie kontenera dla widoku
        Container container = getContentPane();

        // Ustawienie layoutu kontenera
        container.setLayout(new GridLayout(7, 2));

        // Tworzenie etykiety i pola tekstowego dla imienia
        JLabel firstNameLabel = new JLabel("Imię:");
        firstNameField = new JTextField();
        container.add(firstNameLabel);
        container.add(firstNameField);

        // Tworzenie etykiety i pola tekstowego dla nazwiska
        JLabel lastNameLabel = new JLabel("Nazwisko:");
        lastNameField = new JTextField();
        container.add(lastNameLabel);
        container.add(lastNameField);

        // Tworzenie etykiety i pola tekstowego dla loginu
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        container.add(loginLabel);
        container.add(loginField);

        // Tworzenie etykiety i pola tekstowego dla hasła
        JLabel passwordLabel = new JLabel("Hasło:");
        passwordField = new JPasswordField();
        container.add(passwordLabel);
        container.add(passwordField);

        // Tworzenie etykiety i pola tekstowego dla powtórzenia hasła
        JLabel confirmPasswordLabel = new JLabel("Powtórz hasło:");
        confirmPasswordField = new JPasswordField();
        container.add(confirmPasswordLabel);
        container.add(confirmPasswordField);

        // Tworzenie etykiety i pola tekstowego dla adresu e-mail
        JLabel emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        container.add(emailLabel);
        container.add(emailField);

        // Tworzenie przycisku rejestracji
        registerButton = new JButton("Dodaj nowego użytkowanika");
        registerButton.addActionListener(this);
        container.add(registerButton);

        // Ustawienie widoczności okna
        setVisible(true);
    }

    // Obsługa akcji dla przycisku rejestracji
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {
            // Pobranie wartości pól tekstowych
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();

            // Sprawdzenie poprawności hasła
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Podane hasła nie są identyczne", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Zapisanie danych użytkownika do bazy danych
            try {
                // Połączenie z bazą danych
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/psw", "root", "");

                // Zapytanie SQL do wstawienia danych użytkownika
                String sql = "INSERT INTO `logowanie` (`imie`, `login`, `nazwisko`, `haslo`, `email`, `uprawnienia`) VALUES (?, ?, ?, ? ,? ,?)";

                // Przygotowanie zapytania SQL z wartościami parametrów
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, login);
                statement.setString(4, password);
                statement.setString(5, email);
                statement.setString(6, "user"); // dodanie parametru ustawiającego kolumnę uprawnień na wartość "user"

                // Wykonanie zapytania SQL
                statement.executeUpdate();

                // Zamknięcie połączenia z bazą danych
                conn.close();

                // Powiadomienie użytkownika o poprawnym dodaniu do bazy danych
                JOptionPane.showMessageDialog(this, "Użytkownik został zarejestrowany pomyślnie", "Sukces", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                // Wyświetlenie błędu w przypadku nieudanego połączenia lub wstawienia do bazy danych
                JOptionPane.showMessageDialog(this, "Błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}