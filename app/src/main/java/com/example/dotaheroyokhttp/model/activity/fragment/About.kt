package com.example.dotaheroyokhttp.model.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.dotaheroyokhttp.R
import com.example.dotaheroyokhttp.databinding.FragmentAboutBinding
import com.example.dotaheroyokhttp.databinding.FragmentDotaHeroyBinding
import com.example.dotaheroyokhttp.model.utils.FragmentManagere


class About : Fragment() {
    lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.btnAboutClick.setOnClickListener {
            FragmentManagere.setFragment(DotaHeroy.newInstance(), activity as AppCompatActivity)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =About()
    }
}