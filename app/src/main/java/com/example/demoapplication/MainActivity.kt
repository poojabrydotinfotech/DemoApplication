package com.example.demoapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        //val navController = navHostFragment.navController
        /*if (null == savedInstanceState) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CameraBasicFragment.newInstance())
                .commit()
        }*/



    }
}