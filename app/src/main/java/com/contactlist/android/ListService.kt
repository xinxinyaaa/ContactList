package com.contactlist.android

import android.provider.ContactsContract
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListService {
    @GET("android10/Sample-Data/master/Android-CleanArchitecture/users.json")
    fun getListData():Call<List<ListResponse>>
}

//https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/users.json
