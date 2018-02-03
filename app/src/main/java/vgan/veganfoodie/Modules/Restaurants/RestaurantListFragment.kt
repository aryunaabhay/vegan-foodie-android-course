package vgan.veganfoodie.Modules.Restaurants


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.Interfaces.AppViewModel
import vgan.veganfoodie.R


/**
 * A simple [Fragment] subclass.
 */
class RestaurantListFragment : Fragment(), RestaurantsListAdapterDelegate {
    var viewModel: AppViewModel = RestaurantsViewModel()
    var delegatee: RestaurantListDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.configure()
    }

    fun configure() {
        this.configureRestaurantsRecycler()
        this.loadRestaurants()
    }

    fun configureRestaurantsRecycler(){

        val layoutManager = LinearLayoutManager(this.activity.applicationContext, LinearLayoutManager.VERTICAL, false)
        val gridLayoutManager = GridLayoutManager(this.activity.applicationContext, 2)
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
        this.delegatee?.OnTappedRestaurant(restaurant)
    }

}

interface RestaurantListDelegate {
    fun OnTappedRestaurant(restaurant: Restaurant)
}
