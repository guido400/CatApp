package com.example.android.catapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import com.example.android.catapp.db.CatDatabase
import com.example.android.catapp.db.CatDao
import com.example.android.catapp.db.Cat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CatDatabaseTest {

    private lateinit var catDao: CatDao
    private lateinit var db: CatDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, CatDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        catDao = db.catDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCat() {

        val testName = "test"
        val testCat = Cat (testName,1,'f', 10)
        catDao.insert(testCat)
        val resultCat = catDao.selectByID(1)

        assertEquals (testName,resultCat?.name)

    }
}