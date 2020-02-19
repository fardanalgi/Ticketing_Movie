package com.fardancompany.mov

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fardancompany.mov.checkout.model.Checkout

class TiketAdapter(private var data: List<Checkout>,
                   private var listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<TiketAdapter.MyViewHolder>(){

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_checkout_white,parent, false)

        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener)
    }

    override fun getItemCount(): Int = data.size

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val tvKursi: TextView = view.findViewById(R.id.tv_kursi_detail_tiket)

        fun bindItem(data: Checkout, listener: (Checkout) -> Unit ){

            tvKursi.text = "Seat No."+data.kursi

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}