package com.example.dotaheroyokhttp.model.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dotaheroyokhttp.R

object FragmentManagere {
    var currentFragment: Fragment? = null

    fun setFragment(newFragment: Fragment, act: AppCompatActivity){
        val transaction = act.supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(R.id.place_holder_time, newFragment)
        transaction.commit()
        currentFragment = newFragment
    }
}