package com.example.employeemanagement;

import java.time.LocalTime;
import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee> {
    private String lastName; // Прізвище працівника
    private String position; // Посада працівника
    private String department; // Департамент, в якому працює працівник
    private final int badgeNumber; // Номер пропуску працівника (не може бути змінений після створення)
    private boolean isPresent; // Чи присутній працівник на робочому місці
    private String lastEntryExitTime; // Час останнього входу або виходу працівника

    // Конструктор класу з параметрами
    public Employee(String lastName, String position, String department, int badgeNumber, boolean isPresent, String lastEntryExitTime) {
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.badgeNumber = badgeNumber;
        this.isPresent = isPresent;
        this.lastEntryExitTime = lastEntryExitTime;
    }

    // Перевантажений конструктор класу з деякими параметрами за замовчуванням
    public Employee(String lastName, String position, String department, int badgeNumber) {
        // Викликаємо перший конструктор і передаємо йому всі параметри, крім isPresent і lastEntryExitTime
        this(lastName, position, department, badgeNumber, false, LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());
        // Встановлюємо значення поля isPresent в false і значення поля lastEntryExitTime в поточний час
    }

    // Геттери та сеттери для кожного поля
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        // Присвоюємо значення поля isPresent передане значення
        isPresent = present;
        // Присвоюємо значення поля lastEntryExitTime як поточний час
        lastEntryExitTime = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
    }

    public void setPresent(boolean present, String time) {
        // Присвоюємо передані значення
        isPresent = present;
        lastEntryExitTime = time;
    }

    public String getLastEntryExitTime() {
        return lastEntryExitTime;
    }

    public void setLastEntryExitTime(String lastEntryExitTime) {
        this.lastEntryExitTime = lastEntryExitTime;
    }

    // Реалізація методу порівняння інтерфейсу Comparable
    @Override
    public int compareTo(Employee other) {
        int result = this.lastName.compareTo(other.lastName);
        if (result == 0) {
            result = this.position.compareTo(other.position);
        }
        return result;
    }

    // Перевизначення методу toString(), що повертає рядок, який містить всі поля співробітника у вигляді рядка, що розділений комами
    @Override
    public String toString() {
        return lastName + ", " + position + ", " + department + ", " + badgeNumber + ", " + (isPresent ? "Present" : "Not present") + ", " + lastEntryExitTime;
    }
}