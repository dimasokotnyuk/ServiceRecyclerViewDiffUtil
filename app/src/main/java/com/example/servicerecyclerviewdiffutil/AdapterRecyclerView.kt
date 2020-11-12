package com.example.servicerecyclerviewdiffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class AdapterRecyclerView : RecyclerView.Adapter<ViewHolderName>() {

    var data: MutableList<TestName> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderName {
        return ViewHolderName(
            LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderName, position: Int) {
        holder.bindValue(data[position])
    }

    override fun onBindViewHolder(
        holder: ViewHolderName,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.toString() == "KEY_UPDATE") {
            holder.updateValue(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: ArrayList<TestName>) {
        var diffResult = DiffUtil.calculateDiff(DiffUtilTestName(newData, data))
        diffResult.dispatchUpdatesTo(this)
        data.clear()
        this.data.addAll(newData)
    }

    fun addElement() {
        data.add(TestName(UUID.randomUUID().toString(), Random.nextInt(100)))
        notifyItemInserted(data.size-1)
    }

    fun deleteElement(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}