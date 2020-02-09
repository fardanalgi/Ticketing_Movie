package com.fardancompany.mov.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fardancompany.mov.R
import com.fardancompany.mov.checkout.model.Checkout
import com.fardancompany.mov.utils.Preferences


class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total: Int = 0

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))


    }
}
