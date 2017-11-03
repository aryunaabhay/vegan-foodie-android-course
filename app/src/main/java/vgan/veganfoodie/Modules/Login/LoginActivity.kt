package vgan.veganfoodie.Modules.Login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login_acivity.*
import vgan.veganfoodie.Interfaces.BaseActivity
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.Modules.SignUp.SignUpRouter
import vgan.veganfoodie.R

class LoginActivity : AppCompatActivity(), BaseActivity {

    override var viewModel: ViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_acivity)
    }

    fun login(view: View){
        val loginResult = (this.viewModel as? LoginViewModel)?.login(this.email_field.text.toString(), this.password_field.text.toString())
        if(loginResult ?: false){
            //TODO: Intent to the main screen of the app
        }else{
            //TODO: Show error
        }
    }

    fun goToSignUpScreen(view: View){
        SignUpRouter.signUpScreen(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(LoginViewModel.emailTxtIdentifier, this.email_field.text.toString())
        outState?.putString(LoginViewModel.passTxtIdentifier, this.password_field.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        this.email_field?.setText(savedInstanceState?.getString(LoginViewModel.emailTxtIdentifier) ?: "")
        this.password_field?.setText(savedInstanceState?.getString(LoginViewModel.passTxtIdentifier) ?: "")
    }
}
