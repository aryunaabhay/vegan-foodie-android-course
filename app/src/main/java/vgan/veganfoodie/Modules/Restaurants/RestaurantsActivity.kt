package vgan.veganfoodie.Modules.Restaurants

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.restaurant_master_detail.*
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.Modules.RestaurantDetail.container.RestaurantDetailRouter
import vgan.veganfoodie.Modules.RestaurantDetail.map.RestaurantMapFragment
import vgan.veganfoodie.R


class RestaurantsActivity : AppCompatActivity(), RestaurantListDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        (this.fragment_restaurantsContainer as? RestaurantListFragment)?.delegatee = this
    }

    override fun OnTappedRestaurant(restaurant: Restaurant) {
        if (getResources().getString(R.string.deviceType) == "tablet" || this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            (this.fragment_map as? RestaurantMapFragment)?.updateRestaurant(restaurant)
        } else {
            RestaurantDetailRouter.showScreenIn(this, restaurant)
        }
    }

}