package by.bsuir.gameslist.di

import by.bsuir.gameslist.service.AuthenticationService
import by.bsuir.gameslist.service.GameService
import by.bsuir.gameslist.service.implementations.AuthenticationServiceImpl
import by.bsuir.gameslist.service.implementations.GameServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    @Binds
    @Singleton
    internal abstract fun bindAuthenticationService(
        authenticationService: AuthenticationServiceImpl
    ): AuthenticationService

    @Binds
    @Singleton
    internal abstract fun bindGameService(
        gameService: GameServiceImpl
    ): GameService
}