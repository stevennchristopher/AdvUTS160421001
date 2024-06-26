package com.example.advuts160421001.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advuts160421001.databinding.BeritaListItemBinding
import com.example.advuts160421001.model.Berita
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback

class BeritaListAdapter(val beritalist:ArrayList<Berita>)
    : RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>()
{
    class BeritaViewHolder(var binding: BeritaListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = BeritaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(beritalist[position].urlFoto)
            .into(holder.binding.imgBerita, object:Callback {
                override fun onSuccess() {
                    holder.binding.progressImage.visibility = View.INVISIBLE
                    holder.binding.imgBerita.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })

        holder.binding.txtTitleBerita.text = beritalist[position].judul
        holder.binding.txtUsernameBerita.text = "@" + beritalist[position].username_pembuat
        holder.binding.txtDeskripsiBerita.text = beritalist[position].deskripsi

        holder.binding.btnRead.setOnClickListener{
            val action = HomeFragmentDirections.actionBeritaDetails(beritalist[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return beritalist.size
    }

    fun updateBeritaList(newFoodList: ArrayList<Berita>) {
        beritalist.clear()
        beritalist.addAll(newFoodList)
        notifyDataSetChanged()
    }
}