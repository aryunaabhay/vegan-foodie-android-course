package vgan.veganfoodie.Modules.Dashboard

import android.content.Context
import android.content.Intent

/**
 * Created by aryuna on 11/11/17.
 */

class DashboardRouter {
    companion object {
        fun dashboard(ctx: Context) {
            var dashboardScreenIntent = Intent(ctx, DashboardActivity::class.java)
            ctx.startActivity(dashboardScreenIntent)
        }
    }
}