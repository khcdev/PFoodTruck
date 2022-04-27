package com.hungryduck.foodtruck.api.model

data class NotiResponse(
    val id: Long,
    val category: String,
    val title: String,
    val message: String,
    val status: String,
    val location: String
) {
    companion object {
        fun getDefaultError() = NotiResponse(
            id = 0,
            title = "서비스 접속 문제가 발생합니다.",
            category = "error",
            message = "잠시 후 다시 시도해주세요",
            status = "error",
            location = "error"
        )
    }
}