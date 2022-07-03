package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.DataItemBinding

class DataAdapter(private val dataList: ArrayList<Data>): RecyclerView.Adapter<DataAdapter.DataHolder>() {

    private lateinit var mListener: OnDataClick

    interface OnDataClick{
        fun onDataItemClick(position: Int)
        fun onItemDelete(position: Int)
    }

     fun setOnItemClickListener(listener: OnDataClick){
        mListener = listener
    }

    class DataHolder(item: View, listener: OnDataClick):RecyclerView.ViewHolder(item) {
        private val binding = DataItemBinding.bind(item)

        fun bind(data: Data) = with (binding) {
            tvTask.text = data.task
            tvData.text = data.data

        }
        init {
            item.setOnClickListener {
                listener.onDataItemClick(adapterPosition)
            }
        }
        init {
            binding.btDelete.setOnClickListener {
                listener.onItemDelete(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return DataHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bind(dataList[position])

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun addData (data: Data){
        dataList.add(data)
        notifyDataSetChanged()
    }

}