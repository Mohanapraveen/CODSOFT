import java.io.*;
import java.util.*;
    class Student {
        private String id;
        private String name;
        private int age;
        private String department;

        public Student(String id, String name, int age, String department) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.department = department;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return id + "," + name + "," + age + "," + department;
        }
    }

    class StudentManagementSystem {
        private static final String FILE_NAME = "students.txt";
        private List<Student> students = new ArrayList<>();

        public void loadStudents() {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading student data: " + e.getMessage());
            }
        }


        public void saveStudents() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (Student student : students) {
                    writer.write(student.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error saving student data: " + e.getMessage());
            }
        }

        // Method to add a new student
        public void addStudent(Scanner scanner) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            students.add(new Student(id, name, age, department));
            System.out.println("Student added successfully.");
        }

        public void editStudent(Scanner scanner) {
            System.out.print("Enter Student ID to edit: ");
            String id = scanner.nextLine();

            for (Student student : students) {
                if (student.getId().equals(id)) {
                    System.out.print("Enter new Name: ");
                    student.setName(scanner.nextLine());

                    System.out.print("Enter new Age: ");
                    student.setAge(scanner.nextInt());
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter new Department: ");
                    student.setDepartment(scanner.nextLine());

                    System.out.println("Student data updated successfully.");
                    return;
                }
            }
            System.out.println("Student not found.");
        }

        public void searchStudent(Scanner scanner) {
            System.out.print("Enter Student ID to search: ");
            String id = scanner.nextLine();

            for (Student student : students) {
                if (student.getId().equals(id)) {
                    System.out.println("Student found: " + student);
                    return;
                }
            }
            System.out.println("Student not found.");
        }

        public void displayAllStudents() {
            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        }

        public void displayMenu() {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n-- Student Management System --");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Search Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(scanner);
                        saveStudents();
                        break;
                    case 2:
                        editStudent(scanner);
                        saveStudents();
                        break;
                    case 3:
                        searchStudent(scanner);
                        break;
                    case 4:
                        displayAllStudents();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Exiting the system.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            scanner.close();
        }

        public static void main(String[] args) {
            StudentManagementSystem sms = new StudentManagementSystem();
            sms.loadStudents();
            sms.displayMenu();
        }
    }
