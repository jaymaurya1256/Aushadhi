package dev.vedics.aushadhi.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItemMain(name: String, description: String) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
fun ListItemMainPatient(patientName: String, patientId: Long) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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