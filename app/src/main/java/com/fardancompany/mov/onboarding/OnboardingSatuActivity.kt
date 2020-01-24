package com.fardancompany.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fardancompany.mov.HomeActivity
import com.fardancompany.mov.R
import com.fardancompany.mov.sign.signin.SignInActivity
import com.fardancompany.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_satu.*

class OnboardingSatuActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_satu)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")){
            finishAffinity()

            val intent = Intent (this@OnboardingSatuActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        btn_home.setOnClickListener {
            val intent = Intent(this@OnboardingSatuActivity,
                OnboardingDuaActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingSatuActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
