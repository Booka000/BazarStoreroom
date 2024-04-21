package com.albara.bazarstoreroom.di

import android.app.Application
import androidx.room.Room
import com.albara.bazarstoreroom.data.repository.Repository
import com.albara.bazarstoreroom.data.roomDB.StoreroomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideRepository(dp : StoreroomDatabase) : Repository {
        return Repository(dp)
    }

    @Provides
    @Singleton
    fun provideDatabase(app : Application) : StoreroomDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            StoreroomDatabase::class.java,
            "StoreRoomDatabase"
        ).build()
    }
}