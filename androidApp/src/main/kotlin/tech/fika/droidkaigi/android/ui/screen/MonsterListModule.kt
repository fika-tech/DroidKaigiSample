package tech.fika.droidkaigi.android.ui.screen

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import tech.fika.macaron.statemachine.components.StateMachine
import tech.fika.droidkaigi.data.repository.MonsterRepository
import tech.fika.droidkaigi.monsterList.MonsterListAction
import tech.fika.droidkaigi.monsterList.MonsterListIntent
import tech.fika.droidkaigi.monsterList.MonsterListState
import tech.fika.droidkaigi.monsterList.MonsterListStateMachine

@Module
@InstallIn(ActivityRetainedComponent::class)
class MonsterListModule {
    @Provides
    fun provideMonsterListStateMachine(
        monsterRepository: MonsterRepository,
    ): StateMachine<MonsterListIntent, MonsterListAction, MonsterListState> = MonsterListStateMachine(monsterRepository = monsterRepository)
}
