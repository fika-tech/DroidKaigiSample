package tech.fika.droidkaigi.monsterList

import tech.fika.macaron.core.contract.Intent
import tech.fika.droidkaigi.entities.Monster

sealed class MonsterListIntent : Intent {
    data object OnInit : MonsterListIntent()
    data class ClickItem(val monster: Monster) : MonsterListIntent()
    data object OnScrollToBottom : MonsterListIntent()
    data object ClickErrorRetry : MonsterListIntent()
}
