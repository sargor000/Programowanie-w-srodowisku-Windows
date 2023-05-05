import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddEventWindow extends JFrame {

    private Connection conn;

    public AddEventWindow(Connection conn) {
        this.conn = conn;
        initUI();
    }

    private void initUI() {
        // Ustawienie tytułu okna
        setTitle("Dodaj nowe wydarzenie");

        // Ustawienie rozmiaru okna
        setSize(500, 300);

        // Dodanie panelu
        JPanel panel = new JPanel(new FlowLayout());
        add(panel);

        // Ustawienie menadżera układu
        //this.setLayout(new GridLayout(4,2));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); // odstęp między komponentami to 10 pikseli

        // Dodanie etykiet i pól tekstowych
        JLabel nameLabel = new JLabel("Nazwa wydarzenia:");
        JTextField nameField = new JTextField();
        nameLabel.setBounds(0, 10, 220, 250);
        nameField.setPreferredSize(new Dimension(200, 25));

        JLabel agendaLabel = new JLabel("Opis:");
        JTextField agendaField = new JTextField();
        agendaField.setBounds(0, 60, 220, 250);
        agendaField.setPreferredSize(new Dimension(200, 25));

        JLabel dateLabel = new JLabel("Termin: [format daty: rrrr/mm/dd]");
        JTextField dateField = new JTextField();
        agendaField.setBounds(0, 110, 220, 250);
        dateField.setPreferredSize(new Dimension(200, 25));

        // Dodanie komponentów do okna
        this.add(nameLabel);
        this.add(nameField);

        this.add(agendaLabel);
        this.add(agendaField);

        this.add(dateLabel);
        this.add(dateField);

        // Dodanie przycisku do zapisu wydarzenia
        JButton saveButton = new JButton("Dodaj");
        saveButton.setBounds(20, 200, 220, 250);


        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String agenda = agendaField.getText();
            String date = dateField.getText();
            try {
                // Dodanie nowego wydarzenia do bazy danych
                String query = "INSERT INTO wydarzenia(nazwa_wydarzenia, agenda, termin_wydarzenia) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, agenda);
                statement.setString(3, date);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Nowe wydarzenie zostało dodane.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Błąd podczas dodawania wydarzenia: " + ex.getMessage());
            }
        });
        panel.add(saveButton);

        // Wyśrodkowanie okna
        setLocationRelativeTo(null);

        // Ustawienie działania przycisku zamknięcia okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Wyświetlenie okna
        setVisible(true);
    }

}
