import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRegistrationForm extends JFrame {

    private JLabel nameLabel, agendaLabel, dateLabel, typeLabel, foodLabel;
    private JComboBox<String> eventComboBox;
    private JTextArea agendaTextArea;
    private JTextField dateField;
    private JRadioButton fullRadio, partialRadio,sponsorRadio, organizerRadio;
    private ButtonGroup typeButtonGroup;
    private JComboBox vegetarianCheckbox, glutenFreeCheckbox;
    private JButton registerButton;
    private static int userID;

    private Map<String, Integer> event_map;

    private Connection conn;

    public EventRegistrationForm(Connection c, int userID) {
        this.conn = c;
        this.userID = userID;

        nameLabel = new JLabel("Nazwa wydarzenia:");
        nameLabel.setBounds(10, 10, 120, 25);
        add(nameLabel);

        this.event_map = this.getEventNames();
        String[] event_names = new ArrayList<>(this.event_map.keySet()).toArray(new String[0]);

        eventComboBox = new JComboBox<>(event_names);
        eventComboBox.setBounds(140, 10, 200, 25);
        eventComboBox.addActionListener(e -> loadAgendaTerminWydarzenia());
        add(eventComboBox);

        agendaLabel = new JLabel("Agenda:");
        agendaLabel.setBounds(10, 50, 120, 25);
        add(agendaLabel);

        agendaTextArea = new JTextArea();
        agendaTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(agendaTextArea);
        scrollPane.setBounds(140, 50, 200, 100);
        add(scrollPane);

        dateLabel = new JLabel("Termin wydarzenia:");
        dateLabel.setBounds(10, 170, 120, 25);
        add(dateLabel);

        dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(140, 170, 200, 25);
        add(dateField);

        typeLabel = new JLabel("Typ uczestnictwa:");
        typeLabel.setBounds(10, 210, 120, 25);
        add(typeLabel);

        fullRadio = new JRadioButton("Słuchacz");
        fullRadio.setBounds(140, 210, 80, 25);

        partialRadio = new JRadioButton("Autor");
        partialRadio.setBounds(140, 240, 120, 25);

        sponsorRadio = new JRadioButton("Sponsor");
        sponsorRadio.setBounds(140, 270, 80, 25);

        organizerRadio = new JRadioButton("Organizator");
        organizerRadio.setBounds(140, 300, 120, 25);

        ButtonGroup group = new ButtonGroup();
        group.add(fullRadio);
        group.add(partialRadio);
        group.add(sponsorRadio);
        group.add(organizerRadio);

        add(fullRadio);
        add(partialRadio);
        add(sponsorRadio);
        add(organizerRadio);

        foodLabel = new JLabel("Wyżywienie:");
        foodLabel.setBounds(10, 350, 120, 25);
        add(foodLabel);

        String[] food_names = {"Wegetarianskie", "Bezglutenowe", "Bez preferencji"};
        vegetarianCheckbox = new JComboBox<>(food_names);
        vegetarianCheckbox.setBounds(140, 350, 120, 25);
        add(vegetarianCheckbox);

        registerButton = new JButton("Zarejestruj się");
        registerButton.setBounds(140, 450, 120, 25);
        registerButton.addActionListener(e -> registerForEvent());
        add(registerButton);

        setTitle("Formularz zgłoszeń na wydarzenie");
        setSize(370, 550);
        setResizable(false);
        setLayout(null);

        loadAgendaTerminWydarzenia();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Map<String, Integer> getEventNames() {
        Map<String, Integer> event_map = new HashMap<>();

        try {
            Statement statement = this.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nazwa_wydarzenia, id_wydarzenia  FROM wydarzenia");
            while (resultSet.next()) {
                String nazwa_wydarzenia = resultSet.getString("nazwa_wydarzenia");
                Integer id_wydarzenia = resultSet.getInt("id_wydarzenia");
                event_map.put(nazwa_wydarzenia, id_wydarzenia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event_map;
    }

    private void loadAgendaTerminWydarzenia() {
        try {
            PreparedStatement statement = this.conn.prepareStatement("SELECT agenda, termin_wydarzenia FROM wydarzenia WHERE nazwa_wydarzenia=?");
            statement.setString(1, (String) eventComboBox.getSelectedItem());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                agendaTextArea.setText(resultSet.getString("agenda"));
                dateField.setText(resultSet.getDate("termin_wydarzenia").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void registerForEvent() {
        String eventName = (String) eventComboBox.getSelectedItem();
        Integer id_wydarzenia = this.event_map.get(eventName);

        String type = "";
        if (fullRadio.isSelected()) {
            type = "Słuchacz";
        } else if (partialRadio.isSelected()) {
            type = "Autor";
        } else if (sponsorRadio.isSelected()) {
            type = "Sponsor";
        } else if (organizerRadio.isSelected()) {
            type = "Organizator";
        }

        String food = (String) vegetarianCheckbox.getSelectedItem();

            try {
                PreparedStatement statement = this.conn.prepareStatement("INSERT INTO zapisy (id_konta, id_wydarzenia, typ_uczestnictwa, wyzywienie) VALUES (?, ?, ?, ?)");
                statement.setInt(1, this.userID );
                statement.setInt(2, id_wydarzenia);
                statement.setString(3, type);
                statement.setString(4, food);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Zarejestrowano na wydarzenie!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

