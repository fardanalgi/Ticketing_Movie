package com.fardancompany.mov.sign.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.fardancompany.mov.R
import android.widget.Toast
import com.fardancompany.mov.home.HomeActivity
import com.fardancompany.mov.sign.signup.SignUpActivity
import com.fardancompany.mov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*



class SignInActivity : AppCompatActivity() {

    lateinit var iUsername : String
    lateinit var iPassword : String

    lateinit var mDatabase : DatabaseReference
    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")){
            finishAffinity()

            val intent = Intent(this@SignInActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }

       btn_home.setOnClickListener {
           iUsername = et_username.text.toString()
           iPassword = et_password.text.toString()

           if(iUsername.equals("")){
               et_username.error = "Silakan tulis Username Anda"
               et_username.requestFocus()
           }else if (iPassword.equals("")) {
               et_password.error = "Silakan tulis Password Anda"
               et_password.requestFocus()
           }else{
               pushLogin(iUsername, iPassword)
           }
       }

        btn_daftar.setOnClickListener {
            val intent = Intent (this@SignInActivity,
                SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String){
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG)
                        .show()
                }else{
                    if(user.password.equals(iPassword)){
                        Toast.makeText(this@SignInActivity, "Selamat datang", Toast.LENGTH_LONG).show()

                        preference.setValues("name", user.nama.toString())
                        preference.setValues("user", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")
//
                        finishAffinity()

                        val intent = Intent(this@SignInActivity,
                            HomeActivity::class.java)
                        startActivity(intent)
                        
                    }else{
                        Toast.makeText(this@SignInActivity, "Password Anda Salah", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
