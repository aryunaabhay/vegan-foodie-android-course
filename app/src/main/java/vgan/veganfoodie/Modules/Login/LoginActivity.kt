package vgan.veganfoodie.Modules.Login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.design.snackbar
import vgan.veganfoodie.Interfaces.AppViewModel
import vgan.veganfoodie.Interfaces.BaseActivity
import vgan.veganfoodie.Modules.Dashboard.DashboardRouter
import vgan.veganfoodie.Modules.SignUp.SignUpRouter
import vgan.veganfoodie.R




class LoginActivity : AppCompatActivity(), BaseActivity {

    override lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java!!)
        //observe vm values or update them  initial configuration
        this.email_field?.setText( (this.viewModel as? LoginViewModel)?.emailTxt)
        this.password_field?.setText((this.viewModel as? LoginViewModel)?.passTxt)
    }

    fun login(view: View){
        val email = this.email_field.text.toString()
        val password = this.password_field.text.toString()
        (this.viewModel as? LoginViewModel)?.login(email, password,{ result -> Unit
            if (result != null) {
                snackbar(view,  result.message)
                if (result.state == true) {
                    DashboardRouter.dashboard(this)
                }
            }
        })
    }

    fun goToSignUpScreen(view: View){
        SignUpRouter.signUpScreen(this)
    }
}
