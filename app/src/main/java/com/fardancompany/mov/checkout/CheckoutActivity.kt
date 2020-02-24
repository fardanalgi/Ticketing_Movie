package com.fardancompany.mov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fardancompany.mov.R
import com.fardancompany.mov.checkout.adapter.CheckoutAdapter
import com.fardancompany.mov.checkout.model.Checkout
import com.fardancompany.mov.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

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

        btn_tiket.setOnClickListener {
            val intent = Intent(this@CheckoutActivity,
                CheckoutSuccessActivity::class.java)
            startActivity(intent)

            showNotif()
        }

        btn_cancle.setOnClickListener {
            finish()
        }

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = CheckoutAdapter(dataList) {
        }

        if (preferences.getValues("saldo")!!.isNotEmpty()) {
            val localId = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localId)
            tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))
            btn_tiket.visibility = View.VISIBLE
            tvTotal.visibility = View.INVISIBLE

        }else{
            tv_saldo.setText("Rp 0")
            btn_tiket.visibility = View.INVISIBLE
            tvTotal.visibility = View.VISIBLE
            tvTotal.text = "Saldo pada E-Wallet anda tidal mencukupi \n" +
                    "untuk melakukan transaksi"
        }
    }

    private fun showNotif(){
        val NOTIFICATION_CHANEL_ID = "chanel_bwa_notif"
    }
}
