package com.i.number.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.i.db.NumberEntity
import com.i.number.databinding.ItemRvFirstScreenBinding
import com.i.number.model.ModelNumberInfo


class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRvFirstScreenBinding.bind(view)
    fun initView(numberEntity: NumberEntity,onItemClickListener: (NumberEntity) -> Unit) {
        binding.tvNumber.text = numberEntity.number
        binding.tvInfo.text = numberEntity.text
        itemView.setOnClickListener { onItemClickListener(numberEntity) }
    }
}