import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConfirmSaveWindow extends JFrame implements ActionListener {

    //private Connection conn;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel dateLabel;
    private JLabel statusLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField statusField;

    private JButton confirmButton;
    private JButton rejectButton;
    private JButton searchButton;

    private JTable table;
    private DefaultTableModel tableModel;

    private Connection conn;
    public ConfirmSaveWindow(Connection conn) {
        this.conn = conn;
        new ConfirmSaveWindow();
    }

    public ConfirmSaveWindow() {
        super("Potwierdź zapis");

        // Ustawienie rozmiaru okna
        setSize(500, 400);

        // Dodanie panelu
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(panel);

        // Ustawienie menadżera układu
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Dodanie etykiet i pól tekstowych
        titleLabel = new JLabel("Potwierdzenie zapisu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        idLabel = new JLabel("ID zapisu:");
        panel.add(idLabel, gbc);

        idField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        searchButton = new JButton("Szukaj");
        searchButton.addActionListener(this);
        gbc.gridx = 2;
        panel.add(searchButton, gbc);

        nameLabel = new JLabel("Nazwa wydarzenia:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setEditable(false);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        dateLabel = new JLabel("Data:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(dateLabel, gbc);

        dateField = new JTextField(10);
        dateField.setEditable(false);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        statusLabel = new JLabel("Status:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(statusLabel, gbc);

        statusField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(statusField, gbc);

        // Dodanie przycisków
        confirmButton = new JButton("Potwierdź");
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(confirmButton, gbc);

        rejectButton = new JButton("Odrzuć");
        rejectButton.setEnabled(false);
        rejectButton.addActionListener(this);
        gbc.gridy++;
        panel.add(rejectButton, gbc);

        // Dodanie tabeli z danymi
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nazwa wydarzenia");
        tableModel.addColumn("Data");
        tableModel.addColumn("Status");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/psw", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM zapis");
            while (rs.next()) {
                int id = rs.getInt("id_konta");
                String eventName = rs.getString("id_wydarzenia");
                String date = rs.getString("typ_uczestnictwa");
                String status = rs.getString("status");

                tableModel.addRow(new Object[]{id, eventName, date, status});
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Wystąpił błąd podczas pobierania danych z bazy danych.");
        }

// Dodanie tabeli do panelu
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

// Dodanie przycisku potwierdzenia
        JButton confirmButton = new JButton("Potwierdź");
        confirmButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Nie wybrano żadnego zapisu.");
            } else {
                int id = (int) table.getValueAt(selectedRow, 0);
                String eventName = (String) table.getValueAt(selectedRow, 1);
                String date = (String) table.getValueAt(selectedRow, 2);

                Object[] options = {"Potwierdzam", "Odrzucam"};
                int result = JOptionPane.showOptionDialog(this,
                        "Czy potwierdzasz zapis na wydarzenie \"" + eventName + "\" z dnia " + date + "?",
                        "Potwierdzenie zapisu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/psw", "root", "");
                        PreparedStatement stmt = conn.prepareStatement("UPDATE zapis SET status = ? WHERE id_konta = ?");
                        stmt.setString(1, "potwierdzam");
                        stmt.setInt(2, id);
                        stmt.executeUpdate();

                        conn.close();

                        JOptionPane.showMessageDialog(this, "Zapis został potwierdzony.");
                        tableModel.setValueAt("potwierdzam", selectedRow, 3);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Wystąpił błąd podczas aktualizowania danych w bazie danych.");
                    }
                } else if (result == JOptionPane.NO_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/psw", "root", "");
                        PreparedStatement stmt = conn.prepareStatement("UPDATE zapis SET status = ? WHERE id_konta = ?");
                        stmt.setString(1, "odrzucam");
                        stmt.setInt(2, id);
                        stmt.executeUpdate();

                        conn.close();

                        JOptionPane.showMessageDialog(this, "Zapis został odrzucony.");
                        tableModel.setValueAt("odrzucam", selectedRow, 3);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Wystąpił błąd podczas aktualizowania danych w bazie danych.");
                    }
                }
            }
        });

        panel.add(confirmButton);

// Wyświetlenie okna
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}