
package com.shapla.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DeepGreen = Color(0xFF0B4636)
private val LeafGreen = Color(0xFF10B981)
private val Background = Color(0xFFF0F2F5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = DeepGreen,
                    secondary = LeafGreen,
                    background = Background
                )
            ) {
                ShaplaApp()
            }
        }
    }
}

@Composable
fun ShaplaApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val items = listOf(
                    Triple("Home", Icons.Default.Home, 0),
                    Triple("Friends", Icons.Default.People, 1),
                    Triple("Job", Icons.Default.Work, 2),
                    Triple("Vault", Icons.Default.Lock, 3),
                    Triple("Settings", Icons.Default.Settings, 4)
                )

                items.forEach { (label, icon, index) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label, fontSize = 10.sp) }
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background)
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> SimplePage(Icons.Default.People, "Friends", "বন্ধু ও Friend Requests")
                2 -> SimplePage(Icons.Default.Work, "Job & Work", "কাজ খুঁজুন অথবা সেবা দিন")
                3 -> SimplePage(Icons.Default.Lock, "Secure Vault", "ব্যক্তিগত তথ্যের নিরাপদ ভান্ডার")
                4 -> SimplePage(Icons.Default.Settings, "Settings", "অ্যাপের সেটিংস")
            }
        }
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .statusBarsPadding()
                    .padding(18.dp)
            ) {
                Text(
                    "SHAPLA",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DeepGreen
                )
                Text(
                    "কাজ, সেবা, সব একসাথে।",
                    color = LeafGreen,
                    fontSize = 14.sp
                )
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("স্বাগতম শাপলায়!", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text("আপনার সামাজিক যোগাযোগ ও কাজের নতুন ঠিকানা।")
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AssistChip(
                            onClick = {},
                            label = { Text("ছবি") },
                            leadingIcon = { Icon(Icons.Default.Image, null) }
                        )
                        AssistChip(
                            onClick = {},
                            label = { Text("পোস্ট") },
                            leadingIcon = { Icon(Icons.Default.Edit, null) }
                        )
                    }
                }
            }
        }

        item {
            PostCard(
                author = "Shapla Community",
                text = "শাপলা অ্যাপে সবাইকে স্বাগতম! এখানে কাজ, সেবা ও সামাজিক যোগাযোগ একসাথে আনার লক্ষ্য নিয়ে আমরা এগিয়ে যাচ্ছি।"
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Designed & Owned by", color = Color.Gray, fontSize = 12.sp)
                Text("Mohammad Israfil Hossain", color = DeepGreen, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PostCard(author: String, text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(DeepGreen.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, tint = DeepGreen)
                }

                Spacer(Modifier.width(10.dp))

                Column {
                    Text(author, fontWeight = FontWeight.Bold)
                    Text("Shapla", color = Color.Gray, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(text, lineHeight = 21.sp)
            Spacer(Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("👍 লাইক")
                Text("💬 মন্তব্য")
                Text("↗ শেয়ার")
            }
        }
    }
}

@Composable
fun SimplePage(icon: ImageVector, title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(50.dp))
        Icon(icon, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(56.dp))
        Spacer(Modifier.height(16.dp))
        Text(title, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = DeepGreen)
        Spacer(Modifier.height(8.dp))
        Text(subtitle, color = Color.Gray)
    }
}
