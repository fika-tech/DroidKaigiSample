package tech.fika.droidkaigi.android.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import tech.fika.droidkaigi.android.core.Contract
import tech.fika.droidkaigi.android.core.handleEvents
import tech.fika.droidkaigi.android.core.render
import tech.fika.droidkaigi.android.core.renderItems
import tech.fika.droidkaigi.android.ui.components.FullscreenErrorView
import tech.fika.droidkaigi.android.ui.components.LoadingIndicator
import tech.fika.droidkaigi.android.ui.components.MonsterListItem
import tech.fika.droidkaigi.android.ui.components.PageErrorItem
import tech.fika.droidkaigi.android.ui.components.PageLoadingIndicatorItem
import tech.fika.droidkaigi.android.utils.onScrolledToBottom
import tech.fika.droidkaigi.android.utils.screenPadding
import tech.fika.droidkaigi.monsterList.MonsterListAction
import tech.fika.droidkaigi.monsterList.MonsterListIntent
import tech.fika.droidkaigi.monsterList.MonsterListState
import timber.log.Timber

@Composable
fun MonsterListScreen(
    contract: Contract<MonsterListIntent, MonsterListAction, MonsterListState>,
) {
    LaunchedEffect(Unit) {
        contract.dispatch(MonsterListIntent.OnInit)
    }

    contract.handleEvents { action ->
        when (action) {
            is MonsterListAction.NavigateMonsterDetails -> Timber.d("Handle Navigation")
            else -> Unit
        }
    }

    MonsterListContent(state = contract.state, dispatch = contract.dispatch)
}

@Composable
fun MonsterListContent(state: MonsterListState, dispatch: (MonsterListIntent) -> Unit) {
    Scaffold(modifier = Modifier.screenPadding()) { contentPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = contentPadding)) {
            state.render<MonsterListState.Loading> { LoadingIndicator() }

            state.render<MonsterListState.Stable> {
                val lazyListState = rememberLazyListState().apply {
                    onScrolledToBottom { dispatch(MonsterListIntent.OnScrollToBottom) }
                }
                LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
                    items(items = monsterList) { monster ->
                        MonsterListItem(name = monster.name,
                            imageUrl = monster.imageUrl,
                            onClick = { dispatch(MonsterListIntent.ClickItem(monster = monster)) })
                    }
                    state.renderItems<MonsterListState.Stable.PageLoading> {
                        item { PageLoadingIndicatorItem() }
                    }
                    state.renderItems<MonsterListState.Stable.PageError> {
                        item {
                            PageErrorItem(error = it.error,
                                onClickRetry = { dispatch(MonsterListIntent.ClickErrorRetry) })
                        }
                    }
                }
            }
            state.render<MonsterListState.Error> {
                FullscreenErrorView(error = error, onClickRetry = { dispatch(MonsterListIntent.ClickErrorRetry) })
            }
        }
    }
}

