package ru.pgk63.focusstart.data.network.bin.repository

import ru.pgk63.focusstart.data.network.bin.BinApi
import ru.pgk63.focusstart.data.network.bin.model.BinDetails
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val binApi: BinApi
) {

    suspend fun getDetails(bin:String): BinDetails = binApi.getBinDetails(bin)
}