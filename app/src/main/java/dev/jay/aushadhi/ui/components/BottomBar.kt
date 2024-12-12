package dev.jay.aushadhi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.jay.aushadhi.R
import dev.jay.aushadhi.database.entity.patient.PatientInfo
import dev.jay.aushadhi.database.entity.patient.Visit
import dev.jay.aushadhi.ui.theme.Orange
import dev.jay.aushadhi.utils.ScreenType

@Composable
fun BottomNavigationBar(
    navController: NavController,
    screenType: ScreenType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitPointerEvent()
                    }
                }
            }
            .background(Color.Transparent)
            .fillMaxWidth()
            .background(
                Color.White, RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp
                )
            )
            .navigationBarsPadding()
            .padding(top = 16.dp, start = 4.dp, end = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(AushadhiScreen) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
                .background(
                    if (screenType == ScreenType.AUSHADHI) Orange else Color.Transparent,
                    CircleShape
                )
                .padding(6.dp),
            painter = painterResource(id = R.drawable.aushadhi),
            contentDescription = "Aushadhi",
            tint = if (screenType == ScreenType.AUSHADHI) Color.White else Orange
        )
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(SearchScreen) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
                .background(
                    if (screenType == ScreenType.SEARCH) Orange else Color.Transparent,
                    CircleShape
                )
                .padding(6.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            tint = if (screenType == ScreenType.SEARCH) Color.White else Orange
        )
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(DiseaseScreen) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
                .background(
                    if (screenType == ScreenType.DISEASE) Orange else Color.Transparent,
                    CircleShape
                )
                .padding(6.dp),
            painter = painterResource(id = R.drawable.disease),
            contentDescription = "Disease",
            tint = if (screenType == ScreenType.DISEASE) Color.White else Orange
        )
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(PatientScreen) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
                .background(
                    if (screenType == ScreenType.PATIENT) Orange else Color.Transparent,
                    CircleShape
                )
                .padding(6.dp),
            painter = painterResource(id = R.drawable.patient),
            contentDescription = "Patient",
            tint = if (screenType == ScreenType.PATIENT) Color.White else Orange
        )
    }
}

@Composable
fun BottomBarPrescription(modifier: Modifier = Modifier, onClickClean : () -> Unit, onClickUndo : () -> Unit, onClickSave : () -> Unit, onClickPrint : () -> Unit) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(8.dp)) {
        Row(
            modifier = modifier
                .background(Color.White)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(top = 16.dp, start = 4.dp, end = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Icon(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { onClickClean() }
                    .background(Orange, CircleShape)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.clear),
                contentDescription = "Prescription",
                tint = Color.White
            )
            Icon(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { onClickUndo() }
                    .background(Orange, CircleShape)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.undo),
                contentDescription = "Visits",
                tint = Color.White
            )
            Icon(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { onClickSave() }
                    .background(Orange, CircleShape)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.save),
                contentDescription = "Visits",
                tint = Color.White
            )
            Icon(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { onClickPrint() }
                    .background(Orange, CircleShape)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.printer),
                contentDescription = "Aushadhi",
                tint = Color.White
            )
        }
    }
}