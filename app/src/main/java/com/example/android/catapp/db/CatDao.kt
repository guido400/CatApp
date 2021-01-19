package com.example.android.catapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface CatDao {
    @Insert
    suspend fun  insert (cat:Cat)

    @Update
    suspend fun update (cat:Cat)

    @Query("DELETE FROM cat_table")
    suspend fun clear()

    @Query ("SELECT * FROM cat_table")
    fun selectAll(): LiveData<List<Cat>>

    @Query ("SELECT * FROM cat_table WHERE id = :inputId")
    suspend fun selectByID (inputId:Int):Cat?

}