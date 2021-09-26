package com.abhisht28.eventmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event
// (
//    @PrimaryKey(autoGenerate = true) var id: String = null,
//    @ColumnInfo(name = "text") val text: String,
//    @ColumnInfo(name = "eventDetails") val eventDetails: String
//
//)
//        (@ColumnInfo(name = "text") val text:String){
//        @PrimaryKey(autoGenerate = true) var id= 0
//        }
        (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "text") val text: String,
        @ColumnInfo(name = "eventDetails") val textDetails: String,
        @ColumnInfo(name = "dateDetails") val dateDetails :String
)

