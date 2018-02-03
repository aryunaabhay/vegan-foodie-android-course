package vgan.veganfoodie.Modules.RestaurantDetail.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_restaurant_map.*
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.R


class RestaurantMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            //mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_restaurant_map, container, false)

        val mapFragment: SupportMapFragment  = this.map as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun updateRestaurant(restaurant: Restaurant) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    companion object {

        fun newInstance(restaurantId: Int): RestaurantMapFragment {
            val fragment = RestaurantMapFragment()
            val args = Bundle()
            args.putInt("restaurant_id", restaurantId)
            fragment.arguments = args
            return fragment
        }
    }
}
