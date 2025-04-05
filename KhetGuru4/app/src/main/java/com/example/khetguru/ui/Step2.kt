package com.example.khetguru.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.khetguru.R

@Composable
fun Step2(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)  // Light greenish-yellow background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(14.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Image or Illustration
            Spacer(modifier = Modifier.height(25.dp))
            Image(
                painter = painterResource(id = R.drawable.farm1),  // Your farm image
                contentDescription = "Khet Guru Logo",
                modifier = Modifier
                    .size(200.dp)  // Larger size for better impact
                    .clip(CircleShape)
                    .border(4.dp, Color(0xFF388E3C), CircleShape)  // Thick green border
                    .shadow(12.dp, CircleShape)  // Enhanced shadow for depth
            )
Spacer(modifier=Modifier.height(20.dp))
            // Title
            Text(
                text = "How to Use Khet Guru📱",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF388E3C)  // Dark green text
            )

            Spacer(modifier = Modifier.height(20.dp))

            // User Guide Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                UserGuideStep(
                    icon = "🌾",
                    title = "1. Crop Disease Detection",
                    description = "Upload a photo of your crop to identify diseases and get treatment recommendations."
                )
                UserGuideStep(
                    icon = "📈",
                    title = "2. Market Price Updates",
                    description = "Check the latest market prices for various commodities to make informed selling decisions."
                )
                UserGuideStep(
                    icon = "🌤️",
                    title = "3. Weather Forecast",
                    description = "Get real-time weather updates to plan your farming activities effectively."
                )
                UserGuideStep(
                    icon = "🌿",
                    title = "4. Soil Health Yield Prediction",
                    description = "Get soil health analysis and predictions on crop yield to improve productivity."
                )
                UserGuideStep(
                    icon = "🧑‍🏫",
                    title = "5. Expert Advisory",
                    description = "Consult with agricultural experts and get professional advice through the in-app chatbot."
                )
                UserGuideStep(
                    icon = "🏛️",
                    title = "6. Government Schemes",
                    description = "Access detailed information on the latest government schemes and benefits for farmers."
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Navigation Button
            Button(
                onClick = { navController.navigate("dashboard") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)), // Deep green button
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Explore Dashboard",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

// ✅ Reusable composable for guide steps
@Composable
fun UserGuideStep(icon: String, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
            .padding(12.dp)
            .border(2.dp, Color(0xFF4CAF50), RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            fontSize = 28.sp,
            modifier = Modifier.padding(12.dp)
        )
        Column {
            Text(
                modifier = Modifier.padding(3.dp),
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = description,
                fontSize = 16.sp,
                color = Color(0xFF4CAF50)
            )
        }
    }
}
@Preview
@Composable
fun Step2Preview(){
    Step2(navController = NavController(LocalContext.current))
}