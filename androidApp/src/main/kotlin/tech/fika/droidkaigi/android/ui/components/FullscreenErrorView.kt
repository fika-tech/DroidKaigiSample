package tech.fika.droidkaigi.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pokedex.android.R
import tech.fika.droidkaigi.android.ui.foundation.MonsterTheme

@Composable
fun FullscreenErrorView(
    error: Throwable,
    onClickRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(all = 48.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.error_monster),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(ratio = 1f)
                    .widthIn(max = 200.dp)
                    .fillMaxWidth()
            )
            Text(
                text = error.message ?: "An error has occurred",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Button(
                onClick = onClickRetry,
                shape = RoundedCornerShape(size = 4.dp),
            ) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview(widthDp = 375, showBackground = true, backgroundColor = 0xffffff)
@Composable
internal fun PreviewErrorRetryView() {
    MonsterTheme {
        FullscreenErrorView(
            error = Throwable("An error has occurred"),
            onClickRetry = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
