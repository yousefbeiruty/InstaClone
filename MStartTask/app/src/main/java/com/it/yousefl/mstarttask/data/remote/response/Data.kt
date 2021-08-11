package com.it.yousefl.mstarttask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("gregorian")
    val gregorian: Gregorian,
    @SerializedName("hijri")
    val hijri: Hijri
)