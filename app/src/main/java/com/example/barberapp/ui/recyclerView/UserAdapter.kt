package com.example.barberapp.ui.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.barberapp.R
import com.example.barberapp.databinding.ItemCorteBinding

class UserAdapter(
    private val users: List<User> = listOf(),
    private val listener: OnClickListener
    ) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_corte, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)


        with(holder) {
            setListener(user)
            binding.tvNombreCorte.text = user.nombre
            binding.tvDescripcionCorte.text = user.descripcion
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }

    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemCorteBinding.bind(view)
        fun setListener(user: User) {
            binding.root.setOnClickListener { listener.onClick(user) }
        }

    }
}