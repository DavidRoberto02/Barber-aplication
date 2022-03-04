package com.example.barberapp.ui.hombre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barberapp.MainActivity
import com.example.barberapp.databinding.FragmentHombreBinding
import com.example.barberapp.ui.recyclerView.OnClickListener
import com.example.barberapp.ui.recyclerView.User
import com.example.barberapp.ui.recyclerView.UserAdapter

class HombreFragment : Fragment(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHombreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHombreBinding.inflate(inflater, container, false)
        val root: View = binding.root


        userAdapter = UserAdapter (getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this.context)

        binding.recyclerViewHombre.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()

        val alain = User(
            1,
            "Low Fade",
            "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg",
            "Corte chido",
            "Hombre"
        )
        val samanta = User(
            2,
            "marulin chirulin",
            "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg",
            "jejejeje",
            "Hombre"
        )
        val javier = User(
            3,
            "Typer Fase",
            "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg",
            "TA 2-2",
            "Hombre"
        )
        val emma = User(
            4,
            "Hipe fade",
            "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg",
            "Corte no chido",
            "Hombre"
        )
        val hola = User(
            5,
            "Low Fade",
            "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg",
            "Corte chido",
            "Hombre"
        )
        val hola1 = User(
            6,
            "marulin chirulin",
            "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg",
            "jejejeje",
            "Hombre"
        )
        val hola2 = User(
            7,
            "Typer Fase",
            "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg",
            "TA 2-2",
            "Hombre"
        )
        val hola3 = User(
            8,
            "Hipe fade",
            "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg",
            "Corte no chido",
            "Hombre"
        )

        val hola4 = User(
            8,
            "Hipe fade",
            "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg",
            "Corte no chido",
            "Hombre"
        )


        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(hola)
        users.add(hola1)
        users.add(hola2)
        users.add(hola3)
        users.add(hola4)




        return users
    }

    override fun onClick(user: User) {
        Toast.makeText(context, "${user.nombre}", Toast.LENGTH_SHORT).show()
    }
}