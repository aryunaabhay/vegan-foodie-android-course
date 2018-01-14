package vgan.veganfoodie.Modules.RestaurantDetail.container

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import vgan.veganfoodie.Entities.Restaurant

/**
 * Created by aryuna on 12/25/17.
 */
class RestaurantDetailRouter {
    companion object {

        fun showScreenIn(ctx: Context, restaurant: Restaurant) {
            var restaurantIntent = Intent(ctx, RestaurantDetailActivity::class.java)
            RestaurantDetailRouter.screenSetup(restaurantIntent, restaurant)
            ctx.startActivity(restaurantIntent)
        }

        private fun screenSetup(intent: Intent, restaurant: Restaurant) {
            intent.putExtra("restaurant_id", restaurant.id)
        }

        fun viewModel(ctx: AppCompatActivity): RestaurantDetailViewModel {
            val vm = ViewModelProviders.of(ctx).get(RestaurantDetailViewModel::class.java!!)
            val supportFmManager = ctx.supportFragmentManager
            val restaurantId = ctx.intent.getIntExtra("restaurant_id", 0)
            vm.restaurantId = restaurantId
            vm.dataSource = RestaurantDetailAdapter(supportFmManager, restaurantId)
            vm.fm = supportFmManager
            return vm
        }

    }
}