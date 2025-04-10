package com.example.myapplication_ass2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

import com.example.myapplication_ass2.ui.theme.MyApplication_ass2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val settingsViewModel: AppSettingsViewModel = viewModel()

            val checkedStates = remember { mutableStateListOf(false, false, false, false) }

            val chatMessages = remember {
                mutableStateListOf(
                    Message("Dad", "Had a great lunch today!"),
                    Message("Mom", "Don't forget to take your medicine at 6 PM!"),
                    Message("Me", "Got it, thanks!"),
                    Message("Son", "I will visit this weekend!"),
                    Message("Me", "Looking forward to it!"),
                    Message("Daughter", "Sent you some new pictures!"),
                    Message("Grandson", "Love you grandpa! ❤️")
                )
            }

            val routineTasks = remember { mutableStateListOf<RoutineTask>() }

            val doctorRoutineTasks = remember {
                mutableStateListOf(
                    "07:00 - Wake up & light stretching",
                    "07:30 - Wash up & healthy breakfast",
                    "08:30 - Morning walk (outdoor or balcony)",
                    "10:00 - Reading / Puzzle / Brain exercise",
                    "12:00 - Healthy lunch (low salt, balanced)",
                    "13:00 - Nap (30–45 mins)",
                    "14:00 - Light activity (Tai Chi / gardening)",
                    "16:00 - Family video call / watch TV",
                    "18:00 - Dinner (light & easy to digest)",
                    "20:00 - Listen to music / reading",
                    "21:30 - Get ready for bed",
                    "22:00 - Sleep (consistent schedule)"
                )
            }

            MyApplication_ass2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostContainer(
                        navController = navController,
                        settingsViewModel = settingsViewModel,
                        checkedStates = checkedStates,
                        chatMessages = chatMessages,
                        routineTasks = routineTasks,
                        doctorRoutineTasks = doctorRoutineTasks,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    settingsViewModel: AppSettingsViewModel,
    checkedStates: MutableList<Boolean>,
    chatMessages: MutableList<Message>,
    routineTasks: MutableList<RoutineTask>,
    doctorRoutineTasks: MutableList<String>,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, settingsViewModel)
        }
        composable("settings") {
            SettingsScreen(navController, settingsViewModel)
        }
        composable("medication") {
            MainActivity2(navController, checkedStates)
        }
        composable("daily_routine") {
            MainActivity3(navController, routineTasks)
        }
        composable("appointments") {
            DailyRoutineScreen(navController, doctorRoutineTasks)
        }
        composable("communication") {
            FamilyCommunicationScreen(navController, chatMessages)
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    settingsViewModel: AppSettingsViewModel
) {
    val fontSize = settingsViewModel.fontSize.value

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.a),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5f
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.85f),
                            Color.White.copy(alpha = 0.4f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Elderly Care",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color(0xFF2D2D2D)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome to Elderly Care!",
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            val options = listOf(
                Triple("Medication Schedule", "medication", Icons.Default.MedicalServices),
                Triple("Daily Routine Schedule", "daily_routine", Icons.Default.List),
                Triple("Appointments List", "appointments", Icons.Default.DateRange),
                Triple("Family Communication", "communication", Icons.Default.Phone),
                Triple("App Settings", "settings", Icons.Default.Settings)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                options.forEach { (text, route, icon) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(vertical = 10.dp)
                            .height(64.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        onClick = { navController.navigate(route) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(icon, contentDescription = null, tint = Color(0xFF3A3A3A))
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = text,
                                fontSize = fontSize.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
