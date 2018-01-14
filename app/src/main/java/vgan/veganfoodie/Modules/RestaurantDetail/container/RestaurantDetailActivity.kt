package vgan.veganfoodie.Modules.RestaurantDetail.container

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import vgan.veganfoodie.R


class RestaurantDetailActivity : AppCompatActivity() {

    lateinit var viewModel: RestaurantDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        this.configure()
    }

    fun configure(){
        this.viewModel = RestaurantDetailRouter.viewModel(this)
        this.configureViews()
    }

    fun configureViews(){
        //restaurant from vm  ?
        //pass restaurant to fragments o vm fragments
        this.viewpager.adapter = this.viewModel.dataSource
        this.tabcontainer.setupWithViewPager(this.viewpager)
        this.viewpager.currentItem = 0
    }
}
