import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RemoveEvents extends JFrame implements ActionListener {
    private Connection conn;
    public RemoveEvents(Connection conn) {
        this.conn = conn;
        new RemoveEvents();
    }

    private JComboBox<String> wydarzeniaCombo;
    private Vector<String> wydarzeniaVector;
    private JButton usunButton;
    private Connection connection;
    private Statement statement;

    public RemoveEvents() {
        super("Usuń wydarzenie");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Wybierz wydarzenie do usunięcia:");
        add(label);

        wydarzeniaVector = new Vector<String>();
        wydarzeniaCombo = new JComboBox<String>(wydarzeniaVector);
        wydarzeniaCombo.addActionListener(this);
        add(wydarzeniaCombo);

        usunButton = new JButton("Usuń wydarzenie");
        usunButton.addActionListener(this);
        add(usunButton);

        try {
            // Ustawienie połączenia z bazą danych
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/psw", "root", "");

            // Pobranie danych o wydarzeniach z bazy danych
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nazwa_wydarzenia FROM wydarzenia");
            while (resultSet.next()) {
                wydarzeniaVector.add(resultSet.getString("nazwa_wydarzenia"));
            }
            wydarzeniaCombo.updateUI();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Błąd połączenia z bazą danych: " + e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == wydarzeniaCombo) {
            // Wybrano inne wydarzenie z listy rozwijanej
            return;
        }
        if (event.getSource() == usunButton) {
            try {
                // Usunięcie wydarzenia z bazy danych
                String wydarzenie = (String) wydarzeniaCombo.getSelectedItem();
                statement.executeUpdate("DELETE FROM wydarzenia WHERE nazwa_wydarzenia='" + wydarzenie + "'");
                JOptionPane.showMessageDialog(null, "Usunięto wydarzenie: " + wydarzenie, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                wydarzeniaVector.remove(wydarzenie);
                wydarzeniaCombo.updateUI();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Błąd usuwania wydarzenia: " + e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

}
