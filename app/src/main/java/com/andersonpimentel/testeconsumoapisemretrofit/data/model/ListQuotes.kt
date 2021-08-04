package com.andersonpimentel.testeconsumoapisemretrofit.data.model

import com.google.gson.annotations.SerializedName

data class ListQuotes(
    @SerializedName("quotes")
    val quotes: Map<String, Double>
    )