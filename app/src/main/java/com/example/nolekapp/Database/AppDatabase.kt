package com.example.nolekapp.Database

import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Database.Data.TestResultatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

/*
@InstallIn(SingletonComponent::class)
@Module
object AppDatabase{

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                TestResultat::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideTestResultatRepository(realm: Realm): TestResultatRepository {
        return TestResultatRespositoryimpl(realm = realm)
    }

}

 */
