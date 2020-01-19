package com.fardancompany.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fardancompany.mov.onboarding.OnboardingSatuActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreen,
                OnboardingSatuActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}
