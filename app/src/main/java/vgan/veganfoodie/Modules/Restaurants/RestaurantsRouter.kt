package vgan.veganfoodie.Modules.Restaurants

import android.content.Context
import android.content.Intent

/**
 * Created by aryuna on 11/22/17.
 */

class RestaurantsRouter {

companion object {
    fun restaurantListScreen(ctx: Context) {
        var restaurantsIntent = Intent(ctx, RestaurantsActivity::class.java)
        ctx.startActivity(restaurantsIntent)
    }
}

}