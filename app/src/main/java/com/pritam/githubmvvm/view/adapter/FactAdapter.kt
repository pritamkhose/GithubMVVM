package com.pritam.githubmvvm.view.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pritam.githubmvvm.R
import com.pritam.githubmvvm.service.model.Fact
import com.pritam.githubmvvm.databinding.FactsListItemBinding

class FactAdapter(private val FactList: MutableList<Fact>) : RecyclerView.Adapter<FactAdapter.FactViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = DataBindingUtil.inflate<FactsListItemBinding>(LayoutInflater.from(parent.context), R.layout.facts_list_item,
                parent, false)
        binding.isImageLoading = true
        return FactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return FactList.size
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.binding.fact = FactList.get(position)
        holder.binding.executePendingBindings()
    }

    class FactViewHolder(val binding: FactsListItemBinding) : RecyclerView.ViewHolder(binding.root)

}