package com.example.android.catapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "cat_table")
data class Cat (

    @ColumnInfo(name = "name")
    var name:String?,

    @ColumnInfo(name = "image_id")
    var imageId:Int?,

    @ColumnInfo(name = "gender")
    var gender:Char?,

    @ColumnInfo(name = "age")
    var age:Int?
    )
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}



