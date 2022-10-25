package com.contactlist.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.contactlist.android.Fragment.networkFragment
import com.contactlist.android.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(),View.OnClickListener {

    private val TAG = "MainActivity"

    private var mobileTabPage: mobileFragment? = null
    private val networkTabPage: networkFragment? = null

    private var fragmentManager: FragmentManager? = null
    private lateinit var binding: ActivityMainBinding



    private lateinit var mobileTabButton : LinearLayout
    private lateinit var networkTabButton : LinearLayout


    private lateinit var mobileImageButton: ImageButton
    private lateinit var networkImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initFragment()
        initView()
        initEvents()
    }

    private fun initView(){
        mobileImageButton = findViewById(R.id.imageButton_mobile)
        networkImageButton = findViewById(R.id.imageButton_network)
        mobileTabButton = findViewById(R.id.mobile)
        networkTabButton = findViewById(R.id.network)
        mobileImageButton.setImageResource(R.drawable.mobile_contacts)
        networkImageButton.setImageResource(R.drawable.network_unpressed)
    }

    private fun initEvents(){
        mobileTabButton.setOnClickListener(this)
        networkTabButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        resetImg()
        when(v?.id){
            R.id.mobile -> {
                selectTab(0)
            }
            R.id.network -> {
                selectTab(1)
            }
        }
    }

    private fun resetImg(){
        mobileImageButton.setImageResource(R.drawable.mobile_unpressed)
    }
    private fun selectTab(i:Int){
        fragmentManager = supportFragmentManager
        val transaction = fragmentManager!!.beginTransaction()
        hideFragment(transaction)
        when(i){
            0 -> {
                mobileImageButton.setImageResource(R.drawable.mobile_contacts)
                networkImageButton.setImageResource(R.drawable.network_unpressed)
                if (mobileTabPage == null){
                    mobileTabPage = mobileFragment()
                    transaction.add(R.id.content, mobileTabPage!!)
                }else{
                    transaction.show(mobileTabPage!!)
                }
            }
            1 -> {
                networkImageButton.setImageResource(R.drawable.network_contacts)
                mobileImageButton.setImageResource(R.drawable.mobile_unpressed)
                if (networkTabPage == null){
                    networkTabPage == networkFragment()


                }else{
                    transaction.show(networkTabPage)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction){
        if (mobileTabPage != null){
            transaction.hide(mobileTabPage!!)

            transaction.show(networkTabPage!!)
        }
        if (networkTabPage != null){
            transaction.hide(networkTabPage!!)
            transaction.show(mobileTabPage!!)
        }
    }
}