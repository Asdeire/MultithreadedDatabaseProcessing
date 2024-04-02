package com.asdeire.multithreaded_processing;

import java.sql.*;

public class Test {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/workers";
        String username = "sqlname";
        String password = "root";

        /*try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DataGenerator dataGenerator = new DataGenerator(connection);
            dataGenerator.generateData(1_000_000); // Generate 1 million records

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        // Find the average salary
        String queryAverageSalary = "SELECT AVG(salary) AS average_salary FROM workers";

        // Highest and lowest salary among all employees
        String querySalaryRange = "SELECT MAX(salary) AS max_salary, MIN(salary) AS min_salary FROM workers";

        // Count of employees with a certain education
        String queryEducationCount = "SELECT COUNT(*) AS count FROM workers WHERE education = 'Master'";

        // Count of male employees
        String queryMaleCount = "SELECT COUNT(*) AS males FROM workers WHERE gender = 'Male'";

        // Count of female employees
        String queryFemaleCount = "SELECT COUNT(*) AS females FROM workers WHERE gender = 'Female'";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement statement = connection.prepareStatement(queryAverageSalary)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Average Salary: " + resultSet.getDouble("average_salary"));
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(querySalaryRange)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Highest Salary: " + resultSet.getDouble("max_salary"));
                    System.out.println("Lowest Salary: " + resultSet.getDouble("min_salary"));
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(queryEducationCount)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Workers with master education: " + resultSet.getInt("count"));
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(queryMaleCount)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Male workers: " + resultSet.getInt("males"));
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(queryFemaleCount)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Female workers: " + resultSet.getInt("females"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

