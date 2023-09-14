package tech.fika.droidkaigi.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.fika.droidkaigi.android.ui.foundation.MonsterTheme

@Composable
fun PageErrorItem(
    error: Throwable,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClickRetry() }) {
        Text(
            text = error.message ?: "An error has occurred",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .padding()
        )
        Button(
            onClick = onClickRetry,
            shape = RoundedCornerShape(size = 4.dp),
        ) {
            Text(text = "Retry")
        }
    }
}

@Preview
@Composable
private fun PreviewPageErrorItem() {
    MonsterTheme {
        PageErrorItem(
            error = Throwable("An error has occurred"),
            modifier = Modifier.background(color = Color.White)
        ) { }
    }
}