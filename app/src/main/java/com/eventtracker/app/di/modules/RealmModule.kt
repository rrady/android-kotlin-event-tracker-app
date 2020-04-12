package com.eventtracker.app.di.modules

import javax.inject.Singleton
import android.app.Application
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class RealmModule() {
    @Singleton
    @Provides
    fun provideRealm(application: Application): Realm = Realm.getDefaultInstance()
}