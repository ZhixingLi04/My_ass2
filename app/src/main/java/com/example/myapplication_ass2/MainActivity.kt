package com.example.myapplication_ass2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_ass2.ui.theme.MyApplication_ass2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication_ass2Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostContainer(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("medication") { MainActivity2(navController) }
        composable("daily_routine") { MainActivity3(navController) }
        composable("appointments") { DailyRoutineScreen(navController) }
        composable("communication") { FamilyCommunicationScreen(navController) } // 添加 Family Communication 页面
    }
}

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.a),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Elderly Care!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            val options = listOf(
                "Medication Schedule" to "medication",
                "Daily Routine Schedule" to "daily_routine",
                "Appointments List" to "appointments",
                "Family Communication" to "communication",
                "App Settings" to "settings"
            )

            options.forEach { (text, route) ->
                OutlinedButton(
                    onClick = { navController.navigate(route) },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplication_ass2Theme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}
