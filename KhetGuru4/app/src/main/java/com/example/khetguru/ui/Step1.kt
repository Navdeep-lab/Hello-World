package com.example.khetguru.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun Step1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Light green background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Image with Border & Shadow
            Spacer(modifier = Modifier.height(25.dp))
            Image(
                painter = painterResource(id = R.drawable.farm2), // Your farm image
                contentDescription = "Khet Guru Logo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color(0xFF388E3C), CircleShape)  // Thick green border
                    .shadow(12.dp, CircleShape)  // Shadow for depth
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Title with Gradient Effect
            Text(
                text = "Welcome to Khet Guru",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),  // Dark green title
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Benefits Section
            Text(
                text = "🌿Why Use Khet Guru?",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1B5E20)  // Dark green for contrast
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Benefits List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BenefitItem("🌾 Crop Disease Detection", "Identify and treat crop diseases early.")
                BenefitItem("📈 Market Price Updates", "Get real-time commodity prices.")
                BenefitItem("🌤️ Weather Forecast", "Accurate predictions to plan your farming activities.")
                BenefitItem("🌿 Soil Health Yield Prediction", "Get soil health analysis and crop yield forecasts.")
                BenefitItem("🤖 Expert Advisory", "Consult with agricultural experts through our chatbot.")
                BenefitItem("🏛️ Government Schemes", "Access the latest government schemes and benefits.")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Navigation Button
            Button(
                onClick = { navController.navigate("step2") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)), // Deep green
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

// ✅ Reusable composable for benefits section
@Composable
fun BenefitItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))  // Light green background
            .padding(12.dp)
            .border(2.dp, Color(0xFF4CAF50), RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier.padding(5.dp),
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
fun Step1Preview(){
    Step1(navController = NavController(LocalContext.current))
}
