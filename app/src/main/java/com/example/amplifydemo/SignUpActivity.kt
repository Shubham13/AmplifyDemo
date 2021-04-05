package com.example.amplifydemo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        submitBtn.setOnClickListener {
            if(TextUtils.isEmpty(emailEdt.text.toString())){
                Toast.makeText(this,"Plz enter email",Toast.LENGTH_SHORT).show()
            }else if(TextUtils.isEmpty(usernameEdt.text.toString())){
                Toast.makeText(this,"Plz enter username",Toast.LENGTH_SHORT).show()
            }else if(TextUtils.isEmpty(passwordEdt.text.toString())){
                Toast.makeText(this,"Plz enter password",Toast.LENGTH_SHORT).show()
            }else {
                val options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), emailEdt.text.toString())
                    .build()
                Amplify.Auth.signUp(usernameEdt.text.toString(), passwordEdt.text.toString(), options,
                    {
                        val intent = Intent(this,OtpActivity::class.java)
                        intent.putExtra("username", usernameEdt.text.toString())
                        startActivity(intent)
                        Log.i("AuthQuickStart", "Sign up succeeded: $it")
                    },
                    { Log.e("AuthQuickStart", "Sign up failed", it) }
                )
            }
        }

    }
}