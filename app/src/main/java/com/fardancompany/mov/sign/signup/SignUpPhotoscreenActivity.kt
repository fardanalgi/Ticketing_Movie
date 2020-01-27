package com.fardancompany.mov.sign.signup

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fardancompany.mov.R

class SignUpPhotoscreenActivity : AppCompatActivity() {

    val REQUEST_TIME_CAPTURE = 1
    var statusAdd: Boolean = false
    lateinit var filePath : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)
    }
}
