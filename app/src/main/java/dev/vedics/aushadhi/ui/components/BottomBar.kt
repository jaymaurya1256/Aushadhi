package dev.vedics.aushadhi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.vedics.aushadhi.R
import dev.vedics.aushadhi.ui.theme.Orange
import dev.vedics.aushadhi.utils.AUSHADHI_SCREEN
import dev.vedics.aushadhi.utils.DISEASE_SCREEN
import dev.vedics.aushadhi.utils.PATIENT_SCREEN

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(
                topStart = 16.dp, topEnd = 16.dp
            ))
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(AUSHADHI_SCREEN)
                },
            painter = painterResource(id = R.drawable.aushadhi),
            contentDescription = "Aushadhi",
            tint = Orange
        )
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(DISEASE_SCREEN)
                },
            painter = painterResource(id = R.drawable.disease),
            contentDescription = "Disease",
            tint = Orange
        )
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(PATIENT_SCREEN)
                },
            painter = painterResource(id = R.drawable.patient),
            contentDescription = "Patient",
            tint = Orange
        )
    }
}