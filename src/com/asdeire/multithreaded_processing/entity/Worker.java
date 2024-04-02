package com.asdeire.multithreaded_processing.entity;

import com.asdeire.multithreaded_processing.entity.enums.Education;
import com.asdeire.multithreaded_processing.entity.enums.Gender;

import java.time.LocalDate;

public class Worker {

    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private double salary;
    private Education education;

    public double getSalary() {
        return salary;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
