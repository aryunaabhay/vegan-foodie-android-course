package vgan.veganfoodie.Modules.RestaurantDetail.info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_restaurant_info.*
import vgan.veganfoodie.R

class RestaurantInfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
           this.restaurantNameTxt?.text = arguments.getInt("restaurant_id")?.toString() ?: "0"
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_restaurant_info, container, false)
    }

    companion object {
        fun newInstance(restaurantId: Int): RestaurantInfoFragment {
            val fragment = RestaurantInfoFragment()
            val args = Bundle()
            args.putInt("restaurant_id", restaurantId)
            fragment.arguments = args
            return fragment
        }
    }
}
