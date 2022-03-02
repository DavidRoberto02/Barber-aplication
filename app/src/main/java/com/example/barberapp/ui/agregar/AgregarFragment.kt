package com.example.barberapp.ui.agregar

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.barberapp.R
import com.example.barberapp.databinding.FragmentAgregarBinding
import com.google.android.material.snackbar.Snackbar


class AgregarFragment : Fragment() {

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

        binding.btnSubirCorte.setOnClickListener {
            Snackbar.make(binding.root, R.string.message_action_submit, Snackbar.LENGTH_LONG)
                .show()
        }

        return root
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    obtenerFotoGaleria()
                }
                else -> requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            obtenerFotoGaleria()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            obtenerFotoGaleria()
        } else {
            Snackbar.make(binding.root, R.string.message_action_loadimage, Snackbar.LENGTH_LONG)
                .setAction(R.string.access_deneged) {
                    Toast.makeText(context, R.string.access, Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data
            binding.imageViewCorte.setImageURI(data)
        }
    }

    private fun obtenerFotoGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}