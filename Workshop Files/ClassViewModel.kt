package com.example.jetpack_compose1

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import java.util.Collections


//something missing here


class ClassViewModel : ViewModel() {

    // This is the data for our classes
    val availableClasses = listOf(
        //fill me
    )


    //something missing here


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
