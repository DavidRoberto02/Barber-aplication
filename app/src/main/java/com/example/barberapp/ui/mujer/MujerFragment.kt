package com.example.barberapp.ui.mujer

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barberapp.databinding.FragmentAgregarBinding
import com.example.barberapp.databinding.FragmentMujerBinding
import com.example.barberapp.ui.hombre.HomeViewModel
import com.example.barberapp.ui.recyclerView.DetalleCorte
import com.example.barberapp.ui.recyclerView.OnClickListener
import com.example.barberapp.ui.recyclerView.User
import com.example.barberapp.ui.recyclerView.UserAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.*
import com.google.protobuf.Value
import java.io.ByteArrayOutputStream

class MujerFragment : Fragment(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var db: FirebaseFirestore
    private lateinit var databaseReference: DatabaseReference
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bindingAdd: FragmentAgregarBinding
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

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this.context)

        binding.recyclerViewMujer.apply {
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


        val pixie = User(
            "Pixie",
            "https://cafeversatil.com/wp-content/uploads/2018/12/35_guetzli.jpg",
            "Se tiene la falsa idea que el pelo sexy es sinónimo de pelo largo. Este es un concepto erróneo, ya que los cortes cortos también pueden verse muy sexys",
            "Mujer"
        )
        val Bobcorto = User(
            "Bob corto",
            "https://cafeversatil.com/wp-content/uploads/2017/11/00_guetzli-1.jpg",
            "Esta opción es igual de cómoda y versátil que la anterior, y tiene la ventaja que al tener unos 10 o 12 cm de largo, te da más libertad a la hora de peinarlo, puedes alisarlo u ondularlo con más comodidad y facilidad.",
            "Mujer"
        )
        val melenaMidi = User(
            "Melena Midi",
            "https://cafeversatil.com/wp-content/uploads/2018/12/15_guetzli.jpg",
            "El cabello no debe estar demasiado escalonado como en temporadas anteriores. Se pueden agregar unas pocas capas para dar volumen y movimiento.",
            "Mujer"
        )
        val Boblargo = User(
            "Bob largo",
            "https://cafeversatil.com/wp-content/uploads/2018/12/06_guetzli-768x960.jpg",
            "Se trata de un corte muy versátil, que nos sentará bien tanto con una cabellera muy lacia como con una suavemente ondulada y en algunos casos también en su versión más rizada.",
            "Mujer"
        )
        val Carre = User(
            "Carré",
            "https://depeinados.com/wp-content/uploads/2018/11/CorteBob62-600x900.jpg",
            "Este corte es similar al Bob, pero la diferencia radica en que el largo de la parte de atrás es casi igual a la de adelante.",
            "Mujer"
        )
        val Flequillo = User(
            "Flequillo",
            "https://cafeversatil.com/wp-content/uploads/2018/12/01_guetzli-1.jpg",
            "Laterales, rectos o cortina, todos los tipos de flequillo están de moda. Ahora ya no es necesario perder el tiempo y los nervios para arreglar el flequillo antes de salir, cuanto más desordenado, mejor.",
            "Mujer"
        )
        val wolfCut = User(
            "Wolf Cut",
            "https://www.sdpnoticias.com/resizer/E1iyZ_IuxQ8YGIUfzyyD3GDBV1k=/1200x0/filters:format(jpg):quality(90)/cloudfront-us-east-1.images.arcpublishing.com/sdpnoticias/LHOVEKODYND7XG3UDHBLPWEXVA.jpg",
            "El ‘wolf cut’ es un corte de cabello similar al ‘mullet’ y ‘shag’, mismos que surgieron en la década de los 80 y que, además, fueron tendencia en 2021.",
            "Mujer"
        )
        val Caoba = User(
            "Caoba",
            "https://media.glamour.mx/photos/61904cc8dc5382f9acfb3423/master/w_1600,c_limit/268100.jpg",
            "Es un tinte rojo oscuro que tiende al café de la madera y que se ve perfecto tanto en pieles cálidas como frías. Su acabado es brillante, así que entre más largo, más resaltará su coloración.",
            "Mujer"
        )
        val RosaFresa = User(
            "Rosa Fresa",
            "https://media.glamour.mx/photos/61904cc72d97bd4c522a1c88/master/w_1600,c_limit/258444.jpeg",
            "El rosa pastel ha regresado para quedarse y puede llevarse claro o un poco más encendido, en una tonalidad fresa. Para llegar al tinte, es necesario decolorar el cabello, pero vale la pena por completo, sobre todo si eres de piel fría.",
            "Mujer"
        )
        val RubioTigre = User(
            "Rubio trigo",
            "https://media.glamour.mx/photos/61904cc92d97bd4c522a1c90/master/w_1600,c_limit/262281.jpg",
            "El tono se asemeja a un dorado con luces y sombras cafés que favorecen muchísimo si eres de piel cálida.",
            "Mujer"
        )
        val Avellana = User(
            "Avellana",
            "https://media.glamour.mx/photos/61904cc82d97bd4c522a1c8f/master/w_1600,c_limit/221802.jpg",
            "Este color de cabello café con acentos rojizos y cálidos hace que tu piel luzca más clara y tu melena más sexy y saludable.",
            "Mujer"
        )

        users.add(pixie)
        users.add(Bobcorto)
        users.add(melenaMidi)
        users.add(Boblargo)
        users.add(Carre)
        users.add(Flequillo)
        users.add(wolfCut)
        users.add(Caoba)
        users.add(RosaFresa)
        users.add(RubioTigre)
        users.add(Avellana)


        return users


    }

    override fun onClick(user: User) {
        //Toast.makeText(context, "${user.nombre}", Toast.LENGTH_SHORT).show()


        val intent = Intent (this.context, DetalleCorte::class.java)
        intent.putExtra("nombre", user.nombre)
        intent.putExtra("url", user.url)
        intent.putExtra("descripcion", user.descripcion)
        startActivity(intent)

    }
}