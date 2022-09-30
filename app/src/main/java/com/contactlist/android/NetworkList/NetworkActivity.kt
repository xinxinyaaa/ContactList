package com.example.networktest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networktest.databinding.ActivityMainBinding
import com.example.networktest.databinding.ActivityNetworkBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import kotlin.concurrent.thread
import kotlin.reflect.typeOf

class NetworkActivity : AppCompatActivity() {
    val TAG = "NetworkActivity"
    private lateinit var binding: ActivityNetworkBinding
    lateinit var viewModel: NetworkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)



        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendRequestBtn.setOnClickListener {
            //sendRequestWithHttpURLConnection()
            sendRequestWithOkHttp()
        }
    }

    /*private fun sendRequestWithHttpURLConnection(){
        //开启线程发起网络请求
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                val input = connection.inputStream
                //对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }
    }*/
    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                //val request = Request.Builder().url("https://www.baidu.com").build()
                val request = Request.Builder().url("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/users.json").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null){
                    showResponse(responseData)
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
            binding.responseText.text = response

        }

    }
    /*private fun parseJSONWithJSONObject(jsonData: String){
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val full_name = jsonObject.getString("full_name")
                val followers = jsonObject.getString("followers")
                Log.d("MainActivity","id is $id")
                Log.d("MainActivity","full_name is $full_name")
                Log.d("MainActivity","id is $followers")

            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }*/

    private fun parseJSONWithGSON(jsonData: String){
        val gson = Gson()
        val typeOf = object : TypeToken<List<Person>>(){}.type
        val personList = gson.fromJson<List<Person>>(jsonData, typeOf)
        for (person in personList){
            Log.d(TAG,"id is ${person.id}")
            Log.d(TAG,"full_name is ${person.full_name}")
            Log.d(TAG,"followers is ${person.followers}")
        }
    }
}