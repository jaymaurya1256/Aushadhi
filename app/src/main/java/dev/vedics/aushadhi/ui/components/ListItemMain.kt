package dev.vedics.aushadhi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.vedics.aushadhi.utils.ScreenType

@Composable
fun ListItemMain(id: Int, name: String, description: String, navController: NavController, screenType: ScreenType) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable {
                when (screenType) {
                    ScreenType.AUSHADHI -> {
                        navController.navigate(AushadhiDetailScreen(id = id)) {
                            launchSingleTop = true
                        }
                    }

                    ScreenType.DISEASE -> {
                        navController.navigate(DiseaseDetailScreen(id = id)) {
                            launchSingleTop = true
                        }
                    }
                    else -> {
                        // Do nothing
                    }
                }
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 10,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}

@Composable
fun ListItemMainPatient(navController: NavController, patientName: String, patientId: Long) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(PatientDetailScreen(patientId)) {
                    launchSingleTop = true
                }
            },
        elevation = CardDefaults.cardElevation(4.dp)
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = patientName,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Patient id $patientId",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}