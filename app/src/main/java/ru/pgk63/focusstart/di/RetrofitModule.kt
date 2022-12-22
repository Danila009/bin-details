package ru.pgk63.focusstart.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.pgk63.focusstart.data.network.bin.BinApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @[Provides Singleton]
    fun providerBinApi(retrofit: Retrofit): BinApi = retrofit.create<BinApi>()

    @[Provides Singleton]
    fun providerRetrofit():Retrofit = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}