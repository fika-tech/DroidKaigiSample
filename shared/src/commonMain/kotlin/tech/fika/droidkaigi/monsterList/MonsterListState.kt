package tech.fika.droidkaigi.monsterList

import tech.fika.macaron.core.contract.State
import tech.fika.droidkaigi.entities.Monster

sealed class MonsterListState : State {

    data object Initial : MonsterListState()

    data object Loading : MonsterListState()

    sealed class Stable : MonsterListState() {
        abstract val monsterList: List<Monster>
        internal val currentOffset: Int get() = monsterList.size

        data class Initial(
            override val monsterList: List<Monster>,
        ) : Stable()

        data class PageLoading(
            override val monsterList: List<Monster>,
        ) : Stable()

        data class PageError(
            override val monsterList: List<Monster>,
            val error: Throwable,
        ) : Stable()
    }

    data class Error(
        val error: Throwable,
    ) : MonsterListState()
}
