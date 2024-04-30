package com.hfad.engineeringcalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ECTable")
data class ECEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "type")
    val type: String = "ERROR",
    @ColumnInfo(name = "expression")
    val expression: String = "ERROR",
    @ColumnInfo(name = "answer")
    val answer: String = "ERROR"
)