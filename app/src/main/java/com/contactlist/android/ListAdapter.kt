package com.contactlist.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val fragment: Fragment,private val contactList: List<ListResponse>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val id :TextView = view.findViewById(R.id.id)
        val name:TextView = view.findViewById(R.id.name)
        val follower:TextView = view.findViewById(R.id.follower)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = contactList[position]
        holder.id.text = list.id
        holder.name.text = list.full_name
        holder.follower.text = list.followers.toString()
    }

    override fun getItemCount() = contactList.size

}