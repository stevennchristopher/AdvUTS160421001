package com.example.advuts160421001.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advuts160421001.model.Paragraf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ParagrafListViewModel(application: Application): AndroidViewModel(application){
    val paragrafLiveData = MutableLiveData<ArrayList<Paragraf>>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(beritaId: String) {
        val urlOrigin = "http://10.0.2.2/anmp/uts/getParagrafbyIdBerita.php?beritaId=$beritaId"

        queue = Volley.newRequestQueue( getApplication() )
        val stringRequest = StringRequest(
            Request.Method.GET, urlOrigin,
            {
                val sType = object : TypeToken<List<Paragraf>>() { }.type
                val result = Gson().fromJson<List<Paragraf>>(it, sType)
                paragrafLiveData.value = result as ArrayList<Paragraf>?
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoleydetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}