package vgan.veganfoodie.Modules.Dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        this.descriptionView.text = AppDelegate.instance.viewModel.user?.description ?: ""
    }
}
