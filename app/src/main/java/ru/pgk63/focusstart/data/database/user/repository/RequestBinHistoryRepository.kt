package ru.pgk63.focusstart.data.database.user.repository

import kotlinx.coroutines.flow.Flow
import ru.pgk63.focusstart.data.database.user.dao.RequestBinHistoryDao
import ru.pgk63.focusstart.data.database.user.model.RequestBinHistory
import javax.inject.Inject

class RequestBinHistoryRepository @Inject constructor(
    private val dao:RequestBinHistoryDao
) {

    fun getAll(): Flow<List<RequestBinHistory>> = dao.getAll()

    suspend fun add(requestBinHistory: RequestBinHistory) = dao.add(requestBinHistory)
}