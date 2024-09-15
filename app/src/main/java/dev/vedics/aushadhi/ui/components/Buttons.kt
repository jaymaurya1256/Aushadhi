package dev.vedics.aushadhi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun AddButton(
    modifier: Modifier = Modifier,
    text: String,
    category: Enum<ButtonType>,
    backgroundColor: Color = Orange,
    contentColor: Color = White,
    size: Dp = 35.dp,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val icon: Painter = painterResource(
            when (category) {
                ButtonType.ADD_DISEASE -> R.drawable.disease
                ButtonType.ADD_PATIENT -> R.drawable.patient
                ButtonType.ADD_AUSHADHI -> R.drawable.aushadhi
                else -> R.drawable.aushadhi
            }
        )
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

@Composable
fun AushadhiTextInputFiled(
    textHint: String,
    modifier: Modifier = Modifier,
    height: Dp = 50.dp,
    width: Dp = 500.dp
) {
    var text by remember { mutableStateOf("") }
    TextField   (
        value = text,
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        label = { Text(text = textHint) },
        onValueChange = {text = it}
    )
}

@Preview
@Composable
private fun Preview() {
    AddButton(text = "Add +", category = ButtonType.ADD_AUSHADHI) {

    }
}