package br.com.marcos2silva.marvel_compose

import com.google.gson.annotations.SerializedName

data class ErrorHandle(
    @SerializedName("code")
    val code: Int,

    @SerializedName("status")
    val message: String
)

