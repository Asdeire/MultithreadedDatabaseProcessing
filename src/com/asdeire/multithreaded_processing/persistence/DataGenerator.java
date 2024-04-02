package com.asdeire.multithreaded_processing.persistence;
import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class DataGenerator {

    private final Connection connection;

    public DataGenerator(Connection connection) {
        this.connection = connection;
    }

    public void generateData(int recordsToGenerate) {
        try {
            createTableIfNotExists();

            Faker faker = new Faker(new Locale("en"));

            String insertQuery = "INSERT INTO workers (first_name, last_name, gender, salary, education) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            for (int i = 0; i < recordsToGenerate; i++) {
                statement.setString(1, faker.name().firstName());
                statement.setString(2, faker.name().lastName());
                statement.setString(3, faker.options().option("Male", "Female"));
                statement.setDouble(4, faker.number().randomDouble(2, 1000, 5000));
                statement.setString(5, faker.options().option("High School", "Bachelor", "Master", "Doctorate"));

                statement.addBatch();

                if (i % 1000 == 0) {
                    statement.executeBatch();
                }
            }

            statement.executeBatch(); // Execute any remaining batch
            System.out.println("Data insertion completed.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS workers ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "first_name VARCHAR(255), "
                + "last_name VARCHAR(255), "
                + "gender ENUM('Male', 'Female'), "
                + "salary DECIMAL(10, 2), "
                + "education ENUM('High School', 'Bachelor', 'Master', 'Doctorate')"
                + ")";
        connection.createStatement().executeUpdate(createTableQuery);
        System.out.println("Table 'workers' created successfully.");
    }
}

