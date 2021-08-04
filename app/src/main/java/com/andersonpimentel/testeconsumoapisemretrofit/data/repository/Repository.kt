package com.andersonpimentel.testeconsumoapisemretrofit.data.repository

import com.andersonpimentel.testeconsumoapisemretrofit.data.api.ApiClientInstance
import com.andersonpimentel.testeconsumoapisemretrofit.data.api.GetApiData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Repository constructor(private val getApiData: GetApiData) {

    suspend fun getStringQuotesList(): String{
        var inline = ""
        lateinit var scanner: Scanner
        CoroutineScope(Dispatchers.IO).async {
            val url =
                URL("${ApiClientInstance.BASE_URL}/live?access_key=${ApiClientInstance.ACCESS_KEY}")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            scanner = Scanner(conn.url.openStream())
            while (scanner.hasNext()) {
                inline += scanner.nextLine()
            }
            scanner.close()
        }.await()
        return inline
    }

    suspend fun getStringCurrenciesList(): String{
        var inline = ""
        lateinit var scanner: Scanner
        CoroutineScope(Dispatchers.IO).async {
            val url =
                URL("${ApiClientInstance.BASE_URL}/list?access_key=${ApiClientInstance.ACCESS_KEY}")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            scanner = Scanner(conn.url.openStream())
            while (scanner.hasNext()) {
                inline += scanner.nextLine()
            }
            scanner.close()
        }.await()
        return inline
    }
}