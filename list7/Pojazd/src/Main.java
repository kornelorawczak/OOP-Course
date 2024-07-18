import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


class Vehicle implements Serializable{
    protected String color;
    protected String make;
    protected String model;
    protected int year;

    public Vehicle(String color, String make, String model, int year){
        this.color = color;
        this.make = make;
        this.model = model;
        this.year = year;
    }
    @Override
    public String toString(){
        return "Vehicle of color: " + this.color + ", Make: " + this.make + ", Model: " + this.model + ", Year: " + this.year;
    }
}

class Car extends Vehicle implements Serializable{
    private int horsepower;
    private String motor;
    public Car(){
        super("", "", "", 0);
        this.horsepower = 0;
        this.motor = "";
    }
    public Car(String CarColor, String CarMake, String CarModel, String CarMotor, int CarYear, int CarHorsepower){
        super(CarColor, CarMake, CarModel, CarYear);
        this.horsepower = CarHorsepower;
        this.motor = CarMotor;
    }
    @Override
    public String toString(){
        return "Car of color: " + super.color + ", Make: " + super.make + ", Model: " + super.model + ", Year: " + super.year + " has a motor: " + this.motor + " producing " + this.horsepower + " horsepower!";
    }
}

class Plane extends Vehicle implements Serializable{
    private int engines;
    private String purpose;
    public Plane(){
        super("", "", "", 0);
        this.engines = 0;
        this.purpose = "";
    }
    public Plane(String PlaneColor, String PlaneMake, String PlaneModel, int PlaneYear, String PlanePurpose, int PlaneEngines){
        super(PlaneColor, PlaneMake, PlaneModel, PlaneYear);
        this.engines = PlaneEngines;
        this.purpose = PlanePurpose;
    }
    @Override
    public String toString(){
        return this.purpose + " plane of color: " + super.color + ", Make: " + super.make + ", Model: " + super.model + ", Year: " + super.year + " has " + this.engines + " engines";
    }
}

class CarEditor extends JFrame implements Serializable {
    private List<Car> cars = new ArrayList<>();
    public void showGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JLabel titleLabel = new JLabel("Object 'Car' maker");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 30));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(titleLabel, c);


        JLabel makeLabel = new JLabel("Car Make: ");
        makeLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField makeField = new JTextField(20);
        makeField.setPreferredSize(new Dimension(100, 35));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(makeLabel, c);
        c.gridx = 1;
        panel.add(makeField, c);

        JLabel modelLabel = new JLabel("Car Model: ");
        modelLabel.setFont(modelLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField modelField = new JTextField(20);
        modelField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 2;
        panel.add(modelLabel, c);
        c.gridx = 1;
        panel.add(modelField, c);

        JLabel yearLabel = new JLabel("Car Year of production: ");
        yearLabel.setFont(yearLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField yearField = new JTextField(20);
        yearField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 3;
        panel.add(yearLabel, c);
        c.gridx = 1;
        panel.add(yearField, c);

        JLabel colorLabel = new JLabel("Car Color: ");
        colorLabel.setFont(colorLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField colorField = new JTextField(20);
        colorField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 4;
        panel.add(colorLabel, c);
        c.gridx = 1;
        panel.add(colorField, c);

        JLabel motorLabel = new JLabel("Car Motor: ");
        motorLabel.setFont(motorLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField motorField = new JTextField(20);
        motorField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 5;
        panel.add(motorLabel, c);
        c.gridx = 1;
        panel.add(motorField, c);

        JLabel hpLabel = new JLabel("Car Horsepower: ");
        hpLabel.setFont(hpLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField hpField = new JTextField(20);
        hpField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 6;
        panel.add(hpLabel, c);
        c.gridx = 1;
        panel.add(hpField, c);

        JButton createButton = new JButton("Create car object");
        createButton.addActionListener(e -> {
            String make = makeField.getText();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            String color = colorField.getText();
            String motor = motorField.getText();
            int hp = Integer.parseInt(hpField.getText());
            Car car = new Car(color, make, model, motor, year, hp);
            cars.add(car);
            JOptionPane.showMessageDialog(this, "Car created: " + car.toString());
        });
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        panel.add(createButton, c);

        JButton saveButton = new JButton("Save object to file");
        saveButton.addActionListener(e -> {
            try {
                List<Vehicle> existingVehicles = new ArrayList<>();
                File file = new File("cars.dat");

                if (file.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        existingVehicles = (List<Vehicle>) ois.readObject();
                    }
                }

                existingVehicles.addAll(cars);
                //System.out.println(existingVehicles);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(existingVehicles);
                    JOptionPane.showMessageDialog(this, "Vehicles saved to file.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        c.gridy=8;
        panel.add(saveButton, c);

        JButton loadButton = new JButton("Load object from file");
        loadButton.addActionListener(e -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cars.dat"))){
                List<Car> input_cars = (List<Car>) ois.readObject();
                if (input_cars.isEmpty()){
                    JOptionPane.showMessageDialog(this, "No cars to load from file.");
                }
                else{
                    StringBuilder loadedInfo = new StringBuilder("Loaded Cars: \n");
                    for (Car car : input_cars){
                        loadedInfo.append(car.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, loadedInfo.toString());
                }
            }
            catch(IOException | ClassNotFoundException ex){
                ex.printStackTrace();
            }
        });
        c.gridy=9;
        panel.add(loadButton, c);

        JButton clearButton = new JButton("Clear Data");
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        panel.add(clearButton, c);

        clearButton.addActionListener(e -> {
            cars.clear();
            File file = new File("cars.dat");
            if (file.exists()){
                file.delete();
            }
            JOptionPane.showMessageDialog(this, "Data cleared!");
        });
        add(panel);
        setVisible(true);
    }
}

class PlaneEditor extends JFrame implements Serializable {
    private List<Plane> planes = new ArrayList<>();
    public void showGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JLabel titleLabel = new JLabel("Object 'Plane' maker");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 30));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(titleLabel, c);


        JLabel makeLabel = new JLabel("Plane Make: ");
        makeLabel.setFont(makeLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField makeField = new JTextField(20);
        makeField.setPreferredSize(new Dimension(100, 35));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(makeLabel, c);
        c.gridx = 1;
        panel.add(makeField, c);

        JLabel modelLabel = new JLabel("Plane Model: ");
        modelLabel.setFont(modelLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField modelField = new JTextField(20);
        modelField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 2;
        panel.add(modelLabel, c);
        c.gridx = 1;
        panel.add(modelField, c);

        JLabel yearLabel = new JLabel("Plane Year of production: ");
        yearLabel.setFont(yearLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField yearField = new JTextField(20);
        yearField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 3;
        panel.add(yearLabel, c);
        c.gridx = 1;
        panel.add(yearField, c);

        JLabel colorLabel = new JLabel("Plane Color: ");
        colorLabel.setFont(colorLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField colorField = new JTextField(20);
        colorField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 4;
        panel.add(colorLabel, c);
        c.gridx = 1;
        panel.add(colorField, c);

        JLabel enginesLabel = new JLabel("Plane Engines: ");
        enginesLabel.setFont(enginesLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField enginesField = new JTextField(20);
        enginesField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 5;
        panel.add(enginesLabel, c);
        c.gridx = 1;
        panel.add(enginesField, c);

        JLabel purposeLabel = new JLabel("Plane Purpose: ");
        purposeLabel.setFont(purposeLabel.getFont().deriveFont(Font.BOLD, 20));
        JTextField purposeField = new JTextField(20);
        purposeField.setPreferredSize(new Dimension(100, 35));
        c.gridx = 0;
        c.gridy = 6;
        panel.add(purposeLabel, c);
        c.gridx = 1;
        panel.add(purposeField, c);

        JButton createButton = new JButton("Create plane object");
        createButton.addActionListener(e -> {
            String make = makeField.getText();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            String color = colorField.getText();
            String purpose = purposeField.getText();
            int engines = Integer.parseInt(enginesField.getText());
            Plane plane = new Plane(color, make, model, year, purpose, engines);
            planes.add(plane);
            JOptionPane.showMessageDialog(this, "Plane created: " + plane.toString());
        });
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        panel.add(createButton, c);

        JButton saveButton = new JButton("Save object to file");
        saveButton.addActionListener(e -> {
            try {
                List<Vehicle> existingVehicles = new ArrayList<>();
                File file = new File("planes.dat");

                if (file.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        existingVehicles = (List<Vehicle>) ois.readObject();
                    }
                }

                existingVehicles.addAll(planes);
                //System.out.println(existingVehicles);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(existingVehicles);
                    JOptionPane.showMessageDialog(this, "Vehicles saved to file.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        c.gridy=8;
        panel.add(saveButton, c);

        JButton loadButton = new JButton("Load object from file");
        loadButton.addActionListener(e -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("planes.dat"))){
                List<Plane> input_planes = (List<Plane>) ois.readObject();
                if (input_planes.isEmpty()){
                    JOptionPane.showMessageDialog(this, "No Planes to load from file.");
                }
                else{
                    StringBuilder loadedInfo = new StringBuilder("Loaded Planes: \n");
                    for (Plane plane : input_planes){
                        loadedInfo.append(plane.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, loadedInfo.toString());
                }
            }
            catch(IOException | ClassNotFoundException ex){
                ex.printStackTrace();
            }
        });
        c.gridy=9;
        panel.add(loadButton, c);

        JButton clearButton = new JButton("Clear Data");
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        panel.add(clearButton, c);

        clearButton.addActionListener(e -> {
            planes.clear();
            File file = new File("planes.dat");
            if (file.exists()){
                file.delete();
            }
            JOptionPane.showMessageDialog(this, "Data cleared!");
        });
        add(panel);
        setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("In order to open Cars GUI Editor insert 'cars', for Planes GUI Editor insert 'planes': ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (Objects.equals(choice, "cars")){
            new CarEditor().showGUI();
        }
        else if (Objects.equals(choice, "planes")){
            new PlaneEditor().showGUI();
        }
        else{
            throw new InputMismatchException("No such choice!");
        }
    }
}