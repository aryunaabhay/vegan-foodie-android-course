package vgan.veganfoodie.restAPI

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import vgan.veganfoodie.Entities.User
import vgan.veganfoodie.Managers.RetroFitManager

/**
 * Created by aryuna on 11/19/17.
 */

interface UserAPI {

    companion object {
        const val ROOT = "users"
        const val login = "/login"
    }

    @POST(UserAPI.ROOT + UserAPI.login)
    fun login(@Query("email") email: String,
              @Query("password") password: String): Call<User>

    @POST(UserAPI.ROOT)
    fun signUp(@Query("email") email: String,
               @Query("password") password: String): Call<User>

}

class UserService {

    companion object {

        val api: UserAPI
            get() = RetroFitManager.instance.retrofit.create(UserAPI::class.java)


        fun login(email: String, password: String, completion: (user: User?) -> Unit){

            val loginReq = UserService.api.login(email, password)
            loginReq.enqueueWithCompletion({ t, response -> Unit
                 var user = response?.body()
                if (user != null) {
                    completion(user)
                }else{
                    completion(null)
                }
            })
        }

        fun signUp(email: String, password: String, completion: (user: User?) -> Unit) {
            val signupReq = UserService.api.signUp(email, password)

            signupReq.enqueueWithCompletion({ t, response -> Unit
                var user = response?.body()
                if (user != null) {
                    completion(user)
                }else{
                    completion(null)
                }
            })

        }
    }
}


// extension
fun <T> Call<T>.enqueueWithCompletion(completion: (t: Throwable?, response: Response<T>?) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            completion(null, response)
        }
        override fun onFailure(call: Call<T>?, t: Throwable) {
            completion(t, null)
        }
    })
}