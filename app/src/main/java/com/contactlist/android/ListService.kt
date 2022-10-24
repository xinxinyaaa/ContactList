package com.contactlist.android

import android.provider.ContactsContract
import retrofit2.Call
import retrofit2.http.GET

interface ListService {
    @GET("users.json")
    fun getListData():Call<ListResponse>
}

//https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/users.json
