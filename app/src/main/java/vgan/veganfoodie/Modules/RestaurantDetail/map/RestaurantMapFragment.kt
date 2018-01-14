package vgan.veganfoodie.Modules.RestaurantDetail.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vgan.veganfoodie.R


class RestaurantMapFragment : Fragment() {

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
