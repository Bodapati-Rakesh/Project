// EmployeeManagement.java
import java.util.*;
import java.io.*;

public class EmployeeManagement {

    private List<Employee> employees = new ArrayList<>();
    private static final String FILE_NAME = "employees.csv";

    private void loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                int id = Integer.parseInt(arr[0].trim());
                String name = arr[1].trim();
                double salary = Double.parseDouble(arr[2].trim());
                employees.add(new Employee(id, name, salary));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployees() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                bw.write(e.getId() + "," + e.getName() + "," + e.getSalary());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEmployee(int id, String name, double salary) {
        employees.add(new Employee(id, name, salary));
        saveEmployees();
    }

    private void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManagement em = new EmployeeManagement();
        em.loadEmployees();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Management System:");
            System.out.println("1. View All Employees");
            System.out.println("2. Add Employee");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    em.viewEmployees();
                    break;
                case 2:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Employee Salary: ");
                    double salary = sc.nextDouble();

                    em.addEmployee(id, name, salary);
                    System.out.println("Employee added.");
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}
