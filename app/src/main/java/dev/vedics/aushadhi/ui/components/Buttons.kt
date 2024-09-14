package dev.vedics.aushadhi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import dev.vedics.aushadhi.ui.theme.Orange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vedics.aushadhi.R
import dev.vedics.aushadhi.ui.theme.White
import dev.vedics.aushadhi.utils.ButtonType


@Composable
fun Add(
    modifier: Modifier = Modifier,
    text: String,
    category: Enum<ButtonType>,
    bgColor: Color = Orange,
    contentColor: Color = White,
    size: Dp = 35.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = bgColor, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        val icon: Painter = when (category) {
            ButtonType.ADD_DISEASE -> {
                painterResource(id = R.drawable.disease)
            }

            ButtonType.ADD_PATIENT -> {
                painterResource(id = R.drawable.patient)
            }

            ButtonType.ADD_AUSHADHI -> {
                painterResource(id = R.drawable.aushadhi)
            }

            else -> {
                painterResource(id = R.drawable.disease)
            }
        }
        Column {
            Image(
                painter = icon,
                contentDescription = text,
                modifier = Modifier.size(size),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = text,
                color = contentColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    Add(text = "Add +", category = ButtonType.ADD_AUSHADHI) {

    }
}