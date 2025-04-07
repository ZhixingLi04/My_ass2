package com.example.myapplication_ass2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_ass2.ui.theme.MyApplication_ass2Theme

class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication_ass2Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FamilyCommunicationScreen(navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Message(val sender: String, val text: String)

val chatMessages = listOf(
    Message("Dad", "Had a great lunch today!"),
    Message("Mom", "Don't forget to take your medicine at 6 PM!"),
    Message("Me", "Got it, thanks!"),
    Message("Son", "I will visit this weekend!"),
    Message("Me", "Looking forward to it!"),
    Message("Daughter", "Sent you some new pictures!"),
    Message("Grandson", "Love you grandpa! ❤️")
)

@Composable
fun FamilyCommunicationScreen(navController: NavController, modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Family Communication",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chatMessages) { message ->
                ChatBubble(message)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(Color.LightGray)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* 发送消息逻辑 */ }) {
                Text("Send")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Home", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    val isMe = message.sender == "Me"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .background(Color.Transparent),
            colors = CardDefaults.cardColors(
                containerColor = if (isMe) Color(0xFFBBDEFB) else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text = "${if (isMe) "" else message.sender + ": "}${message.text}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FamilyCommunicationScreenPreview() {
    MyApplication_ass2Theme {
        val navController = rememberNavController()
        FamilyCommunicationScreen(navController)
    }
}
