package com.fardancompany.mov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fardancompany.mov.R
import com.fardancompany.mov.home.dashboard.DashboardFragment
import com.fardancompany.mov.utils.Preferences
import com.fardancompany.mov.wallet.adapter.WalletAdapter
import com.fardancompany.mov.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()
    private var total : Int = 0

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        dataList.add(Wallet(
            "Avengers Infinity-war",
            "Sabtu 12 jan, 2019",
            140000.0,
            "0"
        ))

        dataList.add(Wallet(
            "Top Up",
            "Sabtu 15 jan, 2019",
            140000.0,
            "1"
        ))

        dataList.add(Wallet(
            "Anabele",
            "Sabtu 15 jan, 2019",
            140000.0,
            "0"
        ))

        dataList.add(Wallet(
            "Avengers End-Game",
            "Sabtu 16 jan, 2019",
            140000.0,
            "0"
        ))

        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){
//            val intent = Intent (this@MyWalletActivity, DashboardFragment::class.java)
//            startActivity(intent)
        }

        btn_top_up.setOnClickListener {
            val intent = Intent(this, MyWaletTopUpActivity::class.java)
            startActivity(intent)
        }
    }
}
