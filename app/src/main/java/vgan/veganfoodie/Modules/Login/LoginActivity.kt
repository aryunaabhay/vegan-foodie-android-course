package vgan.veganfoodie.Modules.Login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login_acivity.*
import vgan.veganfoodie.Modules.SignUp.SignUpRouter
import vgan.veganfoodie.R

class LoginActivity : AppCompatActivity() {

    val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_acivity)
    }

    fun login(view: View){
        val loginResult = this.viewModel.login(this.emailField.text.toString(), this.passwordField.text.toString())
        if(loginResult){
            //TODO: Intent to the main screen of the app
        }else{
            //TODO: Show error
        }
    }

    fun goToSignUpScreen(view: View){
        SignUpRouter.signUpScreen(this)
    }
}
