package com.andersonpimentel.testeconsumoapisemretrofit.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientInstance {

    var retrofit: Retrofit? = null
    val BASE_URL = "http://api.currencylayer.com/"
    val ACCESS_KEY = "d59fa274e36c79726a39835b05f2b570"

    //create a retrofit instance
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

}