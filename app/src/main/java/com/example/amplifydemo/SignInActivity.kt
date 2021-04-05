package com.example.amplifydemo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.core.Amplify
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        signinBtn.setOnClickListener {
            if(TextUtils.isEmpty(usernameSigninEdt.text.toString())){
                Toast.makeText(this,"plz enter username",Toast.LENGTH_SHORT).show()
            }else if(TextUtils.isEmpty(passwordSigninEdt.text.toString())){
                Toast.makeText(this,"plz enter password",Toast.LENGTH_SHORT).show()
            }else {
                Amplify.Auth.signIn(usernameSigninEdt.text.toString(),
                    passwordSigninEdt.text.toString(),
                    { result ->
                        if (result.isSignInComplete) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Log.i("AuthQuickstart", "Sign in succeeded")
                        } else {
                            Log.i("AuthQuickstart", "Sign in not complete")
                        }
                    },
                    { Log.e("AuthQuickstart", "Failed to sign in", it) }
                )
            }
        }

        registerLabel.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

    }

}