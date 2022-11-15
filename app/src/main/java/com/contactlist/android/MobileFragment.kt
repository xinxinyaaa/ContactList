package com.contactlist.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.contactlist.android.databinding.FragmentListBinding
import com.contactlist.android.databinding.FragmentMobileBinding
import com.contactlist.android.databinding.FragmentNetworkBinding
import kotlinx.android.synthetic.main.fragment_mobile.*

public class MobileFragment : BaseFragment(){
    val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    private lateinit var adapter: ListAdapter
    private lateinit var binding : FragmentMobileBinding


    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobile,container,false)
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentMobileBinding.inflate(layoutInflater)


        bg_mobile_ImageView.setImageResource(R.mipmap.bg_mobile)

        val layoutManager = LinearLayoutManager(activity)
        binding.morecyclerView.layoutManager = layoutManager
        adapter = ListAdapter(this, viewModel.contactList)
        binding.morecyclerView.adapter = adapter

        /*viewModel.contactList.observe(this, Observer{ result ->

        })*/

    }

    override fun initView(): View? {

        binding = FragmentMobileBinding.inflate(layoutInflater)
        return binding.root

    }
}