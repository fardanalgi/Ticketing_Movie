package com.fardancompany.mov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fardancompany.mov.R
import com.fardancompany.mov.checkout.model.Checkout
import com.fardancompany.mov.home.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {

    var statusC3 : Boolean = false
    var statusC4 : Boolean = false
    var total : Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        //menerima data parcelable dari detail activity mengunakan key "data"
        val data = intent.getParcelableExtra<Film>("data")

        tv_judul.text = data.judul

        c3.setOnClickListener {
            if (statusC3){
                c3.setImageResource(R.drawable.button_line_biru_muda)
                statusC3 = false
                total -=1
                beliTiket(total)
            }else{
                c3.setImageResource(R.drawable.ic_rectangle_selected)
                statusC3 = true
                total +=1
                beliTiket(total)

                val data = Checkout("C3","70000")
                dataList.add(data)
            }
        }

        c4.setOnClickListener {
            if (statusC4){
                c4.setImageResource(R.drawable.button_line_biru_muda)
                statusC4 = false
                total -=1
                beliTiket(total)
            }else{
                c4.setImageResource(R.drawable.ic_rectangle_selected)
                statusC4 = true
                total +=1
                beliTiket(total)

                val data = Checkout ("C4","70000")
                dataList.add(data)
            }
        }

        btn_beli_tiket.setOnClickListener {
            val intent = Intent(
                this,
                CheckoutActivity::class.java
            ).putExtra("data",dataList).putExtra("datas", data)
            startActivity(intent)
        }


    }

    private fun beliTiket(total:Int){
        if (total == 0){
            btn_beli_tiket.setText("Beli Tiket")
            btn_beli_tiket.visibility = View.INVISIBLE
        }else{
            btn_beli_tiket.setText("Beli Tiket ("+total+")")
            btn_beli_tiket.visibility = View.VISIBLE
        }
    }
}
