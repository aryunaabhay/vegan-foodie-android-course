package vgan.veganfoodie.Modules.Restaurants

import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.restAPI.RestaurantService

/**
 * Created by aryuna on 11/19/17.
 */

class RestaurantsViewModel: ViewModel {

    fun retrieveRestaurants(completion: (List<Restaurant>) -> Unit){
        RestaurantService.retrieveList { list -> Unit
            if(list != null) {
                completion(list)
            }
        }
    }

}