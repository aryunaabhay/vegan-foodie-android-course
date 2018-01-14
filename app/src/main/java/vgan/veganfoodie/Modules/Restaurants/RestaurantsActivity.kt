package vgan.veganfoodie.Modules.Restaurants

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_restaurants.*
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.Interfaces.AppViewModel
import vgan.veganfoodie.Interfaces.BaseActivity
import vgan.veganfoodie.Modules.RestaurantDetail.container.RestaurantDetailRouter
import vgan.veganfoodie.R





class RestaurantsActivity : AppCompatActivity(), BaseActivity, RestaurantsListAdapterDelegate {

    override var viewModel: AppViewModel = RestaurantsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        this.configure()
    }

    fun configure() {
        this.configureRestaurantsRecycler()
        this.loadRestaurants()
    }

    fun configureRestaurantsRecycler(){
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val gridLayoutManager = GridLayoutManager(application, 2)
        this.restaurants_recycler_view.setLayoutManager(layoutManager)
        this.restaurants_recycler_view.setHasFixedSize(true)

        val adapter = RestaurantsListAdapter()
        adapter.delegate = this
        this.restaurants_recycler_view.adapter = adapter
    }

    fun loadRestaurants(){
        (this.viewModel as? RestaurantsViewModel)?.retrieveRestaurants({ list -> Unit
            (this.restaurants_recycler_view.adapter as? RestaurantsListAdapter)?.setData(list)
        })
    }

    // tapped restaurant
    override fun OnTapRestaurant(restaurant: Restaurant) {
        // open  restaurant detail activity
        // router passing restaurant id and ctx to start the activity
        RestaurantDetailRouter.showScreenIn(this, restaurant)
    }

}