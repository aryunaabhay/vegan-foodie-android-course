package vgan.veganfoodie.Managers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by aryuna on 11/19/17.
 */

class RetroFitManager {

    lateinit var retrofit: Retrofit

    companion object {
        val BASE_URL = "http://fullstackapi.herokuapp.com/"
        lateinit var instance: RetroFitManager

        @Synchronized
        fun init() {
            RetroFitManager.instance = RetroFitManager()
            val retrofit = Retrofit.Builder()
                    .baseUrl(RetroFitManager.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            RetroFitManager.instance.retrofit = retrofit
        }
    }
}