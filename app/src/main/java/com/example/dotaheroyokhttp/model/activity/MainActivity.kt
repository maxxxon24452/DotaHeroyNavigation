package com.example.dotaheroyokhttp.model.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dotaheroyokhttp.R
import com.example.dotaheroyokhttp.databinding.ActivityMainBinding
import com.example.dotaheroyokhttp.model.URL
import com.example.dotaheroyokhttp.model.activity.fragment.DotaHeroy
import com.example.dotaheroyokhttp.model.adapter.HeroyAdapter
import com.example.dotaheroyokhttp.model.data.HeroyItemItemX
import com.example.dotaheroyokhttp.model.hero
import com.squareup.moshi.*
import okhttp3.*
import java.io.*


 class MainActivity : AppCompatActivity(){


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction().replace(R.id.place_holder_time, DotaHeroy.newInstance())
            .commit()
    }




}