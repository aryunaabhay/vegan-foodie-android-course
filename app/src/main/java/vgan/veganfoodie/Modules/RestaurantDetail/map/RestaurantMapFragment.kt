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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import vgan.veganfoodie.Entities.Restaurant
import vgan.veganfoodie.R


class RestaurantMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var marker: Marker? = null

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
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun updateRestaurant(restaurant: Restaurant) {
        this.setMarker(LatLng(restaurant.lat, restaurant.long))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mMap = googleMap
    }

    fun setMarker(coordinates: LatLng){
        val mark = this.marker
        if (mark != null) {
            mark.position = coordinates
        }else {
            this.marker = this.mMap.addMarker(MarkerOptions().position(coordinates))
        }
        // 1 world, 5 continent, 10 city, 15 streets, 20 buildings
        // tilt : inclination in grades
        // bearing: rotation
        val camPosition = CameraPosition.builder().zoom(15f)
        camPosition.target(coordinates)
        this.mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPosition.build()))
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
