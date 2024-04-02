package com.asdeire.multithreaded_processing.persistence;

import com.asdeire.multithreaded_processing.entity.Worker;
import com.asdeire.multithreaded_processing.entity.enums.Education;
import com.asdeire.multithreaded_processing.entity.enums.Gender;
import com.asdeire.multithreaded_processing.service.impl.WorkerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcWorkerRepository implements WorkerRepository {
    private Connection connection;

    public JdbcWorkerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Worker> findByStartDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Worker> workers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM workers WHERE start_date BETWEEN ? AND ?");
            statement.setDate(1, java.sql.Date.valueOf(startDate));
            statement.setDate(2, java.sql.Date.valueOf(endDate));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                workers.add(mapResultSetToWorker(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    @Override
    public long countByStartDateBefore(LocalDate date) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM workers WHERE start_date < ?");
            statement.setDate(1, java.sql.Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double findHighestSalary() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(salary) FROM workers");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public double findLowestSalary() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MIN(salary) FROM workers");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public long countByEducation(Education education) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM workers WHERE education = ?");
            statement.setString(1, education.name());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long countByGender(Gender gender) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM workers WHERE gender = ?");
            statement.setString(1, gender.name());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    // helper method to map ResultSet to Worker object
    private Worker mapResultSetToWorker(ResultSet resultSet) throws SQLException {
        Worker worker = new Worker();
        worker.setId(resultSet.getLong("id"));
        worker.setFirstName(resultSet.getString("first_name"));
        // map other fields
        return worker;
    }
}
