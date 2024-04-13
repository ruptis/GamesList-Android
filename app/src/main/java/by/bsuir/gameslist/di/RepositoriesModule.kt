package by.bsuir.gameslist.di

import by.bsuir.gameslist.repository.CollectionRepository
import by.bsuir.gameslist.repository.GameRepository
import by.bsuir.gameslist.repository.PlatformRepository
import by.bsuir.gameslist.repository.implementations.FirestoreCollectionRepository
import by.bsuir.gameslist.repository.implementations.FirestoreGameRepository
import by.bsuir.gameslist.repository.implementations.FirestorePlatformRepository
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
abstract class RepositoriesModule {
    @Binds
    @Singleton
    internal abstract fun bindCollectionRepository(
        collectionRepository: FirestoreCollectionRepository
    ) : CollectionRepository

    @Binds
    @Singleton
    internal abstract fun bindGameRepository(
        gameRepository: FirestoreGameRepository
    ) : GameRepository

    @Binds
    @Singleton
    internal abstract fun bindPlatformRepository(
        platformRepository: FirestorePlatformRepository
    ) : PlatformRepository
}