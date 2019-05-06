package com.yenen.ahmet.kotlinretrofitviewmodel.bindings

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView

class CustomBindings {

    companion object {
        @BindingAdapter("setAdapter")
        @JvmStatic
        fun bindRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
            view.setHasFixedSize(true)
            view.adapter = adapter
        }


        @JvmStatic
        @BindingAdapter("viewBackground")
        fun setViewBackground(view: CardView, coinName:String?) {
            if(coinName =="eth"){
                view.setCardBackgroundColor(Color.GRAY)
            }else{
                view.setCardBackgroundColor(Color.GREEN)
            }
        }
    }
}