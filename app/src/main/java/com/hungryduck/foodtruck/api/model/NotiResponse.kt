package com.hungryduck.foodtruck.api.model

data class NotiResponse(
    val id: Long,
    val category: String,
    val title: String,
    val message: String,
    val status: String,
    val location: String
)