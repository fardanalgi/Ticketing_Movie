package com.fardancompany.mov.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat

import androidx.recyclerview.widget.LinearLayoutManager
import com.fardancompany.mov.R
import com.fardancompany.mov.TiketActivity
import com.fardancompany.mov.checkout.adapter.CheckoutAdapter
import com.fardancompany.mov.checkout.model.Checkout
import com.fardancompany.mov.home.model.Film
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
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        btn_tiket.setOnClickListener {
            val intent = Intent(this@CheckoutActivity,
                CheckoutSuccessActivity::class.java)
            startActivity(intent)

            showNotif(data)
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

    private fun showNotif(datas:Film){
        val NOTIFICATION_CHANEL_ID = "chanel_bwa_notif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channelName = "BWAMOV Notif Chanel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChanel = NotificationChannel(NOTIFICATION_CHANEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChanel)
        }

//        val intent = Intent(this, CheckoutSuccessActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString("id", "id_film")
//        intent.putExtras(bundle)

        val intent = Intent(this,TiketActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data",datas)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,R.mipmap.ic_launcher
                )
            )
            .setTicker("notif bwa starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 300,300)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Pembayaran Telah di Terima")
            .setContentText("bwamov")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115,builder.build())
    }
}
