import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = createEmployees();
        printAllEmployees(employees);
        printPresentEmployees(employees);
        printNotPresentEmployees(employees);
        searchByLastName(employees, "Smith");
        saveEmployeesToFile(employees, "employees.txt");
        ArrayList<Employee> result = loadEmployeesFromFile("employees.txt");
        if (result != null && result.size() > 0) {
            result.add(new Employee("Fiona", "Executive", "Management", 11));
            result.add(new Employee("Fiona", "Specialist", "Customer Service", 12));
            result.add(new Employee("Fiona", "Assistant", "HR", 13));
            System.out.printf("%nSorting the list of employees...%n%n");
            sortEmployees(result);
            printAllEmployees(result);
        }
    }

    // Функція ініціалізації списку даних працівників
    public static ArrayList<Employee> createEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Smith", "Manager", "Sales", 1, true, "9:00"));
        employees.add(new Employee("Johnson", "Engineer", "IT", 2));
        employees.add(new Employee("Williams", "Assistant", "HR", 3));
        employees.add(new Employee("Jones", "Director", "Marketing", 4, false, "18:00"));
        employees.add(new Employee("Brown", "Analyst", "Finance", 5, true, "9:30"));
        employees.add(new Employee("Davis", "Coordinator", "Operations", 6, false, "16:45"));
        employees.add(new Employee("Miller", "Specialist", "Customer Service", 7, true, "9:45"));
        employees.add(new Employee("Wilson", "Supervisor", "Logistics", 8));
        employees.add(new Employee("Smith", "Consultant", "Legal", 9, true, "10:00"));
        employees.add(new Employee("Taylor", "Executive", "Management", 10, false, "19:00"));

        return employees;
    }

    // Функція запису списку даних працівників у файл fileName
    public static void saveEmployeesToFile(ArrayList<Employee> employees, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Функція зчитування списку даних працівників із файлу fileName
    public static ArrayList<Employee> loadEmployeesFromFile(String fileName) {
        ArrayList<Employee> employees = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            employees = (ArrayList<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    // Функція сортування бульбашкою списку даних працівників
    public static void sortEmployees(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = 0; j < employees.size() - i - 1; j++) {
                if (employees.get(j).compareTo(employees.get(j + 1)) > 0) {
                    Employee temp = employees.get(j);
                    employees.set(j, employees.get(j + 1));
                    employees.set(j + 1, temp);
                }
            }
        }
    }

    // Функція виводу списку працівників
    public static void printAllEmployees(ArrayList<Employee> employees) {
        System.out.println("All employees:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // Функція виводу працівників, присутніх на території
    public static void printPresentEmployees(ArrayList<Employee> employees) {
        System.out.println("\nPresent employees:");
        for (Employee employee : employees) {
            if (employee.isPresent()) {
                System.out.println(employee);
            }
        }
    }

    // Функція виводу працівників, відсутніх на території
    public static void printNotPresentEmployees(ArrayList<Employee> employees) {
        System.out.println("\nNot present employees:");
        for (Employee employee : employees) {
            if (!employee.isPresent()) {
                System.out.println(employee);
            }
        }
    }

    // Функція пошуку (виводу) працівників за прізвищем
    public static void searchByLastName(ArrayList<Employee> employees, String searchLastName) {
        System.out.println("\nSearch result for last name: " + searchLastName);
        for (Employee employee : employees) {
            if (employee.getLastName().equals(searchLastName)) {
                System.out.println(employee);
            }
        }
    }
}