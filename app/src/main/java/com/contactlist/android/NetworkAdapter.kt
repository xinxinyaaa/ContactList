package com.contactlist.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class NetworkAdapter(private val fragment: Fragment, private val contactList: List<ListResponse>):
    RecyclerView.Adapter<NetworkAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val id : TextView = view.findViewById(R.id.id)
        val name: TextView = view.findViewById(R.id.name)
        val followers: TextView = view.findViewById(R.id.followers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.network_item,parent,false)
        //return ViewHolder(view)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val list = contactList[position]
            val intent = Intent(parent.context,NetworkFragment::class.java).apply {
                putExtra("id",list.id)
                putExtra("name",list.name)
                putExtra("followers",list.followers)
            }
            fragment.startActivity(intent)
            fragment.activity?.finish()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = contactList[position]
        holder.id.text = list.id
        holder.name.text = list.name
        holder.followers.text = list.followers.toString()
    }

    override fun getItemCount() = contactList.size

}