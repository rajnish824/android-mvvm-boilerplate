package com.devrajnish.baseapplication.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val items: ArrayList<T> = ArrayList()

    override fun getItemCount() = items.size

    fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>) {
        val startPosition = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    fun addItem(item: T) {
        val startPosition = this.items.size
        this.items.add(item)
        notifyItemInserted(startPosition)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItem(position: Int, item: T) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun getItems(): List<T> = items
}