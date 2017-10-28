package vgan.veganfoodie.Modules.SignUp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import vgan.veganfoodie.Interfaces.BaseActivity
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), BaseActivity {
    override var viewModel: ViewModel = SignUpViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }
}
