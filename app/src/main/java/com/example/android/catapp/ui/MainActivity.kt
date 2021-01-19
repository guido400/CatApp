package com.example.android.catapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.catapp.R
import com.example.android.catapp.db.CatDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireNotNull(this).application
        val catDao = CatDatabase.getInstance(application).catDao
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(catDao)).get(
            MainActivityViewModel::class.java
        )
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> openSettings()
            R.id.clear_cats -> clearCats()
            R.id.add_random_cat -> addRandomCat()
            R.id.add_default_cats -> addDefaultCats()
            R.id.add_cat -> addCat()

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

     fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment);
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun addCat() {
        val fragment = CreateCatFragment()
        loadFragment(fragment)
    }

    private fun addDefaultCats() {
        return
    }

    private fun addRandomCat() {
        return
    }

    private fun clearCats() {
        return
    }

    private fun openSettings() {
        return
    }


}