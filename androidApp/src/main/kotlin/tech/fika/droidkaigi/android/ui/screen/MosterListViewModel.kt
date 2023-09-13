package tech.fika.droidkaigi.android.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import tech.fika.macaron.core.components.Store
import tech.fika.macaron.core.factory.StoreFactory
import tech.fika.macaron.logging.DefaultLogger
import tech.fika.macaron.logging.LoggingMiddleware
import tech.fika.droidkaigi.android.core.StoreViewModel
import tech.fika.macaron.statemachine.components.StateMachine
import tech.fika.droidkaigi.monsterList.MonsterListAction
import tech.fika.droidkaigi.monsterList.MonsterListIntent
import tech.fika.droidkaigi.monsterList.MonsterListState

@HiltViewModel
class MonsterListViewModel @Inject constructor(
    storeFactory: StoreFactory,
    stateMachine: StateMachine<MonsterListIntent, MonsterListAction, MonsterListState>,
) : StoreViewModel<MonsterListIntent, MonsterListAction, MonsterListState>() {
    override val store: Store<MonsterListIntent, MonsterListAction, MonsterListState> = storeFactory.create(
        initialState = MonsterListState.Initial,
        processor = stateMachine.processor,
        reducer = stateMachine.reducer,
        middlewares = listOf(
            LoggingMiddleware(logger = DefaultLogger()),
        ),
        coroutineContext = viewModelScope.coroutineContext,
    )
}
