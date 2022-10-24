package com.contactlist.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel(){
    private val searchLiveData = MutableLiveData<String>()
    val contactList = ArrayList<ListResponse>()
    /*val contactLiveData = Transformations.switchMap(searchLiveData){
        query -> Repository
    }*/
}