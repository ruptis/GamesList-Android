package by.bsuir.gameslist.di

import by.bsuir.gameslist.service.GameService
import by.bsuir.gameslist.service.MockGameService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GameModule {
    @Binds
    @Singleton
    internal abstract fun bindGameService(
        gameService: MockGameService
    ): GameService
}