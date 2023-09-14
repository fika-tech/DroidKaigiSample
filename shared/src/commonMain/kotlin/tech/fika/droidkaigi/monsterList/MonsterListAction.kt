package tech.fika.droidkaigi.monsterList

import tech.fika.macaron.core.contract.Action
import tech.fika.droidkaigi.entities.Monster

sealed class MonsterListAction : Action {
    data object Loading : MonsterListAction()
    data class LoadSuccess(val monsterList: List<Monster>) : MonsterListAction()
    data class LoadError(val error: Throwable) : MonsterListAction()
    data class NavigateDetails(val monster: Monster) : MonsterListAction(), Action.Event
}
