import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.sql.*;

interface Learner {
    void study();
}

class Course {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}

class ProgrammingCourse extends Course implements Learner {
    private String programmingLanguage;

    public ProgrammingCourse(String courseName, String programmingLanguage) {
        super(courseName);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public void study() {
        System.out.println("Studying programming course on " + getCourseName() + " using " + getProgrammingLanguage());
    }
}

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class LearningProgram {
    public static void main(String[] args) {
        try {
            ProgrammingCourse javaCourse = new ProgrammingCourse("Java Programming", "Java");

            javaCourse.study();

            String text = "Hello, World!";
            System.out.println("Original String: " + text);
            System.out.println("Uppercase: " + text.toUpperCase());
            System.out.println("Substring: " + text.substring(0, 5));

            Date currentDate = new Date();
            System.out.println("Current Date: " + currentDate);

            ArrayList<String> topics = new ArrayList<>();
            topics.add("OOP");
            topics.add("Collections");
            topics.add("Exceptions");

            Iterator<String> iterator = topics.iterator();
            while (iterator.hasNext()) {
                String topic = iterator.next();
                System.out.println("Topic: " + topic);

                double squareRoot = Math.sqrt(25);
                System.out.println("Square Root: " + squareRoot);

                performCRUDOperations();

                if (topic.equals("Exceptions")) {
                    throw new CustomException("Custom exception occurred!");
                }
            }
        } catch (CustomException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void performCRUDOperations() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learning";
        String username = "user";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement createStatement = connection.createStatement();
            String createQuery = "INSERT INTO topics (name) VALUES ('New Topic')";
            createStatement.executeUpdate(createQuery);

            Statement readStatement = connection.createStatement();
            String readQuery = "SELECT * FROM topics";
            ResultSet resultSet = readStatement.executeQuery(readQuery);
            while (resultSet.next()) {
                System.out.println("Topic ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name"));
            }

            Statement updateStatement = connection.createStatement();
            String updateQuery = "UPDATE topics SET name = 'Updated Topic' WHERE id = 1";
            updateStatement.executeUpdate(updateQuery);

            Statement deleteStatement = connection.createStatement();
            String deleteQuery = "DELETE FROM topics WHERE id = 1";
            deleteStatement.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
