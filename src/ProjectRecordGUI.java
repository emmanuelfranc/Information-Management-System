import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectRecordGUI extends JFrame implements ActionListener {
    private JTextField id = new JTextField(2);
    private JTextField name = new JTextField(25);
    private JTextField type = new JTextField(6);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField location = new JTextField(9);
    private JTextField cost = new JTextField(6);
    private JTextField price = new JTextField(6);
    private JTextField venSize = new JTextField(5);
    private JTextField duration = new JTextField(2);
    private JTextField durUnits = new JTextField(3);

    private JTextField network = new JTextField(5);
    private JTextField playwright = new JTextField(8);
    private JTextField genre = new JTextField(8);
    private JTextField format = new JTextField(9);

    private JLabel labId = new JLabel("Id: ");
    private JLabel labn = new JLabel("Name: ");
    private JLabel labt = new JLabel("Type: ");
    private JLabel labd = new JLabel("Day: ");
    private JLabel labm = new JLabel("Month: ");
    private JLabel laby = new JLabel("Year: ");
    private JLabel labl = new JLabel("Location: ");
    private JLabel labc = new JLabel("Cost: ");
    private JLabel labp = new JLabel("Price: ");
    private JLabel labv = new JLabel("Venue Size: ");
    private JLabel labdur = new JLabel("Duration: ");
    private JLabel labdurUn = new JLabel("Duration Units: ");

    private JLabel labnet = new JLabel("Network: ");
    private JLabel labplwr = new JLabel("Playwright: ");
    private JLabel labgen = new JLabel("Genre: ");
    private JLabel labf = new JLabel("Format: ");

    private JButton addP = new JButton("Add");
    private JButton summary = new JButton("Summary");
    private JButton findAllByCost = new JButton("Find By Cost");
    private JButton findAllByType = new JButton("Find By Type");
    private JButton findAllByLocation = new JButton("Find By Location");
    private JButton removeP = new JButton("Remove");

    private String[] options = {"Select option:", "Network", "Playwright", "Genre", "Format"};
    private JComboBox comboBox = new JComboBox<String>(options);

    private ProjectRecord myProjects = new ProjectRecord();

    private JTextArea outputArea = new JTextArea(12, 70);
    private int filmCount = 0, musicCount = 0, tvCount = 0, theaterCount = 0;
    private double totalRevenue = 0.0, maxCost = 0.0, maxPrice = 0.0, minCost = Double.MAX_VALUE, minPrice = Double.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException {
        ProjectRecordGUI application = new ProjectRecordGUI();
        application.readCSV("src/the_kilted_haggis_productions_projects.csv");
    }//main

    //set up the GUI
    public ProjectRecordGUI() {
        super("Project Record");
        setLayout(new FlowLayout());
        add(comboBox);
        comboBox.addActionListener(this);
        add(labId);
        add(id);
        add(labn);
        add(name);
        add(labt);
        add(type);
        add(labd);
        add(day);
        add(labm);
        add(month);
        add(laby);
        add(year);
        add(labl);
        add(location);
        add(labc);
        add(cost);
        add(labp);
        add(price);
        add(labv);
        add(venSize);
        add(labdur);
        add(duration);
        add(labdurUn);
        add(durUnits);
        add(labnet);
        add(network);
        labnet.setVisible(false);
        network.setVisible(false);
        add(labplwr);
        add(playwright);
        labplwr.setVisible(false);
        playwright.setVisible(false);
        add(labgen);
        add(genre);
        labgen.setVisible(false);
        genre.setVisible(false);
        add(labf);
        add(format);
        labf.setVisible(false);
        format.setVisible(false);
        add(addP);
        addP.setEnabled(false);
        add(summary);
        add(findAllByCost);
        add(findAllByType);
        add(findAllByLocation);
        add(removeP);
        findAllByType.addActionListener(this);
        findAllByLocation.addActionListener(this);
        addP.addActionListener(this);
        summary.addActionListener(this);
        findAllByCost.addActionListener(this);
        removeP.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(1300, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String message = "";
        addP.setEnabled(comboBox.getSelectedIndex() != 0);
        if (e.getSource() == comboBox) {

            if (comboBox.getSelectedItem().equals("Select option:")) {
                message = "Select an option from the menu";
            }
            if (comboBox.getSelectedItem().equals("Network")) {
                setVisibleFalse("Network");
                setVisibleTrue("Network");
            } else if (comboBox.getSelectedItem().equals("Playwright")) {
                setVisibleFalse("Playwright");
                setVisibleTrue("Playwright");
            } else if (comboBox.getSelectedItem().equals("Genre")) {
                setVisibleFalse("Genre");
                setVisibleTrue("Genre");
            } else if (comboBox.getSelectedItem().equals("Format")) {
                setVisibleFalse("Format");
                setVisibleTrue("Format");
            }
        }
        if (e.getSource() == addP) {
            message = addProject(comboBox.getSelectedItem().toString());
        }
        if (e.getSource() == summary) {
            message = projectSummary();
        }
        if (e.getSource() == findAllByType) {
            message = findAllByType();
        }
        if (e.getSource() == findAllByLocation) {
            message = findAllByLocation();
        }
        if (e.getSource() == findAllByCost) {
            message = findAllByCost();
        }
        if (e.getSource() == removeP) {
            message = removeProject();
        }

        outputArea.setText(message);
        blankDisplay();
    }//action performed

    //read the csv file
    private void readCSV(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        scanner.useDelimiter(",");
        String headerLine = scanner.nextLine();
        while (scanner.hasNext()) {

            String[] values = scanner.nextLine().split(",", -1);

            int id = Integer.parseInt(values[0]);
            String pName = values[1];
            String typ = values[2];

            String dateString = values[3];
            String[] date = dateString.split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);

            String location = values[4];
            double co = Double.parseDouble(values[5]);
            double pr = Double.parseDouble(values[6]);
            String venSize = values[7];
            int duration = Integer.parseInt(values[8]);
            String durUnits = values[9];
            String network = values[10];
            String playwright = values[11];
            String genre = values[12];
            String format = values[13];

            String formattedCost = String.format("%.2f", co);
            String formattedPrice = String.format("%.2f", pr);

            double cost = Double.parseDouble(formattedCost);
            double price = Double.parseDouble(formattedPrice);

            double revenue = price - cost;
            totalRevenue += revenue;

            if (cost > maxCost) {
                maxCost = cost;
            }
            if (price > maxPrice) {
                maxPrice = price;
            }
            if (cost < minCost) {
                minCost = cost;
            }
            if (price < minPrice) {
                minPrice = price;
            }

            switch (typ) {
                case "TV":
                    Project tvProject = new TvProject(id, pName, typ, day, month, year, location, cost,
                            price, venSize, duration, durUnits, network);
                    myProjects.addProject(tvProject);
                    tvCount++;
                    break;
                case "Theater":

                    Project theaterProject = new TheaterProject(id, pName, typ, day, month, year, location, cost,
                            price, venSize, duration, durUnits, playwright);
                    myProjects.addProject(theaterProject);
                    theaterCount++;
                    break;
                case "Music":
                    Project musicProject = new MusicProject(id, pName, typ, day, month, year, location, cost,
                            price, venSize, duration, durUnits, genre);
                    myProjects.addProject(musicProject);
                    musicCount++;
                    break;
                case "Film":
                    Project filmProject = new FilmProject(id, pName, typ, day, month, year, location, cost,
                            price, venSize, duration, durUnits, format);
                    myProjects.addProject(filmProject);
                    filmCount++;
                    break;
            }
        }
        scanner.close();
    }

    //add a given project to the list of projects
    public String addProject(String what) {
        try {
            String message = "Project Added\n";
            System.out.println("Adding " + what + " project to the records");
            String iD = id.getText();
            String n = name.getText();
            String t = type.getText();
            String da = day.getText();
            String mo = month.getText();
            String ye = year.getText();
            String l = location.getText();
            String co = cost.getText();
            String pr = price.getText();
            String v = venSize.getText();
            String du = duration.getText();
            String dUni = durUnits.getText();

            if (t == null || t.trim().isEmpty() ||
                    l == null || l.trim().isEmpty() ||
                    co == null || co.trim().isEmpty() ||
                    iD == null || iD.trim().isEmpty() ||
                    n == null || n.trim().isEmpty() ||
                    da == null || da.trim().isEmpty() ||
                    mo == null || mo.trim().isEmpty() ||
                    ye == null || ye.trim().isEmpty() ||
                    pr == null || pr.trim().isEmpty() ||
                    v == null || v.trim().isEmpty() ||
                    du == null || du.trim().isEmpty() ||
                    dUni == null || dUni.trim().isEmpty()) {
                return "Null value(s) detected! Please enter all data to add a project.";
            }

            int i = Integer.parseInt(iD);
            int d = Integer.parseInt(da);
            int m = Integer.parseInt(mo);
            int y = Integer.parseInt(ye);
            double c = Double.parseDouble(co);
            double p = Double.parseDouble(pr);
            int dur = Integer.parseInt(du);

            if (isDuplicate(i, n)) {
                return "Duplicates not allowed!\n" +
                        "Entry already recorded with.." +
                        "\nID:\t" + i +
                        "\nName:\t" + n;
            }
            if (!isValidDate(m + "/" + d + "/" + y)) {
                return "Date is not valid, please input a valid date.";
            }

            switch (comboBox.getSelectedItem().toString()) {
                case "Network":
                    String net = network.getText();
                    TvProject tv = new TvProject(i, n, t, d, m, y, l, c, p, v, dur, dUni, net);
                    myProjects.addProject(tv);
                    tvCount++;
                    break;
                case "Playwright":
                    String pw = playwright.getText();
                    TheaterProject tp = new TheaterProject(i, n, t, d, m, y, l, c, p, v, dur, dUni, pw);
                    myProjects.addProject(tp);
                    theaterCount++;
                    break;
                case "Genre":
                    String g = genre.getText();
                    MusicProject mp = new MusicProject(i, n, t, d, m, y, l, c, p, v, dur, dUni, g);
                    myProjects.addProject(mp);
                    musicCount++;
                    break;
                case "Format":
                    String f = format.getText();
                    FilmProject fp = new FilmProject(i, n, t, d, m, y, l, c, p, v, dur, dUni, f);
                    myProjects.addProject(fp);
                    filmCount++;
                    break;
            }
            double revenue = p - c;
            totalRevenue += revenue;

            if (c > maxCost) {
                maxCost = c;
            }
            if (p > maxPrice) {
                maxPrice = p;
            }
            if (c < minCost) {
                minCost = c;
            }
            if (p < minPrice) {
                minPrice = p;
            }
            return message;
        } catch (Exception ex) {
            return "Something went wrong. Please check that all inputs are valid and try again.";
        }
    }

    //remove a given project from the list of projects
    public String removeProject() {
        try {
            String iD = id.getText();
            String nm = name.getText();

            if (iD == null || iD.trim().isEmpty()
                    || nm == null || nm.trim().isEmpty()) {
                return "Please enter a project id and name to delete a project.";
            }
            int i = Integer.parseInt(iD);
            return myProjects.removeProject(i, nm);
        } catch (Exception ex) {
            return "Something went wrong. Please check that all inputs are valid and try again.";
        }
    }

    public String findAllByLocation() {
        outputArea.setText("Looking up record...");
        return myProjects.findAll("location", location.getText());
    }

    public String findAllByType() {
        outputArea.setText("Looking up record...");
        return myProjects.findAll("type", type.getText());
    }

    public String findAllByCost() {
        try {
            outputArea.setText("Looking up record...");
            return myProjects.findAll("cost", cost.getText());
        } catch (Exception ex) {
            return "Please enter a valid project cost.";
        }
    }

    public boolean isDuplicate(int id, String name) {
        outputArea.setText("Checking for duplicates...");
        String message = myProjects.checkDuplicates(id, name);
        return !message.equals("No projects found");

    }

    //Generate a summary for the projects
    private String projectSummary() {
        return "Kilted Haggis has produced " + myProjects.getNumberOfProjects() + " projects that turned a profit, \n" +
                filmCount + " of which were Films, " + musicCount + " were Music, " + theaterCount + " were Theater and \n" +
                tvCount + " were Tv Programs.The cost of projects range from " + minCost + " to " + String.format("%,.2f \n", maxCost) +
                "while the project price to customers range from " + String.format("%,.2f", minPrice) + " to " + String.format("%,.2f.", maxPrice) +
                "\nThe revenue was " + String.format("%,.2f", totalRevenue) + "\n";
    }

    //sets the corresponding component to true when selected.
    public void setVisibleTrue(String project) {
        switch (project) {
            case "Network":
                labnet.setVisible(true);
                network.setVisible(true);
                break;
            case "Playwright":
                labplwr.setVisible(true);
                playwright.setVisible(true);
                break;
            case "Genre":
                labgen.setVisible(true);
                genre.setVisible(true);
                break;
            case "Format":
                labf.setVisible(true);
                format.setVisible(true);
                break;
        }
    }

    //sets the corresponding componenet to false when selected.
    public void setVisibleFalse(String project) {
        switch (project) {
            case "Network":
                labplwr.setVisible(false);
                playwright.setVisible(false);
                labgen.setVisible(false);
                genre.setVisible(false);
                labf.setVisible(false);
                format.setVisible(false);
                break;
            case "Playwright":
                labnet.setVisible(false);
                network.setVisible(false);
                labgen.setVisible(false);
                genre.setVisible(false);
                labf.setVisible(false);
                format.setVisible(false);
                break;
            case "Genre":
                labplwr.setVisible(false);
                playwright.setVisible(false);
                labnet.setVisible(false);
                network.setVisible(false);
                labf.setVisible(false);
                format.setVisible(false);
                break;
            case "Format":
                labnet.setVisible(false);
                network.setVisible(false);
                labgen.setVisible(false);
                genre.setVisible(false);
                labplwr.setVisible(false);
                playwright.setVisible(false);
                break;
        }
    }

    //clear all display in the textfields
    public void blankDisplay() {
        id.setText("");
        name.setText("");
        type.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        location.setText("");
        cost.setText("");
        price.setText("");
        venSize.setText("");
        duration.setText("");
        durUnits.setText("");
        network.setText("");
        playwright.setText("");
        genre.setText("");
        format.setText("");
    }//blank display

    //validate date in the format MM/dd/yyyy
    public boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

