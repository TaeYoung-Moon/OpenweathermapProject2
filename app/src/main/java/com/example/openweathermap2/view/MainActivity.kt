package com.example.openweathermap2.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.openweathermap2.R
import com.example.openweathermap2.adapter.CityAdapter
import com.example.openweathermap2.databinding.ActivityMainBinding
import com.example.openweathermap2.listener.OnItemClickListener
import com.example.openweathermap2.model.City
import com.example.openweathermap2.util.JsonUtil
import com.example.openweathermap2.viewmodel.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MainActivity : AppCompatActivity(), OnItemClickListener, TextWatcher {
    private var mCityAdapter: CityAdapter? = null
    private var binding // 데이터 바인딩 작업
            : ActivityMainBinding? = null
    private var viewModelFactory: AndroidViewModelFactory? = null
    private var mainViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        binding?.tieSearch?.setText("")
    }

    fun init() {
        if (viewModelFactory == null) {
            viewModelFactory = AndroidViewModelFactory.getInstance(application)
        }
        mainViewModel = ViewModelProvider(this, viewModelFactory!!).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.setViewModel(mainViewModel)
        binding?.executePendingBindings()
        val cityList = JsonUtil.getJsonFromAssets(this, "citylist.json")
        val listType = object : TypeToken<ArrayList<City?>?>() {}.type
        val cities = Gson()?.fromJson<ArrayList<City?>?>(cityList, listType)

        cities.sortWith { data1, data2 -> data1!!.name!!.compareTo(data2!!.name!!) }


        // 뷰 어뎁터 설정
        mCityAdapter = CityAdapter(cities, this)
        binding?.listCity?.adapter = mCityAdapter
        binding?.srlRefresh?.setOnRefreshListener {
            binding?.listCity?.adapter = mCityAdapter
            binding?.srlRefresh?.isRefreshing = false
        }
        binding?.tieSearch?.addTextChangedListener(this)
    }

    override fun onItemClick(id: String) {
        val intent = Intent(this, LoadingActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
        mCityAdapter?.filter?.filter(charSequence)
    }

    override fun afterTextChanged(edit: Editable?) {}
}