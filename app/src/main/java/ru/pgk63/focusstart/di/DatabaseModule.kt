package ru.pgk63.focusstart.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.pgk63.focusstart.data.database.user.UserDatabase
import ru.pgk63.focusstart.data.database.user.dao.RequestBinHistoryDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @[Provides Singleton]
    fun providerRequestBinHistoryDao(
        database: UserDatabase
    ): RequestBinHistoryDao = database.requestBinHistoryDao()

    @[Provides Singleton]
    fun providerUserDatabase(
        @ApplicationContext context: Context
    ): UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        "user_database"
    ).build()
}






