package com.example.barberapp.ui.hombre


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barberapp.databinding.FragmentAgregarBinding
import com.example.barberapp.databinding.FragmentHombreBinding
import com.example.barberapp.ui.recyclerView.DetalleCorte
import com.example.barberapp.ui.recyclerView.OnClickListener
import com.example.barberapp.ui.recyclerView.User
import com.example.barberapp.ui.recyclerView.UserAdapter
import com.google.firebase.firestore.*

class HombreFragment : Fragment(), OnClickListener {

    private var users = mutableListOf<User>()
    private lateinit var db: FirebaseFirestore
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHombreBinding? = null
    private lateinit var bindingAdd: FragmentAgregarBinding

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


        userAdapter = UserAdapter(getUsers(), this)

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


        val lowFade = User(
            "Low Fade",
            "https://www.todonline.co/contenido/uploads/2021/06/Curly-Hair-cortes-pelo-rizado-Drop-Fade.webp",
            "Mantiene los lados de abajo de tu cabello lo más cerca de tu piel y se va volviendo más largo conforme va yendo hacia arriba.",
            "Hombre"
        )
        val typerFade = User(
            "Typer Fade",
            "https://highnessdottv.files.wordpress.com/2016/03/taper-fade-10.jpg?w=750",
            "corte de pelo degradado, generalmente masculino, donde el cabello está muy recortado en la nuca, patillas y laterales de la cabeza, volviéndose más largo en la parte superior de la misma.",
            "Hombre"
        )
        val razorFade = User(
            "Razor Fade",
            "https://cms.modumb.com/assets/img/web/images/corte_razor_fade.jpg",
            "Es un tipo de corte en el que el degradado comienza en la nuca y va subiendo hasta alcanzar un largo más pronunciado",
            "Hombre"
        )
        val highFade = User(
            "High Fade",
            "https://cms.modumb.com/assets/img/web/images/corte_high_fade.jpg",
            "Implica un afeitado profundo a lo largo de los lados y en la parte posterior de la cabeza.",
            "Hombre"
        )
        val midFade = User(
            "Mid Fade",
            "https://modatoponline.com/wp-content/uploads/2018/10/23_guetzli-768x777.jpg",
            "El desvanecimiento medio es otro tipo de corte gradual donde la disminución comienza en la mitad del cuero cabelludo. Es uno de los preferidos, ya que logra un estilo apuesto.",
            "Hombre"
        )
        val undercut = User(
            "Undercut",
            "https://modatoponline.com/wp-content/uploads/2018/10/15_guetzli-768x768.jpg",
            "El peinado undercut o socavado es cualquier tipo de corte en el que el pelo en la parte más alta de la cabeza es largo y espeso y lleva a una parte inferior muy corta.",
            "Hombre"
        )
        val pompadour = User(
            "Pompadour",
            "https://modatoponline.com/wp-content/uploads/2018/10/70_guetzli-768x768.jpg",
            "Este corte de cabello deja una gran porción de cabello para girarlo hacia atrás. Este estilo resurgió en los años 80 y en la actualidad tiene muchos seguidores.",
            "Hombre"
        )
        val Mullet = User(
            "Mullet",
            "https://tendenzias.com//wp-content/uploads/2021/03/cortes-de-pelo-para-hombre-invierno-2021-corte-troye-sivan-istock.jpg",
            "El corte de estilo mullet ha arrasado entre los cortes de moda para la mujer en este 2022, pero también se van a llevar mucho para los hombres. ",
            "Hombre"
        )
        val buzzCut = User(
            "Buzz cut",
            "https://cafeversatil.com/wp-content/uploads/2019/05/23_guetzli.jpg",
            "Ideal para los hombres que quieren algo práctico, sin complicaciones, que requiere poco mantenimiento. Si tienes el pelo muy grueso o muy rizado, difícil de peinar, esta es una excelente opción. ",
            "Hombre"
        )
        val mohawk = User(
            "Mohawk",
            "https://cafeversatil.com/wp-content/uploads/2019/05/11_guetzli.jpg",
            "El pelo corto con la cresta que llega hasta la nuca, ligeramente más larga. Esto le da mucho volumen y movimiento así como un aspecto muy juvenil.",
            "Hombre"
        )



        users.add(lowFade)
        users.add(typerFade)
        users.add(razorFade)
        users.add(highFade)
        users.add(midFade)
        users.add(undercut)
        users.add(pompadour)
        users.add(Mullet)
        users.add(buzzCut)
        users.add(mohawk)

        return users

    }


    override fun onClick(user: User) {
        val intent = Intent(this.context, DetalleCorte::class.java)
        intent.putExtra("nombre", user.nombre)
        intent.putExtra("url", user.url)
        intent.putExtra("descripcion", user.descripcion)
        startActivity(intent)
    }
}