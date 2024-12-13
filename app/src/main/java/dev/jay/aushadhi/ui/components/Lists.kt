package dev.jay.aushadhi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.jay.aushadhi.R
import dev.jay.aushadhi.database.entity.patient.PatientInfo
import dev.jay.aushadhi.ui.screens.search.Type
import dev.jay.aushadhi.ui.theme.Orange
import dev.jay.aushadhi.utils.ScreenType

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
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 10,
                fontSize = 13.sp,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Composable
fun ListItemMainPatient(navController: NavController, patientInfo: PatientInfo) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(PatientDetailScreen(patientInfo.patientId)) {
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
                text = "${patientInfo.name}(${patientInfo.patientId})",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "${patientInfo.age} year old (${patientInfo.gender})",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = patientInfo.description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif
            )

        }
    }
}

@Composable
fun ListItemSearch(
    title: String,
    description: String,
    type: Type,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    maxLines = 5,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            VerticalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = when (type) {
                    Type.AUSHADHI -> R.drawable.aushadhi
                    Type.DISEASE -> R.drawable.disease
                    Type.PATIENT -> R.drawable.patient
                }),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp),
                colorFilter = ColorFilter.tint(Orange),
            )        }
    }
}
