package vgan.veganfoodie.Modules.Restaurants

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.R

/**
 * Created by aryuna on 11/24/17.
 */

class RestaurantsListAdapter: RecyclerView.Adapter<RestaurantViewHolder>() {
    private var restaurants: List<Restaurant> = emptyList<Restaurant>()
    var delegate: RestaurantsListAdapterDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item_view, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
        holder.itemView.setOnClickListener {
            this.delegate?.OnTapRestaurant(restaurant)
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    fun setData(restaurants: List<Restaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

}

// restaurant view cell wrapper
class RestaurantViewHolder(view: View): RecyclerView.ViewHolder(view) {
    lateinit var titleTxtView: TextView
    lateinit var descTxtView: TextView

    init {
        //this(view)
        //binding properties with layout
        this.titleTxtView = view.findViewById<TextView>(R.id.restaurant_title_text)
        this.descTxtView = view.findViewById<TextView>(R.id.restaurant_specialty_text)

    }

    fun bind(restaurant: Restaurant) {
        this.titleTxtView.text = restaurant.name
        this.descTxtView.text = restaurant.specialty
    }
}

interface RestaurantsListAdapterDelegate {
    fun OnTapRestaurant(restaurant: Restaurant)
}
