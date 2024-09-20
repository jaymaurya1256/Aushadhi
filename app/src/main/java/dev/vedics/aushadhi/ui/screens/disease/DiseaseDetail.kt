package dev.vedics.Disease.ui.screens.disease

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.vedics.aushadhi.ui.screens.disease.DiseaseViewModel

@Composable
fun DiseaseDetail(navController: NavController, id: Int, viewModel: DiseaseViewModel = hiltViewModel()) {

    val disease = viewModel.getDiseaseById(id)
    val name = remember { mutableStateOf("") }
    val description =  remember { mutableStateOf("") }

    LaunchedEffect(key1 = disease) {
        disease.collect {
            name.value = it.name
            description.value = it.description
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name.value,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = description.value,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }


}