package com.contactlist.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.contactlist.android.R
import com.contactlist.android.databinding.FragmentListBinding
import com.contactlist.android.databinding.FragmentNetworkBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

public class networkFragment: Fragment() {
    val TAG = "networkFragment"
    val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    private lateinit var adapter: NetworkAdapter
    private lateinit var binding : FragmentNetworkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_network,container,false)
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentNetworkBinding.inflate(layoutInflater)
        sendRequestWithOkHttp()


        //val search: EditText = findViewById(R.id.search)
        val layoutManager = LinearLayoutManager(activity)
        binding.netrecyclerView.layoutManager = layoutManager
        adapter = NetworkAdapter(this, viewModel.contactList)
        binding.netrecyclerView.adapter = adapter

        /*viewModel.contactList.observe(this, Observer{ result ->

        })*/

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
                    parseJSONWithGSON(responseData)
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response:String){
        runOnUiThread {
            //进行UI操作，显示结果在界面上
            binding.netrecyclerView.text = response

        }

    }

    private fun parseJSONWithGSON(jsonData: String){
        val gson = Gson()
        val typeOf = object : TypeToken<List<ListResponse>>(){}.type
        val personList = gson.fromJson<List<ListResponse>>(jsonData, typeOf)
        for (person in personList){
            Log.d(TAG,"id is ${person.id}")
            Log.d(TAG,"full_name is ${person.name}")
            Log.d(TAG,"followers is ${person.followers}")
        }
    }
}