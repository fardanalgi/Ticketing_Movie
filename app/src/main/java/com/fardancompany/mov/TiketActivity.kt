package com.fardancompany.mov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fardancompany.mov.checkout.model.Checkout
import com.fardancompany.mov.home.model.Film
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        val data = intent.getParcelableExtra<Film>("data")

        tv_judul.text = data.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(img_poster)

        rv_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1",""))
        dataList.add(Checkout("C2",""))

        rv_checkout.adapter = TiketAdapter(dataList){

        }

    }
}
