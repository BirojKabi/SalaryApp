package com.example.sailsal2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle



        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //to fix the bottom app bar faint shadow issue
        bottomnavigationview.background = null

        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        // This is for the back arrow which moves back when drawer is open
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        // To enable selection for navigation drawer menu items

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()
        val navController = findNavController(R.id.fragment)
        //next_btn.setOnClickListener{Toast.makeText(this, "ButtonClicked", Toast.LENGTH_SHORT).show()
        //    val navHostFragment = supportFragmentManager
        //        .findFragmentById(R.id.fragment) as NavHostFragment
        //    when(navHostFragment.fragment){
        //        firstFragment -> navController.navigate(R.id.secondFragment )
        //    }
        //    }

        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> navController.navigate(R.id.firstFragment)




                R.id.preferences -> navController.navigate(R.id.preferenceFragment)

                R.id.credit -> navController.navigate(R.id.creditFragment)
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}