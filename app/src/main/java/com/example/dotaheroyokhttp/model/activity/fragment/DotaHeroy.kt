package com.example.dotaheroyokhttp.model.activity.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dotaheroyokhttp.R
import com.example.dotaheroyokhttp.databinding.FragmentDotaHeroyBinding
import com.example.dotaheroyokhttp.model.URL
import com.example.dotaheroyokhttp.model.adapter.HeroyAdapter
import com.example.dotaheroyokhttp.model.const.Constant.ID_HERO
import com.example.dotaheroyokhttp.model.data.HeroyItemItemX
import com.example.dotaheroyokhttp.model.hero
import com.example.dotaheroyokhttp.model.utils.FragmentManagere
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import java.io.*

open class DotaHeroy : Fragment(), HeroyAdapter.ItemClickListener {
    lateinit var binding: FragmentDotaHeroyBinding
    private var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDotaHeroyBinding.inflate(inflater, container, false)
        DotaHero()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_project){
            FragmentManagere.setFragment(About.newInstance(), activity as AppCompatActivity)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun DotaHero() {
        val request: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("mylog", "response")

                val json: String? = response.body?.string()
                //  отрываем поток для записи
                var bw: BufferedWriter = BufferedWriter(
                    OutputStreamWriter(
                        activity?.openFileOutput("dota.txt", AppCompatActivity.MODE_PRIVATE)
                    )
                );
                // пишем данные
                bw.write("${json}");
                // закрываем поток
                bw.close();
                Log.d("mylogi", "Файл записан $bw");
                //чтения
                val inputStream = activity?.openFileInput("dota.txt")
                val r = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (r.readLine().also { line = it } != null) {
                    Log.d("mylogini", "Файл записан $line");
                    val moshi = Moshi.Builder().build()
                    val list =
                        Types.newParameterizedType(List::class.java, HeroyItemItemX::class.java)
                    val adapter: JsonAdapter<List<HeroyItemItemX>> = moshi.adapter(list)
                    hero = adapter.fromJson(line)!!
                    activity?.runOnUiThread {
                        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
                        binding.recyclerview.adapter = HeroyAdapter(this@DotaHeroy, hero)
                    }


                    Log.d("mylogini", "Файл записан $line");
                }


            }


        })
    }


    override fun onClickItem(position: Int) {

        requireActivity().supportFragmentManager.setFragmentResult(
            "requestKey",
            bundleOf(ID_HERO to position)
        )
        FragmentManagere.setFragment(HeroyDetails.newInstance(), activity as AppCompatActivity)

    }

    companion object {

        @JvmStatic
        fun newInstance() = DotaHeroy()
    }
}