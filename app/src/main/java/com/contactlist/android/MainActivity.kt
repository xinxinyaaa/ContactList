package com.contactlist.android

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.contactlist.android.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.bottom.*

class MainActivity : BaseActivity(),View.OnClickListener {

    private val TAG = "MainActivity"

    //private lateinit var mobileTabPage: MobileFragment
    private var mobileTabPage: MobileFragment? = null
    //private  lateinit var networkTabPage: NetworkFragment
    private var networkTabPage: NetworkFragment? = null

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding



    private lateinit var mobileTabButton : LinearLayout
    private lateinit var networkTabButton : LinearLayout


    private lateinit var mobileImageButton: ImageButton
    private lateinit var networkImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initFragment()
        initView()
        initEvents()
        //initListeners()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    private fun initView(){
        mobileImageButton = findViewById(R.id.imageButton_mobile)
        networkImageButton = findViewById(R.id.imageButton_network)
        mobileTabButton = findViewById(R.id.mobile)
        networkTabButton = findViewById(R.id.network)

        mobileImageButton.setImageResource(R.mipmap.mobile_contacts)
        networkImageButton.setImageResource(R.mipmap.network_unpressed)

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
        mobileImageButton.setImageResource(R.mipmap.mobile_unpressed)
    }

    private fun selectTab(it:Int){
        fragmentManager = supportFragmentManager
        val transaction = fragmentManager!!.beginTransaction()
        hideFragment(transaction)
        FragmentUtil.fragmentUtil.getFragment(it)?.let { it1 ->
            transaction.replace(R.id.container,
                    it1,it.toString())
        }

        when(it){
            0 -> {

                mobileImageButton.setImageResource(R.mipmap.mobile_contacts)
                networkImageButton.setImageResource(R.mipmap.network_unpressed)
                binding.container.visibility = View.GONE

                if (mobileTabPage == null){
                    mobileTabPage = MobileFragment()
                    transaction.add(R.id.container, mobileTabPage!!)
                }else{
                    transaction.show(mobileTabPage!!)
                }
            }
            1 -> {
                networkImageButton.setImageResource(R.mipmap.network_contacts)
                mobileImageButton.setImageResource(R.mipmap.mobile_unpressed)
                binding.container.visibility = View.GONE
                if (networkTabPage == null){
                    networkTabPage == NetworkFragment()


                }else{
                    transaction.show(networkTabPage!!)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction){
        if (mobileTabPage != null){
            transaction.hide(mobileTabPage!!)

            //transaction.show(networkTabPage!!)
        }
        if (networkTabPage != null){
            transaction.hide(networkTabPage!!)
            // transaction.show(mobileTabPage!!)
        }
    }
}