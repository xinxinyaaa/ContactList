package com.contactlist.android

class FragmentUtil private constructor(){

    val mobileFragment by lazy { MobileFragment() }
    val networkFragment by lazy { NetworkFragment() }

    companion object{
        val fragmentUtil by lazy { FragmentUtil()!! }
    }

    fun getFragment(tabId: Int): BaseFragment?{
        when(tabId){
            R.id.mobile -> return mobileFragment
            R.id.network -> return networkFragment
        }
        return null
    }
}