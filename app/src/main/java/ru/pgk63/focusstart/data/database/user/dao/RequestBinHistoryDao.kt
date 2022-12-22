package ru.pgk63.focusstart.data.database.user.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.pgk63.focusstart.data.database.user.model.RequestBinHistory

@Dao
interface RequestBinHistoryDao {

    @Query("SELECT * FROM request_bin_history ORDER BY date DESC")
    fun getAll():Flow<List<RequestBinHistory>>

    @Upsert
    suspend fun add(requestBinHistory: RequestBinHistory)
}