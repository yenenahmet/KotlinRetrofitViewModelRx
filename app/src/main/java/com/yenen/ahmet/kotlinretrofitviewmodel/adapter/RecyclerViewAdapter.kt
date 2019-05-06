package com.yenen.ahmet.kotlinretrofitviewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.yenen.ahmet.kotlinretrofitviewmodel.base.BaseRecyclerViewAdapter
import com.yenen.ahmet.kotlinretrofitviewmodel.databinding.RecyclerviewItemLayoutBinding
import com.yenen.ahmet.kotlinretrofitviewmodel.model.Crypto

class RecyclerViewAdapter : BaseRecyclerViewAdapter<Crypto.Market, RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemLayoutBinding.inflate(getInflater(parent),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item :Crypto.Market? = getItem(position)
        if(item!= null){
            holder.bind(item)
        }
    }

    inner class ViewHolder(val binding: RecyclerviewItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Crypto.Market) {
            binding.viewadapter = item
            binding.executePendingBindings()
        }
    }

}