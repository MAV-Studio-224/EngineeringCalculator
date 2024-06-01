package com.hfad.engineeringcalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.engineeringcalculator.databinding.HistoryElementBinding

class HistoryAdapter(private val clickListener: (item: ECEntity) -> Unit): ListAdapter<ECEntity,
        HistoryAdapter.ItemViewHolder>(HistoryDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ItemViewHolder(val binding: HistoryElementBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryElementBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }

        fun bind(item: ECEntity, clickListener: (item: ECEntity) -> Unit) {
            binding.ecEntity = item
            binding.delete.setOnClickListener {
                clickListener(item)
            }
        }
    }
}