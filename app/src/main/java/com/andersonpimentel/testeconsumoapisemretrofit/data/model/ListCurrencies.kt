package com.andersonpimentel.testeconsumoapisemretrofit.data.model

import com.google.gson.annotations.SerializedName

data class ListCurrencies(
    @SerializedName("currencies")
    val currencies: Map<String, String>,
)