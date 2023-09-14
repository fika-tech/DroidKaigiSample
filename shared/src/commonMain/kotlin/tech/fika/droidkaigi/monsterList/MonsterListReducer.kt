package tech.fika.droidkaigi.monsterList

import tech.fika.macaron.core.components.Reducer

class MonsterListReducer : Reducer<MonsterListAction, MonsterListState> {
    override suspend fun reduce(action: MonsterListAction, state: MonsterListState): MonsterListState = when (action) {
        is MonsterListAction.Loading -> when (state) {
            is MonsterListState.Initial -> {
                MonsterListState.Loading
            }
            is MonsterListState.Stable.Initial -> {
                MonsterListState.Stable.PageLoading(state.monsterList)
            }
            else -> state
        }
        is MonsterListAction.LoadSuccess -> when (state) {
            is MonsterListState.Loading -> {
                MonsterListState.Stable.Initial(action.monsterList)
            }
            is MonsterListState.Stable.PageLoading -> {
                MonsterListState.Stable.Initial(state.monsterList + action.monsterList)
            }
            else -> state
        }
        is MonsterListAction.LoadError -> when (state) {
            is MonsterListState.Loading -> {
                MonsterListState.Error(action.error)
            }
            is MonsterListState.Stable.PageLoading -> {
                MonsterListState.Stable.PageError(state.monsterList, action.error)
            }
            else -> state
        }
        is MonsterListAction.NavigateDetails -> state
    }
}