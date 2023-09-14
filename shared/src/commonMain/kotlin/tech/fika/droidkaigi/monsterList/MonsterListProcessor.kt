package tech.fika.droidkaigi.monsterList

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.fika.droidkaigi.data.repository.MonsterRepository
import tech.fika.macaron.core.components.Processor

class MonsterListProcessor(
    private val repository: MonsterRepository,
) : Processor<MonsterListIntent, MonsterListAction, MonsterListState> {
    override suspend fun process(intent: MonsterListIntent, state: MonsterListState): Flow<MonsterListAction> = flow {
        when (intent) {
            is MonsterListIntent.OnInit -> when (state) {
                is MonsterListState.Initial -> {
                    loadMonsterList(offset = 0, monsterRepository = repository, emit = ::emit)
                }
                else -> Unit
            }
            is MonsterListIntent.ClickItem -> when (state) {
                is MonsterListState.Stable -> {
                    emit(MonsterListAction.NavigateDetails(intent.monster))
                }
                else -> Unit
            }
            is MonsterListIntent.ClickErrorRetry -> when (state) {
                is MonsterListState.Error -> loadMonsterList(0, repository, ::emit)
                is MonsterListState.Stable.PageError -> {
                    loadMonsterList(offset = state.currentOffset, monsterRepository = repository, emit = ::emit)
                }
                else -> Unit
            }
            is MonsterListIntent.OnScrollToBottom -> when (state) {
                is MonsterListState.Stable.Initial -> {
                    loadMonsterList(offset = state.currentOffset, monsterRepository = repository, emit = ::emit)
                }
                else -> Unit
            }
        }
    }
}

private const val PAGE_LIMIT = 20

private suspend fun loadMonsterList(
    offset: Int,
    monsterRepository: MonsterRepository,
    emit: suspend (MonsterListAction) -> Unit,
) {
    emit(MonsterListAction.Loading)
    runCatching {
        monsterRepository.getMonsters(offset = offset, limit = PAGE_LIMIT)
    }.onSuccess { monsterList ->
        emit(MonsterListAction.LoadSuccess(monsterList = monsterList))
    }.onFailure { error ->
        emit(MonsterListAction.LoadError(error = error))
    }
}
