package ru.pgk63.focusstart.data.database.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "request_bin_history")
data class RequestBinHistory(
    @PrimaryKey(autoGenerate = false) val name:String,
    val date: Date? = Date()
)