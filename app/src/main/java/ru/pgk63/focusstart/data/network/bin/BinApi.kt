package ru.pgk63.focusstart.data.network.bin

import retrofit2.http.GET
import retrofit2.http.Path
import ru.pgk63.focusstart.data.network.bin.model.BinDetails

interface BinApi {

    @GET("/{bin}")
    suspend fun getBinDetails(@Path("bin") bin:String):BinDetails
}