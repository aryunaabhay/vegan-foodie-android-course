package vgan.veganfoodie.Modules.SignUp

import android.content.Context
import android.content.Intent

/**
 * Created by aryuna on 10/28/17.
 */

class SignUpRouter {

    companion object {
        fun signUpScreen(ctx: Context) {
            var signUpScreenIntent = Intent(ctx, SignUpActivity::class.java)
            ctx.startActivity(signUpScreenIntent)
        }
    }
}