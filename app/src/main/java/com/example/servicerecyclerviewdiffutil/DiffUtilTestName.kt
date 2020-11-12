package com.example.servicerecyclerviewdiffutil

import androidx.recyclerview.widget.DiffUtil


class DiffUtilTestName(
    private val oldList: List<TestName>,
    private val newList: List<TestName>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
        return oldList[oldItemPos].id == newList[newItemPos].id
    }

    override fun areContentsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
        return oldList[oldItemPos] == newList[newItemPos]
    }
}



