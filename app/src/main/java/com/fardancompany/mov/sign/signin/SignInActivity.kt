package com.fardancompany.mov.sign.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.firebase.database.FirebaseDatabase

import com.fardancompany.mov.R
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername : String
    lateinit var iPassword : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

       btn_home.setOnClickListener {
           iUsername = et_username.text.toString()
           iPassword = et_password.text.toString()

           if(iUsername.equals("")){
               et_username.error = "Silakan tulis Username Anda"
               et_username.requestFocus()
           }else if (iPassword.equals("")) {
               et_password.error = "Silakan tulis Password Anda"
               et_password.requestFocus()
//           }else{
//               pushLogin(iUsername, iPassword)
           }
       }
    }

//    private fun pushLogin(iUsername: String, iPassword: String){
//        mDatabase.child(iUsername).addvalueEventListener(object : ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                val user = dataSnapshot.getValue(User::class.java)
//
//            }
//        })
//    }
}
