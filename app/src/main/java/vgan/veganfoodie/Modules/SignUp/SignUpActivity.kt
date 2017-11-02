package vgan.veganfoodie.Modules.SignUp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_up.*
import vgan.veganfoodie.Interfaces.BaseActivity
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.R

class SignUpActivity : AppCompatActivity(), BaseActivity {
    override var viewModel: ViewModel = SignUpViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun signUpUser(view: View){
        //TODO: sign up 
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(SignUpViewModel.emailTxtIdentifier, this.emailField.text.toString())
        outState?.putString(SignUpViewModel.passTxtIdentifier, this.passwordField.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        this.emailField.setText(savedInstanceState?.getString(SignUpViewModel.emailTxtIdentifier))
        this.passwordField.setText(savedInstanceState?.getString(SignUpViewModel.passTxtIdentifier))
    }
}
