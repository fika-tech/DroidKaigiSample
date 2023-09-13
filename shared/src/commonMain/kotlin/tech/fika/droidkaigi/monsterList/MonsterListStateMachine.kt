package tech.fika.droidkaigi.monsterList

import tech.fika.macaron.statemachine.components.StateMachine
import tech.fika.droidkaigi.data.repository.MonsterRepository

class MonsterListStateMachine(
    private val monsterRepository: MonsterRepository,
) : StateMachine<MonsterListIntent, MonsterListAction, MonsterListState>(
    builder = {
        state<MonsterListState.Initial> {
            process<MonsterListIntent.OnInit> {
                loadMonsterList(offset = 0, monsterRepository = monsterRepository, emit = ::emit)
            }
            reduce<MonsterListAction.Loading> {
                MonsterListState.Loading
            }
        }

        state<MonsterListState.Loading> {
            reduce<MonsterListAction.LoadSuccess> {
                MonsterListState.Stable.Initial(monsterList = action.monsterList)
            }
            reduce<MonsterListAction.LoadError> {
                MonsterListState.Error(error = action.error)
            }
        }

        state<MonsterListState.Stable> {
            process<MonsterListIntent.ClickItem> {
                emit(MonsterListAction.NavigateMonsterDetails(monster = intent.monster))
            }
        }

        state<MonsterListState.Stable.Initial> {
            process<MonsterListIntent.OnScrollToBottom> {
                loadMonsterList(offset = state.currentOffset, monsterRepository = monsterRepository, emit = ::emit)
            }
            reduce<MonsterListAction.Loading> {
                MonsterListState.Stable.PageLoading(monsterList = state.monsterList)
            }
        }

        state<MonsterListState.Stable.PageLoading> {
            reduce<MonsterListAction.LoadSuccess> {
                MonsterListState.Stable.Initial(monsterList = state.monsterList + action.monsterList)
            }
            reduce<MonsterListAction.LoadError> {
                MonsterListState.Stable.PageError(monsterList = state.monsterList, error = action.error)
            }
        }

        state<MonsterListState.Stable.PageError> {
            process<MonsterListIntent.ClickErrorRetry> {
                loadMonsterList(offset = state.currentOffset, monsterRepository = monsterRepository, emit = ::emit)
            }
            reduce<MonsterListAction.Loading> {
                MonsterListState.Stable.PageLoading(monsterList = state.monsterList)
            }
        }

        state<MonsterListState.Error> {
            process<MonsterListIntent.ClickErrorRetry> {
                loadMonsterList(offset = 0, monsterRepository = monsterRepository, emit = ::emit)
            }
        }
    }
)

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
