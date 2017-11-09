package vgan.veganfoodie.Modules.SignUp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
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
        val email = this.email_field.text.toString()
        val password = this.password_field.text.toString()
        val signUpResult = (this.viewModel as? SignUpViewModel)?.signup(email, password)
        if (signUpResult != null) {
            Toast.makeText(this.applicationContext, signUpResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(SignUpViewModel.emailTxtIdentifier, this.email_field.text.toString())
        outState?.putString(SignUpViewModel.passTxtIdentifier, this.password_field.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        this.email_field?.setText(savedInstanceState?.getString(SignUpViewModel.emailTxtIdentifier) ?: "")
        this.password_field?.setText(savedInstanceState?.getString(SignUpViewModel.passTxtIdentifier) ?: "" )
    }
}
