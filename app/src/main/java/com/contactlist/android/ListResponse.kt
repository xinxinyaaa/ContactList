package com.contactlist.android

import com.google.gson.annotations.SerializedName

data class ListResponse(val id: String,
                        @SerializedName("full_name") val name: String, val followers: Int)
