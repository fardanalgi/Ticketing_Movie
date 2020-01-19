package com.fardancompany.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fardancompany.mov.R
import kotlinx.android.synthetic.main.activity_onboarding_dua.*

class OnboardingDuaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_dua)

        btn_home.setOnClickListener {
            val intent = Intent(this@OnboardingDuaActivity,
                OnboardingTigaActivity::class.java)
            startActivity(intent)
        }
    }
}
