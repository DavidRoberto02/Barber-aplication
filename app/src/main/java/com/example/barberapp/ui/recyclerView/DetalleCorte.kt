package com.example.barberapp.ui.recyclerView


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.barberapp.databinding.ActivityDetalleCorteBinding
import java.lang.Exception

class DetalleCorte : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleCorteBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleCorteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNombreCorteInfo.text = intent.getStringExtra("nombre")
        val extraImage = intent.getStringExtra("url")
        try {
            Glide.with(binding.imgPhotoDetalle.context)
                .load(extraImage)
                .into(binding.imgPhotoDetalle)
        }catch (e: Exception){

        }
        binding.tvDescripcionCorteInfo.text = intent.getStringExtra("descripcion")

    }

}




