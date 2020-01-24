package com.fardancompany.mov.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fardancompany.mov.R
import com.fardancompany.mov.utils.Preferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername : String
    lateinit var sPassword : String
    lateinit var sNama : String
    lateinit var sEmail : String

    private lateinit var mFirebaseDatabase : DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        btn_home.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error = "Silakan isi Username"
                et_username.requestFocus()
            }else if (sPassword.equals("")) {
                et_password.error = "Silakan isi Password"
                et_password.requestFocus()
            }else if (sNama.equals("")){
                et_nama.error = "Silakan isi Nama"
                et_nama.requestFocus()
            }else if (sEmail.equals("")){
                et_email.error = "Silakan isi Email"
                et_email.requestFocus()
            }else{

//                save(sUsername, sPassword, sNama, sEmail)
            }
        }
    }
}
