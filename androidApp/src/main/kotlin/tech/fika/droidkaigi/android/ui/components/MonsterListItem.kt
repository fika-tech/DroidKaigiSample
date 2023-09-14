package tech.fika.droidkaigi.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import tech.fika.droidkaigi.android.ui.foundation.MonsterTheme
import tech.fika.droidkaigi.data.repository.MockMonsterRepository
import tech.fika.droidkaigi.monsterList.MonsterListStateMachine
import tech.fika.macaron.statemachine.components.StateMachineProcessor
import tech.fika.macaron.statemachine.components.StateMachineReducer

@Composable
fun MonsterListItem(
    name: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(36.dp),
        )
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .padding()
        )
    }
}

@Preview
@Composable
fun PreviewMonsterListItem() {
    MonsterTheme {
        MonsterListItem(
            name = "Name",
            imageUrl = "",
            modifier = Modifier.background(Color.White)
        )
    }
}