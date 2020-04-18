package com.eventtracker.app.di.modules.data

import javax.inject.Singleton
import dagger.Module
import dagger.Provides

import io.realm.Realm

@Module
class RealmModule() {
    @Singleton
    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()
}