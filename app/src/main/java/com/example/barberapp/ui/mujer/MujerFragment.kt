package com.example.barberapp.ui.mujer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.barberapp.databinding.FragmentMujerBinding

class MujerFragment : Fragment() {

    private lateinit var mujerViewModel: MujerViewModel
    private var _binding: FragmentMujerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mujerViewModel =
            ViewModelProvider(this).get(MujerViewModel::class.java)

        _binding = FragmentMujerBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}