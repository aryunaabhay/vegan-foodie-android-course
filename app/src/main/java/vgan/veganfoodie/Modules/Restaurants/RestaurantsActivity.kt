package vgan.veganfoodie.Modules.Restaurants

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_restaurants.*
import vgan.veganfoodie.Interfaces.BaseActivity
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.R





class RestaurantsActivity : AppCompatActivity(), BaseActivity {
    override var viewModel: ViewModel = RestaurantsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        this.configureRestaurantsRecycler()
    }

    fun configureRestaurantsRecycler(){
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val gridLayoutManager = GridLayoutManager(application, 2)
        this.restaurants_recycler_view.setLayoutManager(layoutManager)
        this.restaurants_recycler_view.setHasFixedSize(true)
        this.restaurants_recycler_view.adapter = RestaurantsListAdapter()
        this.loadRestaurants()
    }

    fun loadRestaurants(){
        (this.viewModel as? RestaurantsViewModel)?.retrieveRestaurants({ list -> Unit
            (this.restaurants_recycler_view.adapter as? RestaurantsListAdapter)?.setData(list)
        })
    }

}