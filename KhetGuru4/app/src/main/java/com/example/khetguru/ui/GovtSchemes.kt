package com.example.khetguru.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Data class for schemes
data class Scheme(
    val title: String,
    val description: String,
    val link: String
)

@Composable
fun GovernmentSchemesScreen(navController: NavController) {
    val schemes = listOf(
        Scheme(
            "PM-Kisan Samman Nidhi",
            "Financial assistance to farmers with direct income support of ₹6,000 per year.",
            "https://pmkisan.gov.in/"
        ),
        Scheme(
            "Soil Health Card Scheme",
            "Provides farmers with soil health reports to improve crop productivity.",
            "https://soilhealth.dac.gov.in/"
        ),
        Scheme(
            "Pradhan Mantri Fasal Bima Yojana",
            "Insurance coverage for crop failures due to natural calamities.",
            "https://pmfby.gov.in/"
        ),
        Scheme(
            "National Agriculture Market (e-NAM)",
            "Online trading platform for better price discovery and transparency.",
            "https://www.enam.gov.in/"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8E9)) // Light green background
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Government Schemes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(schemes) { scheme ->
                SchemeCard(scheme)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun SchemeCard(scheme: Scheme) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = scheme.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = scheme.description,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            // "Learn More" Button with Null Checks and URL Validation
            Button(
                onClick = {
                    if (context != null && scheme.link.startsWith("http")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scheme.link))
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Failed to open link", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Invalid URL or Context", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                modifier = Modifier
                    .align(Alignment.End)
                    .height(40.dp)
            ) {
                Text(text = "Learn More", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGovernmentSchemesScreen() {
    val navController = rememberNavController()
    GovernmentSchemesScreen(navController)
}
