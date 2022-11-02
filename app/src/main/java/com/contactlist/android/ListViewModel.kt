package com.contactlist.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel(){
    //private val contactLiveData = MutableLiveData<String>()
    private val contactLiveData = MutableLiveData<ListResponse>()
    var id = ""
    var name = ""
    var followers = 0


    val contactList = ArrayList<ListResponse>()
    val contactData = Transformations.switchMap(){
         -> Repository
    }
}