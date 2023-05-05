import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class LoginOrRegisterView extends JFrame implements ActionListener {
    private JButton loginButton;
    private JButton registerButton;

    private Connection conn;

    public LoginOrRegisterView(Connection c) {

        this.conn = c;

        // Ustawienie tytułu okna
        setTitle("Wybierz opcję");

        // Ustawienie rozmiaru okna
        setSize(300, 150);

        // Tworzenie kontenera dla widoku
        Container container = getContentPane();

        // Ustawienie layoutu kontenera
        container.setLayout(new GridLayout(2, 1));

        // Tworzenie przycisku logowania
        loginButton = new JButton("Logowanie");
        loginButton.addActionListener(this);
        container.add(loginButton);

        // Tworzenie przycisku rejestracji
        registerButton = new JButton("Rejestracja");
        registerButton.addActionListener(this);
        container.add(registerButton);

        // Ustawienie widoczności okna
        setVisible(true);
    }

    // Obsługa akcji dla przycisków
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Zamknięcie okna wyboru opcji
            dispose();

            // Uruchomienie widoku logowania
            new LoginView(this.conn);
        } else if (e.getSource() == registerButton) {
            // Zamknięcie okna wyboru opcji
            dispose();

            // Uruchomienie widoku rejestracji
            new RegistrationView(this.conn);
        }
    }
}
