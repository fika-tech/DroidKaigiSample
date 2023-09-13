package tech.fika.droidkaigi.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.fika.droidkaigi.data.repository.MockMonsterRepository
import tech.fika.droidkaigi.data.repository.MonsterRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideMonsterRepository(): MonsterRepository = MockMonsterRepository()
}
