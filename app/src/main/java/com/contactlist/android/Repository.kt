package com.contactlist.android

import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {
    /*fun getListData() = fire(Dispatchers.IO){
        val listResponse = ContactListNetwork.getListData()
        if (listResponse.id == "ok"){
            val full = listResponse.name
            Result.success(full)
        }else{
            Result.failure(RuntimeException("response status is ${listResponse.id}"))
        }

    }

    private fun<T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context){
        val result = try {
            block()
        }catch (e:Exception){
            Result.failure<List<ListResponse>>(e)
        }
        emit(result)
    }*/

}