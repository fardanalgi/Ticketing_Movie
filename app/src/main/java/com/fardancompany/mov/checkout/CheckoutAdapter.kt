package com.fardancompany.mov.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fardancompany.mov.R
import com.fardancompany.mov.checkout.model.Checkout
import kotlinx.android.synthetic.main.row_item_checkout.view.*
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter (private var data: List<Checkout>,
                       private var listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.LeagueAdapter>(){

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapter {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_checkout,parent,false)

        return LeagueAdapter(inflatedView)

    }

    override fun onBindViewHolder(holder: LeagueAdapter, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter,position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueAdapter(view: View) : RecyclerView.ViewHolder(view){

        private val tvKursi: TextView = view.tv_kursi
        private val tvHarga: TextView = view.tv_harga

        fun bindItem(data : Checkout, listener: (Checkout) -> Unit, context: Context, position: Int){

            val localeID = Locale("in","ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")){
                tvKursi.text = data.kursi
                tvKursi.setCompoundDrawables(null,null,null,null)
            }else{
                tvKursi.text = "Seat No. "+data.kursi
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }



    }
}