package com.example.jetpack_compose1

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

// No separate file for this class
class SchoolClassItem(
    val id: Int,
    val name: String,
    val instructor: String,
    val time: String
)

class ClassViewModel : ViewModel() {

    // This is our fake backend data
    val availableClasses = listOf(
        SchoolClassItem(1, "Intro to Android", "Prof. Lee", "Mon 10AM"),
        SchoolClassItem(2, "Data Structures", "Dr. Kim", "Tue 2PM"),
        SchoolClassItem(3, "UI Design", "Dr. Park", "Wed 1PM"),
        SchoolClassItem(4, "AI Ethics", "Prof. Singh", "Thu 11AM"),
        SchoolClassItem(5, "Cloud Computing", "Dr. Chan", "Fri 3PM")
    )

    // Registered classes (mutable list wrapped in Compose state)
    private val _registeredClasses = mutableStateListOf<SchoolClassItem>()
    val registeredClasses: List<SchoolClassItem>
        get() = _registeredClasses

    // Function to add a class
    fun registerClass(item: SchoolClassItem) {
        if (_registeredClasses.contains(item)) {
            return
        }
        _registeredClasses.add(item)
    }

    // Function to remove a class
    fun deregisterClass(item: SchoolClassItem) {
        _registeredClasses.remove(item)
    }

}
