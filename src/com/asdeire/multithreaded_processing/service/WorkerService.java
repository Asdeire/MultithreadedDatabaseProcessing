package com.asdeire.multithreaded_processing.service;

import com.asdeire.multithreaded_processing.entity.Worker;
import com.asdeire.multithreaded_processing.entity.enums.Education;
import com.asdeire.multithreaded_processing.entity.enums.Gender;
import com.asdeire.multithreaded_processing.service.impl.WorkerRepository;

import java.time.LocalDate;
import java.util.List;

public class WorkerService {
    private WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public double averageSalary(LocalDate startDate, LocalDate endDate) {
        List<Worker> workers = workerRepository.findByStartDateBetween(startDate, endDate);
        double totalSalary = workers.stream().mapToDouble(Worker::getSalary).sum();
        return totalSalary / workers.size();
    }

    public long countWorkersWithExperience(int years) {
        LocalDate cutoffDate = LocalDate.now().minusYears(years);
        return workerRepository.countByStartDateBefore(cutoffDate);
    }

    public double highestSalary() {
        return workerRepository.findHighestSalary();
    }

    public double lowestSalary() {
        return workerRepository.findLowestSalary();
    }

    public long countWorkersWithEducation(Education education) {
        return workerRepository.countByEducation(education);
    }

    public long countWorkersByGender(Gender gender) {
        return workerRepository.countByGender(gender);
    }
}
