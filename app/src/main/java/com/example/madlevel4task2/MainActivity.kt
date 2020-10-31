package com.example.madlevel4task2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var historyRepository: HistoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionbar = supportActionBar
        actionbar!!.title = "Mad Level 4 Task 2"

        historyRepository = HistoryRepository(this)

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        // Hide delete menu icon
        menu.findItem(R.id.delete_settings).isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.history_settings -> {
                // Hide history menu item
                toolbar.menu.findItem(R.id.history_settings).isVisible = false
                toolbar.menu.findItem(R.id.delete_settings).isVisible = true

                // Navigates to another fragment
                navController.navigate(R.id.action_playFragment_to_historyFragment)

                //actionbar
                val actionbar = supportActionBar
                //set actionbar title
                actionbar!!.title = "Your Game History"
                //set back button
                actionbar.setDisplayHomeAsUpEnabled(true)
                true
            }
            R.id.delete_settings -> {
                historyRepository.deleteHistory()

//                val fragment = HistoryFragment()
//                val fragmentManager = supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//
//                fragment.dataChanged()
//
//                fragmentTransaction.replace(R.id.flHistoryContainer, fragment)
//                fragmentTransaction.detach(fragment)
//                fragmentTransaction.attach(fragment)
//                fragmentTransaction.commit()

                Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val actionbar = supportActionBar
        actionbar!!.title = "Mad Level 4 Task 2"
        actionbar.setDisplayHomeAsUpEnabled(false)

        toolbar.menu.findItem(R.id.history_settings).isVisible = true
        toolbar.menu.findItem(R.id.delete_settings).isVisible = false

        onBackPressed()
        return true
    }
}