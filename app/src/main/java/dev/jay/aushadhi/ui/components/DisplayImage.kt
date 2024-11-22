package dev.jay.aushadhi.ui.components

import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun DisplayImage(filePath: String, sampleImageRes: Int, modifier: Modifier = Modifier, size: Dp = 80.dp ) {
    val file = File(filePath)

    val imageBitmap = if (file.exists()) {
        BitmapFactory.decodeFile(filePath)?.asImageBitmap()
    } else {
        null
    }

    if (imageBitmap != null) {
        Image(
            painter = BitmapPainter(imageBitmap),
            contentDescription = null,
            modifier = modifier.padding(1.dp).size(size)
        )
    } else {
        Image(
            painter = painterResource(id = sampleImageRes),
            contentDescription = null,
            modifier = modifier.size(size).padding(1.dp)
        )
    }
}