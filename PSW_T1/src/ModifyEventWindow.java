import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyEventWindow extends JFrame implements ActionListener {

    private Connection conn;
    private JLabel eventLabel, nameLabel, agendaLabel, dateLabel;
    private JComboBox<String> eventComboBox;
    private JTextField nameField, agendaField, dateField;
    private JButton modifyButton;
    private Connection connection;

    public ModifyEventWindow(Connection conn) {
        this.conn = conn;
        new ModifyEventWindow();
    }

    public ModifyEventWindow() {
        // Ustawienie tytułu okna
        setTitle("Modyfikuj wydarzenie");

        // Ustawienie rozmiaru okna
        setSize(500, 250);

        // Połączenie z bazą danych
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost/psw";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

// Dodanie etykiet i pól tekstowych
        eventLabel = new JLabel("Wybierz wydarzenie:");
        eventComboBox = new JComboBox<>();
        nameLabel = new JLabel("Nazwa wydarzenia:");
        nameField = new JTextField();
        agendaLabel = new JLabel("Opis:");
        agendaField = new JTextField();
        dateLabel = new JLabel("Termin: [format daty: rrrr/mm/dd]");
        dateField = new JTextField();

// Dodanie komponentów do panelu
        panel.add(eventLabel);
        panel.add(eventComboBox);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(agendaLabel);
        panel.add(agendaField);
        panel.add(dateLabel);
        panel.add(dateField);

        add(panel);

        // Wczytanie wydarzeń z bazy danych
        loadEvents();

        // Dodanie przycisku modyfikuj
        modifyButton = new JButton("Modyfikuj");
        modifyButton.addActionListener(this);
        panel.add(modifyButton);

        // Ustawienie widoczności okna
        setVisible(true);
    }

    private void loadEvents() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT nazwa_wydarzenia FROM wydarzenia");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                eventComboBox.addItem(resultSet.getString("nazwa_wydarzenia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            String selectedEvent = (String) eventComboBox.getSelectedItem();
            String newName = nameField.getText();
            String newAgenda = agendaField.getText();
            String newDate = dateField.getText();

            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE wydarzenia SET nazwa_wydarzenia=?, agenda=?, termin_wydarzenia=? WHERE nazwa_wydarzenia=?");
                statement.setString(1, newName);
                statement.setString(2, newAgenda);
                statement.setString(3, newDate);
                statement.setString(4, selectedEvent);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Wydarzenie zostało zmodyfikowane.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Wystąpił błąd podczas modyfikowania wydarzenia. Spróbuj ponownie.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
                }
            }
        }