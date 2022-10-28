package com.contactlist.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel(){
    //private val contactLiveData = MutableLiveData<String>()
    //private val contactLiveData = MutableLiveData<ListResponse>()
    val contactList = ArrayList<ListResponse>()
    /*val contactLiveData = Transformations.switchMap(searchLiveData){
        query -> Repository
    }*/
}