package com.example.repaso_m

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class tablePost (

    @PrimaryKey()
    val id:Int?,

    @ColumnInfo(name="userId")
    val userid:Int?,

    @ColumnInfo(name="title")
    val title:String?,

    @ColumnInfo(name="body")
    val body:String?

 )