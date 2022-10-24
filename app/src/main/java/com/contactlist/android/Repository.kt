package com.contactlist.android

import kotlinx.coroutines.Dispatchers

object Repository {
    fun getListData() = liveData(Dispatchers.IO){
        val result = try {
            val listResponse = ContactListNetwork.getListData()
            if (listResponse.id == "ok"){
                val full = listResponse.full_name
                Result.success(full)
            }else{
                Result.failure(RuntimeException("response status is ${listResponse.id}"))
            }
        }catch (e:Exception){
            Result.failure<List<ListResponse>>(e)
        }
        emit(result)
    }
}