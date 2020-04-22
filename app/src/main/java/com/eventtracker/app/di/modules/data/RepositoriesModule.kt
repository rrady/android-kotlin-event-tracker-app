package com.eventtracker.app.di.modules.data

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

import com.eventtracker.domain.repositories.*
import com.eventtracker.data.repositories.*

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideHostRepository(db: FirebaseFirestore, storage: FirebaseStorage): HostRepository {
        return HostRepositoryImpl(db, storage)
    }

    @Provides
    @Singleton
    fun provideEventRepository(db: FirebaseFirestore): EventRepository {
        return EventRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(db: FirebaseFirestore): LocationRepository {
        return LocationRepositoryImpl(db)
    }
}