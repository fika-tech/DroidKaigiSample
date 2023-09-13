package tech.fika.droidkaigi.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.fika.macaron.core.factory.DefaultStoreFactory
import tech.fika.macaron.core.factory.StoreFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideStoreFactory(): StoreFactory = DefaultStoreFactory()
}