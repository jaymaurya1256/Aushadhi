package dev.jay.aushadhi.utils

import android.content.Context
import android.os.Build
import android.util.TypedValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun <T> oneShotFlow() = MutableSharedFlow<T>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

fun DrawScope.drawPaths(paths: List<Path>) {
    paths.forEach { path ->
        drawPath(path, Color.Black, style = Stroke(4f))
    }
}

fun dpToPx(context: Context, dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}

fun timeInMilliToDate(timeInMillis: Long): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
        val formattedDate = formatter.format(Instant.ofEpochMilli(timeInMillis))
        addDaySuffix(formattedDate)
    } else {
        val dateFormat = SimpleDateFormat("d MMMM yyyy, h:mm a", Locale.getDefault())
        val date = Date(timeInMillis)
        val formattedDate = dateFormat.format(date)
        addDaySuffix(formattedDate)
    }
}

private fun addDaySuffix(formattedDate: String): String {
    val day = formattedDate.split(" ")[0].toInt()
    val suffix = when (day % 10) {
        1 -> if (day == 11) "th" else "st"
        2 -> if (day == 12) "th" else "nd"
        3 -> if (day == 13) "th" else "rd"
        else -> "th"
    }
    return formattedDate.replaceFirst(Regex("\\d+"), "$day$suffix")
}