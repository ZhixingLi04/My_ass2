package com.example.myapplication_ass2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_ass2.ui.theme.MyApplication_ass2Theme

data class Medication(
    val name: String,
    val dosage: String,
    val time: String
)

@Composable
fun MainActivity2(navController: NavController, modifier: Modifier = Modifier) {
    val medications = listOf(
        Medication("Aspirin", "1 tablet", "8:00 AM"),
        Medication("Metformin", "500mg", "12:00 PM"),
        Medication("Lisinopril", "10mg", "6:00 PM"),
        Medication("Atorvastatin", "20mg", "9:00 PM")
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.b),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Medication Schedule",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(medications) { medication ->
                    MedicationCard(medication)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("home") }, // 返回 HomeScreen
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Home", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
fun MedicationCard(medication: Medication) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = medication.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Dosage: ${medication.dosage}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Time: ${medication.time}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicationScreenPreview() {
    MyApplication_ass2Theme {
        val navController = rememberNavController()
        MainActivity2(navController)
    }
}
