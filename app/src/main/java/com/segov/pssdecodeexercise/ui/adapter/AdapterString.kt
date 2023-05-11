package com.segov.pssdecodeexercise.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.segov.pssdecodeexercise.databinding.ItemDriverBinding

/**
 * Display a string with an image on the left.
 * Simple demonstration of an adapter with diff callback.
 */
class AdapterString(private val callback: (String) -> Unit) : ListAdapter<String, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem.contentEquals(newItem)
        }
    }

    inner class ViewHolder(val view: ItemDriverBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDriverBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        with ((holder as ViewHolder).view) {
            driverName.text = item
            root.setOnClickListener { callback(item) }
        }
    }

}