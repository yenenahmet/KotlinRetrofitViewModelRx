package com.yenen.ahmet.kotlinretrofitviewmodel.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yenen.ahmet.kotlinretrofitviewmodel.R
import com.yenen.ahmet.kotlinretrofitviewmodel.databinding.ActivityMainBinding
import com.yenen.ahmet.kotlinretrofitviewmodel.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(savedInstanceState,binding,this)
        binding.mainViewModel = viewModel
        obsertveDate(viewModel)
    }

    private fun obsertveDate(viewModel: MainViewModel) {
        viewModel.getData().observe(this,
            Observer { t ->
                if (t != null) {
                    viewModel.getAdapter()?.setItems(t)
                }
            })
    }
}
