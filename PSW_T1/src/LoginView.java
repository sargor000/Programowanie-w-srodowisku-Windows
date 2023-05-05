import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class LoginView extends JFrame implements ActionListener {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordBox;
    private JButton loginButton;
    private int loginAttempts = 0; //licznik błędnych prób logowania
    private final int MAX_LOGIN_ATTEMPTS = 3; //maksymalna liczba błędnych prób logowania

    private Connection conn;

    public LoginView(Connection c) {

        this.conn = c;

        // Ustawienie tytułu okna
        setTitle("Logowanie do wydarzenia");

        // Ustawienie rozmiaru okna
        setSize(300, 150);

        // Tworzenie kontenera dla widoku
        Container container = getContentPane();

        // Ustawienie layoutu kontenera
        container.setLayout(new GridLayout(3, 2));

        // Tworzenie etykiety i pola tekstowego dla loginu
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        container.add(loginLabel);
        container.add(loginField);

        // Tworzenie etykiety i pola tekstowego typu hasło
        JLabel passwordLabel = new JLabel("Hasło:");
        passwordField = new JPasswordField();
        container.add(passwordLabel);
        container.add(passwordField);

        // Tworzenie checkboxa pozwalającego na odsłonięcie hasła
        showPasswordBox = new JCheckBox("Pokaż hasło");
        showPasswordBox.addActionListener(this);
        container.add(showPasswordBox);

        // Tworzenie przycisku logowania
        loginButton = new JButton("Zaloguj");
        loginButton.addActionListener(this);
        container.add(loginButton);

        // Ustawienie widoczności okna
        setVisible(true);
    }

    // Obsługa akcji dla checkboxa i przycisku
    public void actionPerformed(ActionEvent e) {
        Authentication auth = new Authentication(this.conn);
        String login = null;

        String password = null;
        if (e.getSource() == loginButton) {
            // Pobranie wartości loginu i hasła
            login = loginField.getText();
            password = new String(passwordField.getPassword());

            // Wywołanie metody autoryzującej z klasy Authentication
            int id_konta = auth.authenticate(login, password);
//            boolean thisIsAdmin = auth.authenticateadmin(login);

            // Sprawdzenie wyniku autoryzacji i wyświetlenie odpowiedniego komunikatu
            if (id_konta != -1) {
                String checkPermissions = auth.authenticateWhatYoursPermissions(login);

                // Komunikat o poprawnym zalogowaniu
                JOptionPane.showMessageDialog(this, "Poprawne zalogowanie!");
                // Sprawdzanie uprawnień użytkownika i wyświetlenie odpowiedniego widoku
//                 if (thisIsAdmin == true) {
//                    JOptionPane.showMessageDialog(this, "Witaj Admin!");

//                 }  else if  (thisIsAdmin == false) {
//                    JOptionPane.showMessageDialog(this, "Witaj Użytkowniku!");
                 if (checkPermissions.equals("admin")){
                     JOptionPane.showMessageDialog(this, "Witaj Admin!");
                     Panel adminPanel = new Panel(this.conn);
                     adminPanel.setVisible(true);

                 } else if (checkPermissions.equals("user")){
                    JOptionPane.showMessageDialog(this, "Witaj Użytkowniku!");
                     EventRegistrationForm registrationForm = new EventRegistrationForm(this.conn, id_konta);
                     registrationForm.setVisible(true);

                 } else {
                    JOptionPane.showMessageDialog(this, "Nie masz uprawnień!!!");
                 }

            }

            //Błędne zalogowanie
            else {
                // Zwiększenie licznika błędnych prób logowania
                loginAttempts++;

                // Sprawdzenie, czy osiągnięto maksymalną liczbę błędnych prób
                if (loginAttempts == MAX_LOGIN_ATTEMPTS) {
                    JOptionPane.showMessageDialog(this, "Osiągnięto maksymalną liczbę błędnych prób logowania. Próby logowania zostały zablokowane.");
                    loginButton.setEnabled(false);
                    return;
                }

                // Wyświetlenie odpowiedniego komunikatu
                JOptionPane.showMessageDialog(this, "Niepoprawny login lub hasło. Pozostało prób: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));

                // Czyszczenie pól tekstowych
                loginField.setText("");
                passwordField.setText("");
            }

        }
        //Pokaz hasło
        else if (e.getSource() == showPasswordBox) {
            if (showPasswordBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }

    }
}
