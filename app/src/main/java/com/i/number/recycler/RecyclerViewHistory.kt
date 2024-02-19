package com.i.number.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.i.db.NumberEntity
import com.i.number.R

//todo установить клил на холдер
class RecyclerViewHistory(
    private val listHistory: List<NumberEntity>,
    private val onItemClickListener: (NumberEntity) -> Unit
) :
    RecyclerView.Adapter<ItemHolder>() {
    override fun getItemCount() = listHistory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_first_screen, parent, false)
        return ItemHolder(view = view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.initView(listHistory[position], onItemClickListener = onItemClickListener)
    }


}
