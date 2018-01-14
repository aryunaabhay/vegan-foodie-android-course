package vgan.veganfoodie.Modules.RestaurantDetail.container

import android.arch.lifecycle.ViewModel
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import vgan.veganfoodie.Interfaces.AppViewModel
import vgan.veganfoodie.Modules.RestaurantDetail.info.RestaurantInfoFragment
import vgan.veganfoodie.Modules.RestaurantDetail.map.RestaurantMapFragment

/**
 * Created by aryuna on 12/20/17.
 */
class RestaurantDetailViewModel(): AppViewModel, ViewModel() {

    lateinit var dataSource: RestaurantDetailAdapter
    lateinit var fm: FragmentManager
    var restaurantId: Int = 0

}

class RestaurantDetailAdapter(var fm: FragmentManager, var restaurantId: Int) : FragmentPagerAdapter(fm) {

    lateinit var tabsData: List<RestaurantTabInfo>

    init {
         this.tabsData = this.generateData()
    }

    override fun getCount(): Int {
        return this.tabsData.count()
    }

    override fun getItem(position: Int): Fragment {
        return tabsData[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabsData[position].title
    }

    fun generateData(): List<RestaurantTabInfo> {
        val tabInfo = RestaurantTabInfo("Info", RestaurantInfoFragment.newInstance(this.restaurantId))
        val tabMap = RestaurantTabInfo("Map", RestaurantMapFragment.newInstance(this.restaurantId))
        return arrayListOf(tabInfo, tabMap)
    }
}

class RestaurantTabInfo(var title: String, var fragment: Fragment) {

}