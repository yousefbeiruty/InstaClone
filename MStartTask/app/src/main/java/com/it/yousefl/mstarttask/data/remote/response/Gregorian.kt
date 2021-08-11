package com.it.yousefl.mstarttask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Gregorian(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: String,
    @SerializedName("designation")
    val designation: Designation,
    @SerializedName("format")
    val format: String,
    @SerializedName("month")
    val month: Month,
    @SerializedName("weekday")
    val weekday: Weekday,
    @SerializedName("year")
    val year: String
)