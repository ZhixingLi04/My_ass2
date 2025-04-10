package com.example.myapplication_ass2

import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

data class RoutineTask(
    val name: String,
    val description: String,
    val time: String
)

@Composable
fun MainActivity3(
    navController: NavController,
    tasks: MutableList<RoutineTask>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("Select Time") }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.b),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Daily Routine Schedule",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Task Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp)
            )

            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            time = String.format("%02d:%02d", hour, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = time)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (name.isNotBlank() && description.isNotBlank() && time != "Select Time") {
                        tasks.add(RoutineTask(name, description, time))
                        name = ""
                        description = ""
                        time = "Select Time"
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Task")
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(tasks) { index, task ->
                    RoutineCard(
                        task = task,
                        onDelete = { tasks.removeAt(index) }
                    )
                }
            }

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Back to Home", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
fun RoutineCard(task: RoutineTask, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(task.name, style = MaterialTheme.typography.titleMedium)
                    Text("Description: ${task.description}", style = MaterialTheme.typography.bodyMedium)
                    Text("Time: ${task.time}", style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
