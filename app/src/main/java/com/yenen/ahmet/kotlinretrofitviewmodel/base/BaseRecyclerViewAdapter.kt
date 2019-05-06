package com.yenen.ahmet.kotlinretrofitviewmodel.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseRecyclerViewAdapter <T, E : RecyclerView.ViewHolder> : RecyclerView.Adapter<E>(){

    private var items :MutableList<T>? = null

    override fun getItemCount(): Int {
        if(items!=null){
            return items!!.size
        }
        return 0
    }

    fun getItem(position:Int): T? {
        if(items!=null && !items!!.isEmpty()){
            return items!!.get(position)
        }
        return null
    }
    fun setItems(list : List<T>?){
        if(list!= null ){
            items = list as MutableList<T>
            notifyDataSetChanged()
        }
    }
    fun getItems():List<T>?{
        return items
    }

    fun addItems(list:List<T>?){
        if(items!=null && list!=null && !list.isEmpty()){
            val x:Int = items!!.size
            items!!.addAll(list)
            val y:Int  = items!!.size
            notifyItemRangeInserted(x,y)
        }
    }

    fun getInflater(parent : ViewGroup) :LayoutInflater{
        return LayoutInflater.from(parent.context)
    }

    fun clear(){
        items?.clear()
    }
}