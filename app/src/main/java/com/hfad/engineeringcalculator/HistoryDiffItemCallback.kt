package com.hfad.engineeringcalculator

import androidx.recyclerview.widget.DiffUtil

class HistoryDiffItemCallback: DiffUtil.ItemCallback<ECEntity>() {
    override fun areItemsTheSame(oldItem: ECEntity, newItem: ECEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ECEntity, newItem: ECEntity): Boolean {
        return oldItem == newItem
    }
}