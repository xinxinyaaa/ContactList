package com.contactlist.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class ListFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    private lateinit var adapter: ListAdapter
    //private lateinit var binding : FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val search: EditText = findViewById(R.id.search)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = ListAdapter(this, viewModel.contactList)
        recyclerView.adapter = adapter
    }


}