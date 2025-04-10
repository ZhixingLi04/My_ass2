package com.example.myapplication_ass2

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Medication(
    val name: String,
    val dosage: String,
    val time: String
)

@Composable
fun MainActivity2(
    navController: NavController,
    checkedStates: MutableList<Boolean>,
    modifier: Modifier = Modifier
) {
    val medications = listOf(
        Medication("Aspirin", "1 tablet", "8:00 AM"),
        Medication("Metformin", "500mg", "12:00 PM"),
        Medication("Lisinopril", "10mg", "6:00 PM"),
        Medication("Atorvastatin", "20mg", "9:00 PM")
    )

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.b),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Medication Schedule",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(medications) { index, medication ->
                    AnimatedVisibility(
                        visible = !checkedStates[index],
                        exit = fadeOut(animationSpec = tween(500)) + shrinkVertically(animationSpec = tween(500))
                    ) {
                        MedicationCard(
                            medication = medication,
                            onDone = { checkedStates[index] = true }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    "Back to Home",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun MedicationCard(
    medication: Medication,
    onDone: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = medication.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Dosage: ${medication.dosage}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Time: ${medication.time}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = {
                    isClicked = true
                    onDone()
                },
                modifier = Modifier.padding(start = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // ✅ 绿色按钮
            ) {
                Text("Done")
            }
        }
    }
}
