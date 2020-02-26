package com.fardancompany.mov.wallet.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fardancompany.mov.R
import com.fardancompany.mov.wallet.model.Wallet
import java.text.NumberFormat
import java.util.*

class WalletAdapter (private var data: List<Wallet>,
                     private val listener: (Wallet) -> Unit)
    : RecyclerView.Adapter<WalletAdapter.MyViewHolder>(){

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_transaksi, parent, false)

        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItem(data[position],listener, contextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class MyViewHolder(view: View) :RecyclerView.ViewHolder(view){

        private val tvTitle: TextView = view.findViewById(R.id.tv_movie)
        private val tvDate: TextView = view.findViewById(R.id.tv_date)
        private val tvMoney: TextView = view.findViewById(R.id.tv_money)

        fun bindItem(data : Wallet, listener: (Wallet) -> Unit, context: Context, position: Int){

            tvTitle.setText(data.title)
            tvDate.setText(data.date)

            val localID = Locale("in","ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)

            if (data.status.equals("0")){
                tvMoney.setText("- "+formatRupiah.format(data.money!!.toDouble()))
            }else{
                tvMoney.setText("+ "+formatRupiah.format(data.money!!.toDouble()))
                tvMoney.setTextColor(Color.GREEN)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}