package com.example.dotaheroyokhttp.model.activity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResultListener
import coil.load
import com.example.dotaheroyokhttp.R
import com.example.dotaheroyokhttp.databinding.FragmentHeroyDetailsBinding
import com.example.dotaheroyokhttp.model.const.Constant
import com.example.dotaheroyokhttp.model.const.Constant.ID_HERO
import com.example.dotaheroyokhttp.model.const.Constant.REQUEST
import com.example.dotaheroyokhttp.model.hero
import com.example.dotaheroyokhttp.model.utils.FragmentManagere
import com.example.dotaheroyokhttp.model.utils.FragmentManagere.currentFragment


class HeroyDetails : Fragment() {
    lateinit var binding: FragmentHeroyDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeroyDetailsBinding.inflate(inflater, container, false)
        //arguments
       setFragmentResultListener("requestKey") { key, bundle ->
           val heroID = bundle.getInt(ID_HERO)


           Log.d("dotaID", heroID.toString())

           with(binding) {
               heroNameDetails.text = com.example.dotaheroyokhttp.model.hero[heroID].localized_name
               heroImageDetails.load("https://api.opendota.com${com.example.dotaheroyokhttp.model.hero[heroID].img}")
               rolesDetails.text = "roles: ${com.example.dotaheroyokhttp.model.hero[heroID].roles}"
               baseHealthDetails.text =
                   "health: ${com.example.dotaheroyokhttp.model.hero[heroID].base_health}"
               baseAttackMaxDetails.text =
                   "atack max: ${com.example.dotaheroyokhttp.model.hero[heroID].base_attack_max}"
               baseMana.text = "mana: ${com.example.dotaheroyokhttp.model.hero[heroID].base_mana}"
           }
       }
        binding.btnFragClick.setOnClickListener {
            FragmentManagere.setFragment(DotaHeroy.newInstance(), activity as AppCompatActivity)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = HeroyDetails()
    }
}


