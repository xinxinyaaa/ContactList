package com.contactlist.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel(){
    //private val contactLiveData = MutableLiveData<String>()
    val contactLiveData = MutableLiveData<ListResponse>()
    var id = ""
    var name = ""
    var followers = 0


    val contactList = ArrayList<ListResponse>()
    private fun initList(){
        //contactList.add(ListResponse(id),)
    }
    /*val contactData = Transformations.switchMap(){
         -> Repository
    }*/
}