package com.contactlist.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.contactlist.android.R
import com.contactlist.android.databinding.FragmentListBinding
import com.contactlist.android.databinding.FragmentNetworkBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_network.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class NetworkFragment: BaseFragment() {
    val TAG = "networkFragment"
    val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    private lateinit var adapter: NetworkAdapter
    private lateinit var binding : FragmentNetworkBinding

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_network,container,false)
        //return super.onCreateView(inflater, container, savedInstanceState)
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentNetworkBinding.inflate(layoutInflater)
        bg_network_ImageView.setImageResource(R.mipmap.bg_network)
        sendRequestWithOkHttp()


        //val search: EditText = findViewById(R.id.search)
        val layoutManager = LinearLayoutManager(activity)
        binding.netrecyclerView.layoutManager = layoutManager
        adapter = NetworkAdapter(this, viewModel.contactList)
        binding.netrecyclerView.adapter = adapter

        /*viewModel.contactList.observe(this, Observer{ result ->

        })*/

    }

    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_network,null)

    }

    override fun initListeners() {
        super.initListeners()
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NetworkAdapter(this,viewModel.contactList)
        recyclerView.adapter = adapter
    }

    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                //val request = Request.Builder().url("https://www.baidu.com").build()
                val request = Request.Builder().url("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/users.json").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null){
                    //showResponse(responseData)
                    //parseJSONWithJSONObject(responseData)
                    JSONtoList(responseData)
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    /*private fun showResponse(response:String){
        runOnUiThread {
            //??????UI?????????????????????????????????
            binding.netrecyclerView.text = response

        }

    }*/

    private fun JSONtoList(jsonData: String){
        val gson = Gson()
        val typeOf = object : TypeToken<ArrayList<ListResponse>>(){}.type
        val personList = gson.fromJson<List<ListResponse>>(jsonData, typeOf)
        //personList = Gson().fromJson(jsonData,typeOf)
        for (person in personList){
            Log.d(TAG,"id is ${person.id}")
            Log.d(TAG,"full_name is ${person.name}")
            Log.d(TAG,"followers is ${person.followers}")
        }
    }
}