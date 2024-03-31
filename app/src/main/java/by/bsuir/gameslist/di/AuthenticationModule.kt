package by.bsuir.gameslist.di

import by.bsuir.gameslist.service.AuthenticationService
import by.bsuir.gameslist.service.MockAuthenticationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {
    @Binds
    @Singleton
    abstract fun bindAuthenticationService(
        authenticationService: MockAuthenticationService
    ): AuthenticationService
}