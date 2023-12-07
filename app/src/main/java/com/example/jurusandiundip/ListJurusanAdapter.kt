package com.example.jurusandiundip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// YANG LAMAAAAAA
class ListJurusanAdapter(private val listItem: ArrayList<Jurusan>) : RecyclerView.Adapter<ListJurusanAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Jurusan)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvAccreditation: TextView = itemView.findViewById(R.id.tv_item_accreditation)
        val tvTopicLearned: TextView = itemView.findViewById(R.id.tv_item_topic_learned)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_jurusan_jurusan, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, accreditation, _, topic_learned, image) = listItem[position]

        holder.imgPhoto.setImageResource(image)
        holder.tvName.text = name
        holder.tvAccreditation.text = accreditation
        holder.tvTopicLearned.text = topic_learned

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listItem[holder.adapterPosition])
        }
    }
}