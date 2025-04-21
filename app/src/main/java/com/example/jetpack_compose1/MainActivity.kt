package com.example.jetpack_compose1

// These are needed for Android apps
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

// These are needed for the UI
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// This uses our custom theme file (auto-generated in most Compose projects)
import com.example.jetpack_compose1.ui.theme.Jetpack_Compose1Theme

class MainActivity : ComponentActivity() {

    // This is our view model — it stores the data and logic
    private val viewModel: ClassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This sets the content of the app using Jetpack Compose
        setContent {
            // This is used to remember if dark mode is on
            var darkMode by remember { mutableStateOf(false) }

            // This wraps our UI with a theme (light or dark)
            Jetpack_Compose1Theme(darkTheme = darkMode) {

                // This fills the whole screen with a background
                Surface(modifier = Modifier.fillMaxSize()) {

                    // This adds padding around everything inside
                    Column(modifier = Modifier.padding(16.dp)) {

                        // Header row with title and dark mode switch
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Class Picker",
                                style = MaterialTheme.typography.headlineMedium
                            )

                            // Dark mode toggle switch
                            Switch(
                                checked = darkMode,
                                onCheckedChange = { darkMode = it }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp)) // Space between sections

                        // Available classes section
                        Text(
                            text = "Available Classes",
                            style = MaterialTheme.typography.titleMedium
                        )

                        // List of all available classes
                        LazyColumn {
                            items(viewModel.availableClasses.size) { index ->

                                // Get the current class
                                val classItem = viewModel.availableClasses[index]

                                // Check if already registered
                                val isAdded = viewModel.registeredClasses.contains(classItem)

                                // Show each class inside a card
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column {
                                            Text(text = classItem.name)
                                            Text(
                                                text = "${classItem.instructor} | ${classItem.time}",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }

                                        // Add button — disabled if already added
                                        Button(
                                            onClick = {
                                                viewModel.registerClass(classItem)
                                            },
                                            enabled = !isAdded
                                        ) {
                                            Text(if (isAdded) "Added" else "Add")
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Section for registered classes
                        Text(
                            text = "Your Classes",
                            style = MaterialTheme.typography.titleMedium
                        )

                        LazyColumn {
                            items(viewModel.registeredClasses.size) { index ->

                                val classItem = viewModel.registeredClasses[index]

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column {
                                            Text(text = classItem.name)
                                            Text(
                                                text = "${classItem.instructor} | ${classItem.time}",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }

                                        // Button to remove the class
                                        Button(
                                            onClick = {
                                                viewModel.deregisterClass(classItem)
                                            }
                                        ) {
                                            Text("Remove")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
