import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Panel extends JFrame {
    private JButton addUserButton, removeUserButton, resetPasswordButton, addEventButton, removeEventButton, modifyEventButton, confirmSignupButton, rejectSignupButton;
    private Connection conn;

    public Panel(Connection conn) {
        this.conn = conn;

        // Ustawienia okna
        setTitle("Panel");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Ustawienia sekcji i przycisków
        JPanel userSection = new JPanel();
        userSection.setBorder(BorderFactory.createTitledBorder("Obsługa użytkowników"));
        addUserButton = new JButton("Dodaj użytkownika");

        class addUserButtonListener implements ActionListener {
            private Connection conn;

            public addUserButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewUser addNewUserPrzezAdmin = new AddNewUser(this.conn);
                addNewUserPrzezAdmin.setVisible(true);
            }
        }

        addUserButtonListener add_user_button_listener = new addUserButtonListener(this.conn);
        addUserButton.addActionListener(add_user_button_listener);


        removeUserButton = new JButton("Usuń użytkownika");
        class removeUserButtonListener implements ActionListener {
            private Connection conn;

            public removeUserButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                UserDeletionWindow userDeletionWindowPrzezAdmin = new UserDeletionWindow(this.conn);
                userDeletionWindowPrzezAdmin.setVisible(true);
            }
        }

        removeUserButtonListener remove_user_button_listener = new removeUserButtonListener(this.conn);
        removeUserButton.addActionListener(remove_user_button_listener); // Dodajemy ActionListener do przycisku "Usuń użytkownika"

        resetPasswordButton = new JButton("Reset hasła");
        class resetPasswordButtonListener implements ActionListener {
            private Connection conn;

            public resetPasswordButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordResetWindow passwordResetWindowPrzezAdmin = new PasswordResetWindow(this.conn);
                passwordResetWindowPrzezAdmin.setVisible(true);
            }
        }

        resetPasswordButtonListener reset_password_button_listener = new resetPasswordButtonListener(this.conn);
        resetPasswordButton.addActionListener(reset_password_button_listener); // Dodajemy ActionListener do przycisku "Reset hasła"

        userSection.add(addUserButton);
        userSection.add(removeUserButton);
        userSection.add(resetPasswordButton);


        JPanel eventSection = new JPanel();
        eventSection.setBorder(BorderFactory.createTitledBorder("Obsługa wydarzeń"));

        addEventButton = new JButton("Dodaj wydarzenie");
        class addEventButtonListener implements ActionListener {
            private Connection conn;

            public addEventButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                AddEventWindow addEventWindowPrzezAdmin = new AddEventWindow(this.conn);
                addEventWindowPrzezAdmin.setVisible(true);
            }
        }

        addEventButtonListener add_event_button_listener = new addEventButtonListener(this.conn);
        addEventButton.addActionListener(add_event_button_listener); // Dodajemy ActionListener do przycisku "Dodaj wydarzenie"


        removeEventButton = new JButton("Usuń wydarzenie");
        class removeEventButtonListener implements ActionListener {
            private Connection conn;

            public removeEventButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveEvents removeEventsprzezAdmin = new RemoveEvents(this.conn);
                removeEventsprzezAdmin.setVisible(true);
            }
        }

        removeEventButtonListener remove_event_button_listener = new removeEventButtonListener(this.conn);
        removeEventButton.addActionListener(remove_event_button_listener); // Dodajemy ActionListener do przycisku "Dodaj wydarzenie"

        modifyEventButton = new JButton("Modyfikacja");
        class modifyEventButtonListener implements ActionListener {
            private Connection conn;

            public modifyEventButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyEventWindow modifyEventWindowPrzezAdmin = new ModifyEventWindow(this.conn);
                modifyEventWindowPrzezAdmin.setVisible(true);
            }
        }

        modifyEventButtonListener modify_event_button_listener = new modifyEventButtonListener(this.conn);
        modifyEventButton.addActionListener(modify_event_button_listener); // Dodajemy ActionListener do przycisku "Modyfikacja"

        eventSection.add(addEventButton);
        eventSection.add(removeEventButton);
        eventSection.add(modifyEventButton);

        JPanel signupSection = new JPanel();
        signupSection.setBorder(BorderFactory.createTitledBorder("Obsługa zapisów na wydarzenie"));


        confirmSignupButton = new JButton("Potwierdzenie i odrzucenie zapisu");
        class confirmSignupButtonListener implements ActionListener {

            Connection conn;

            public confirmSignupButtonListener(Connection conn) {
                this.conn = conn;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmSaveWindow confirmSaveWindowPrzezAdmin = new ConfirmSaveWindow();
                confirmSaveWindowPrzezAdmin.setVisible(true);

            }
        }

        confirmSignupButtonListener confirm_signup_button_listener = new confirmSignupButtonListener(this.conn);
        confirmSignupButton.addActionListener(confirm_signup_button_listener); // Dodajemy ActionListener do przycisku "Modyfikacja"

       //rejectSignupButton = new JButton("Odrzucenie zapisu");

        signupSection.add(confirmSignupButton);
        //signupSection.add(rejectSignupButton);

        // Ustawienia panelu głównego
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(userSection);
        mainPanel.add(eventSection);
        mainPanel.add(signupSection);
        add(mainPanel);

        setVisible(true);
    }

}
