package ru.pgk63.focusstart.data.network.bin.model

data class BinDetails(
    val number:BinNumber?,
    val scheme:String?,
    val type:String?,
    val brand:String?,
    val prepaid:Boolean?,
    val country:Country?,
    val bank:Bank?
)

data class BinNumber(
    val length:Int?,
    val luhn:Boolean?
)

data class Country(
    val numeric:String?,
    val alpha2:String?,
    val name:String?,
    val emoji:String?,
    val currency:String?,
    val latitude:Double?,
    val longitude:Double?
)

data class Bank(
    val name:String?,
    val url:String?,
    val phone:String?,
    val city:String?
)