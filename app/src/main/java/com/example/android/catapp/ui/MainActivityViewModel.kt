package com.example.android.catapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.catapp.db.Cat
import com.example.android.catapp.db.CatDao
import com.example.android.catapp.db.CatDatabase
import kotlinx.coroutines.launch

class MainActivityViewModel(val dataSource: CatDao) : ViewModel() {

    fun addCat(cat: Cat) {
        viewModelScope.launch {
            dataSource.insert(cat)
        }
    }

    val cats = dataSource.selectAll()

}