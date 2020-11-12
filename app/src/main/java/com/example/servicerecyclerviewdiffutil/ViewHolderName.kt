package com.example.servicerecyclerviewdiffutil

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderName(private val listItemView: View) : RecyclerView.ViewHolder(listItemView) {
    fun bindValue(item: TestName) {
        listItemView.findViewById<TextView>(R.id.tvValue).text = item.value.toString()
    }

    fun updateValue(item: TestName) {
        listItemView.findViewById<TextView>(R.id.tvValue).text = item.value.toString()
    }
}
