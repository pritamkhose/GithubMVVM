package com.pritam.githubmvvm.view.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pritam.githubmvvm.R
import com.pritam.githubmvvm.service.model.Item
import com.pritam.githubmvvm.databinding.UserSearchListItemBinding

class UserSerachAdapter(private val aList: MutableList<Item>) : RecyclerView.Adapter<UserSerachAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<UserSearchListItemBinding>(LayoutInflater.from(parent.context), R.layout.user_search_list_item,
            parent, false)
        binding.isImageLoading = true
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return aList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.item = aList.get(position)
        holder.binding.executePendingBindings()
    }

    class ItemViewHolder(val binding: UserSearchListItemBinding) : RecyclerView.ViewHolder(binding.root)

}