package vgan.veganfoodie.restAPI

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.Managers.RetroFitManager

/**
 * Created by aryuna on 11/19/17.
 */

interface RestaurantApi {
    companion object {
        const val ROOT = "restaurants"
    }

    @GET(RestaurantApi.ROOT)
    fun retrieveList(): Call<List<Restaurant>>
}

class RestaurantService {
    companion object {
        fun api(): RestaurantApi {
            return RetroFitManager.instance.retrofit.create(RestaurantApi::class.java)
        }

        fun retrieveList(completion: (List<Restaurant>?) -> Unit ){
            val listReq = RestaurantService.api().retrieveList()
            listReq.enqueue(object: Callback<List<Restaurant>> {
                override fun onResponse(call: Call<List<Restaurant>>?, response: Response<List<Restaurant>>?) {
                    var restaurants = response?.body()
                    if (restaurants != null) {
                        //val realm = Realm.getDefaultInstance()
                        //realm.copyToRealmOrUpdate(list)
                        //TODO run this as a transaction  execureTransactionAsync
                        completion(restaurants)
                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>?, t: Throwable?) {
                    completion(null)
                }
            })
        }
    }
}