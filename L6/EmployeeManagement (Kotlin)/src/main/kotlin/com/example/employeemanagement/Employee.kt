package com.example.employeemanagement

import java.io.Serializable
import java.time.LocalTime

class Employee
    @JvmOverloads constructor(
        var lastName : String, // Прізвище працівника
        var position: String, // Посада працівника
        var department: String, // Департамент, в якому працює працівник
        val badgeNumber: Int, // Номер пропуску працівника (не може бути змінений після створення)
        var isPresent: Boolean = false, // Чи присутній працівник на робочому місці
        // Час останнього входу або виходу працівника
        var lastEntryExitTime: String = LocalTime.now().hour.toString() + ":" + LocalTime.now().minute) : Serializable, Comparable<Employee> {

    // Реалізація методу порівняння інтерфейсу Comparable
    override fun compareTo(other: Employee): Int {
        var result = lastName.compareTo(other.lastName)
        if (result == 0) {
            result = position.compareTo(other.position)
        }
        return result
    }

    // Перевизначення методу toString(), що повертає рядок, який містить всі поля співробітника у вигляді рядка, що розділений комами
    override fun toString(): String {
        return lastName + ", " + position + ", " + department + ", " + badgeNumber + ", " + (if (isPresent) "Present" else "Not present") + ", " + lastEntryExitTime
    }
}