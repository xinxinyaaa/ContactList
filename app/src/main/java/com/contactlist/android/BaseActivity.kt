package com.contactlist.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.contactlist.android.ContactListApplication.Companion.context

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListeners()
        initData()
    }

    open protected fun initData() {

    }

    open protected fun initListeners() {

    }

    /*protected fun showToast(msg: String) {
            runOnUiThread { toast(msg) }
        }*/


    protected abstract fun getLayoutId(): Int
}
