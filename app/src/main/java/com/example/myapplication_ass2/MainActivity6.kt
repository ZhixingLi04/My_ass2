package com.example.myapplication_ass2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(navController: NavHostController) {
    var fontSize by remember { mutableStateOf(16f) }
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var isDarkMode by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    val languages = listOf("English", "中文", "Bahasa Melayu")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Font size
        Text("Font Size Preview", fontSize = fontSize.sp)
        Slider(
            value = fontSize,
            onValueChange = { fontSize = it },
            valueRange = 12f..30f,
            steps = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Language selection
        Text("Language:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedLanguage)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = { Text(language) },
                        onClick = {
                            selectedLanguage = language
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Dark mode switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dark Mode", modifier = Modifier.weight(1f), fontSize = 18.sp)
            Switch(checked = isDarkMode, onCheckedChange = { isDarkMode = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable Notifications", modifier = Modifier.weight(1f), fontSize = 18.sp)
            Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Help center
        Button(
            onClick = { /* Show help or navigate */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Help Center")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // About section
        Text("App Version: 1.0.0", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("Developed by Team ElderCare", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(24.dp))

        // Logout
        Button(
            onClick = { /* Simulate logout */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text("Logout", color = MaterialTheme.colorScheme.onError)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Back to home
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Back to Home", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
