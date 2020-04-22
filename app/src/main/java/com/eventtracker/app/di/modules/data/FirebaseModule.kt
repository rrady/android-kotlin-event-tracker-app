package com.eventtracker.app.di.modules.data

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

@Module
class FirebaseModule {
    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        var instance = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        instance.firestoreSettings = settings

        return instance
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}