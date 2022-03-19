package com.example.barberapp.ui.agregar

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.barberapp.R
import com.example.barberapp.databinding.FragmentAgregarBinding
import com.example.barberapp.ui.recyclerView.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class AgregarFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    lateinit var ImageUri : Uri
    private val users = mutableListOf<User>()
    private lateinit var agregarViewModel: AgregarViewModel
    private var _binding: FragmentAgregarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agregarViewModel =
            ViewModelProvider(this).get(AgregarViewModel::class.java)

        _binding = FragmentAgregarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imageViewCorte.setOnClickListener { requestPermission() }

        binding.btnSubirImagen.setOnClickListener { uploadImage() }

        binding.btnSubirCorte.setOnClickListener {
            val nombre = binding.etNombreCorte.text.toString()
            val descripcion = binding.etDescripcionCorte.text.toString()
            val imagen = binding.imageViewCorte.setImageURI(ImageUri).toString()
            val sexo = binding.categoriaCorte.selectedItem.toString()

            saveFireStore(descripcion, imagen, sexo, nombre)
            Snackbar.make(binding.root, R.string.message_action_submit, Snackbar.LENGTH_LONG)
                .show()
        }

        return root
    }


    private fun saveFireStore(descripcion: String, imagen: String, sexo: String, nombre: String) {
        //CREAR LA COLECCION DE DATOS EN CLOUD FIRESTORE.
        db.collection("cortes")
            .document(nombre)
            .set(
                hashMapOf(
                    "Descripcion" to descripcion,
                    "Sexo" to sexo,
                    "Imagen" to imagen
                )
            )
    }

    private fun uploadImage() {

        val progressDialog = ProgressDialog(this.context)
        progressDialog.setMessage("Subiendo imagen...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(ImageUri)
            .addOnSuccessListener {

                binding.imageViewCorte.setImageURI(null)
                Toast.makeText(this.context, "Subida con exito", Toast.LENGTH_SHORT).show()
                if (progressDialog.isShowing) progressDialog.dismiss()


            }.addOnFailureListener {
                if (progressDialog.isShowing) progressDialog.dismiss()
                Toast.makeText(this.context, "Ha fallado", Toast.LENGTH_SHORT).show()

            }
    }


    private fun requestPermission() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK ){

            ImageUri = data?.data!!
            binding.imageViewCorte.setImageURI(ImageUri)


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

