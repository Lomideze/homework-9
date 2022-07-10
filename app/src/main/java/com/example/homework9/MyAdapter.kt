package com.example.homework9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val itemList: ArrayList<MyDataClass>) :
    RecyclerView.Adapter<MyAdapter.itemViewHoler>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    inner class itemViewHoler(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.firstNameTv)
        val lastName: TextView = itemView.findViewById(R.id.lastNameTv)
        val email: TextView = itemView.findViewById(R.id.emailTv)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHoler {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        return itemViewHoler(view, mListener)
    }

    override fun onBindViewHolder(holder: itemViewHoler, position: Int) {
        val p = itemList[position]

        holder.firstName.text = p.firstName
        holder.lastName.text = p.lastName
        holder.email.text = p.email

    }

    override fun getItemCount(): Int = itemList.size
}
