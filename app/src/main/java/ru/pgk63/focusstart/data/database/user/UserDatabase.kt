package ru.pgk63.focusstart.data.database.user

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pgk63.focusstart.data.database.converters.DateConverter
import ru.pgk63.focusstart.data.database.user.dao.RequestBinHistoryDao
import ru.pgk63.focusstart.data.database.user.model.RequestBinHistory

@Database(entities = [RequestBinHistory::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class UserDatabase : RoomDatabase() {

    abstract fun requestBinHistoryDao(): RequestBinHistoryDao

}