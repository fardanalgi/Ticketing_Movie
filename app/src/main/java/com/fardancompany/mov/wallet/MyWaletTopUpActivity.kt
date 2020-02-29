package com.fardancompany.mov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.fardancompany.mov.R
import kotlinx.android.synthetic.main.activity_my_walet_top_up.*

class MyWaletTopUpActivity : AppCompatActivity() {

    private var status : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_walet_top_up)

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        tv_10k.setOnClickListener {
            if (status){
                disselectMoney(tv_10k)
            }else{
                selectMoney(tv_10k)
            }
        }

    }

    private fun selectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.Pink))
        textView.setBackgroundResource(R.drawable.shape_line_red)
        status = true
        btn_top_up.visibility = View.VISIBLE
    }

    private fun disselectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.putih))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status = false
        btn_top_up.visibility = View.INVISIBLE
    }
}
