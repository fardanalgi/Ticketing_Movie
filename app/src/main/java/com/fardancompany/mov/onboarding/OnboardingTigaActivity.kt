package com.fardancompany.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fardancompany.mov.R
import com.fardancompany.mov.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_tiga.*

class OnboardingTigaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_tiga)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent (this@OnboardingTigaActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
