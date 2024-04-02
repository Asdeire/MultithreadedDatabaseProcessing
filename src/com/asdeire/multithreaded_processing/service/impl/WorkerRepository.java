package com.asdeire.multithreaded_processing.service.impl;

import com.asdeire.multithreaded_processing.entity.Worker;
import com.asdeire.multithreaded_processing.entity.enums.Education;
import com.asdeire.multithreaded_processing.entity.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public interface WorkerRepository {
    List<Worker> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    long countByStartDateBefore(LocalDate date);

    double findHighestSalary();

    double findLowestSalary();

    long countByEducation(Education education);

    long countByGender(Gender gender);

}

