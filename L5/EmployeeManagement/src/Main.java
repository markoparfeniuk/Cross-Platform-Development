public class Main {
    public static void main(String[] args) {
        Employee[] employees = createEmployees();
        printAllEmployees(employees);
        printPresentEmployees(employees);
        printNotPresentEmployees(employees);
        searchByLastName(employees, "Smith");
    }

    // Функція ініціалізації масиву даних працівників
    public static Employee[] createEmployees() {
        Employee[] employees = new Employee[] {
            new Employee("Smith", "Manager", "Sales", 1, true, "9:00"),
            new Employee("Johnson", "Engineer", "IT", 2),
            new Employee("Williams", "Assistant", "HR", 3),
            new Employee("Jones", "Director", "Marketing", 4, false, "18:00"),
            new Employee("Brown", "Analyst", "Finance", 5, true, "9:30"),
            new Employee("Davis", "Coordinator", "Operations", 6, false, "16:45"),
            new Employee("Miller", "Specialist", "Customer Service", 7, true, "9:45"),
            new Employee("Wilson", "Supervisor", "Logistics", 8),
            new Employee("Smith", "Consultant", "Legal", 9, true, "10:00"),
            new Employee("Taylor", "Executive", "Management", 10, false, "19:00")
        };

        return employees;
    }

    // Функція виводу масиву працівників
    public static void printAllEmployees(Employee[] employees) {
        System.out.println("All employees:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // Функція виводу працівників, присутніх на території
    public static void printPresentEmployees(Employee[] employees) {
        System.out.println("\nPresent employees:");
        for (Employee employee : employees) {
            if (employee.isPresent()) {
                System.out.println(employee);
            }
        }
    }

    // Функція виводу працівників, відсутніх на території
    public static void printNotPresentEmployees(Employee[] employees) {
        System.out.println("\nNot present employees:");
        for (Employee employee : employees) {
            if (!employee.isPresent()) {
                System.out.println(employee);
            }
        }
    }

    // Функція пошуку (виводу) працівників за прізвищем
    public static void searchByLastName(Employee[] employees, String searchLastName) {
        System.out.println("\nSearch result for last name: " + searchLastName);
        for (Employee employee : employees) {
            if (employee.getLastName().equals(searchLastName)) {
                System.out.println(employee);
            }
        }
    }
}