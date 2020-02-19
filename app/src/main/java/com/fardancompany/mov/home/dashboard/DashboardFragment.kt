package com.fardancompany.mov.home.dashboard


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fardancompany.mov.DetailActivity

import com.fardancompany.mov.R
import com.fardancompany.mov.home.model.Film
import com.fardancompany.mov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var datalist = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_nama.text = preferences.getValues("nama")
        if (!preferences.getValues("saldo").equals("")){
            currecy(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        rv_now_playing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_coming_soon.layoutManager = LinearLayoutManager(context!!.applicationContext)
        getData()
    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                datalist.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()){

                    val film = getdataSnapshot.getValue(Film::class.java!!)
                    datalist.add(film!!)
                }

                rv_now_playing.adapter = NowPlayingAdapter(datalist) {
                    //mengirim data megunakan key "data" ke detail activity
                    val intent = Intent(context,
                        DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                rv_coming_soon.adapter = ComingSoonAdapter(datalist){
                    val intent = Intent(context,
                        DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun currecy(harga: Double, textView: TextView){
        val localeId = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeId)
        textView.text = formatRupiah.format(harga)
    }


}
