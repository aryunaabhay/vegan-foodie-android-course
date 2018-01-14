package vgan.veganfoodie.Modules.Dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import vgan.veganfoodie.Modules.Restaurants.RestaurantsRouter
import vgan.veganfoodie.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    fun showRestaurants(v: View) {
        RestaurantsRouter.restaurantListScreen(this)
    }
}
