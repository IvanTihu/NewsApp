package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.DataItemBinding
import com.squareup.picasso.Picasso

class DataAdapter(private var dataList: List<Article>?,
                  val itemClickListener: OnDataClick?=null,
                  val deleteClickListener: OnDataClick?=null,
                  ): RecyclerView.Adapter<DataAdapter.DataHolder>() {


    fun updateList(dataList: List<Article>?){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    private lateinit var mListener: OnDataClick

    interface OnDataClick{
        fun onDataItemClick(position: Int)
        fun onItemDelete(position: Int)
    }

     fun setOnItemClickListener(listener: OnDataClick){
        mListener = listener
    }

    inner class DataHolder(item: View):RecyclerView.ViewHolder(item) {

        init {
            item.setOnClickListener {
                itemClickListener?.onDataItemClick(adapterPosition)
                deleteClickListener?.onItemDelete(adapterPosition)
            }
        }

        private val binding = DataItemBinding.bind(item)
        fun bind(data: Article?) = with (binding) {
            tvTask.text = data?.title
            tvData.text = data?.author
            Picasso.get().load(data?.urlToImage).into(imFoto);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return DataHolder(view)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val itemsPosition = dataList?.get(position)
        holder.bind(itemsPosition)
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }
}