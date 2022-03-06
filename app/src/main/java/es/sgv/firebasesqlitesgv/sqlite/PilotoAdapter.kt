package es.sgv.firebasesqlitesgv.sqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.sgv.firebasesqlitesgv.sqlite.Piloto
import es.sgv.firebasesqlitesgv.R
import es.sgv.firebasesqlitesgv.databinding.ItemPilotoBinding

//el segundo parametro (el del onclicklistener) es una funcion lambda
class PilotoAdapter(private val pilotos:List<Piloto>, private val onClickListener:(Piloto) -> Unit) : RecyclerView.Adapter<PilotoAdapter.PilotoViewHolder>(){ //  : -> extends

    //aqui le pasamos el item, el layout que va a poder modificar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PilotoViewHolder(layoutInflater.inflate(R.layout.item_piloto, parent, false))

    }

    //este metodo pasa por cada uno de los items y llama al Piloto render pasandole ese item
    override fun onBindViewHolder(holder: PilotoViewHolder, position: Int) {
        var item = pilotos[position]

        holder.render(item, onClickListener) //aqui tambien le devolvemos la lambda del onclick
    }

    override fun getItemCount(): Int { //devuelve el tamaño del listado que tenemos
        return pilotos.size
    }

    //

    class PilotoViewHolder(view: View): RecyclerView.ViewHolder(view) {

        //le pasamos view, que es la vista que recibía
        val binding = ItemPilotoBinding.bind(view) //el binding se nos crea automaticamente y lo renombrar con el nombre del xml+binding, en este caso item_piloto.xml -> ItemPilotoBinding

        //gracias al binding podemos cargarnos todo esto
        /*
        val Pimagen = view.findViewById<ImageView>(R.id.IV_pilotoImg)
        val Pnombre = view.findViewById<TextView>(R.id.TV_nombrePiloto)
        val Pdorsal = view.findViewById<TextView>(R.id.TV_dorsal)
        val Pescuderia = view.findViewById<TextView>(R.id.TV_escuderia)
        */

        fun render(piloto: Piloto, onClickListener:(Piloto) -> Unit){

            //basicamente con el binding accedemos directamente a la id de cada componente (la id que le hemos puesto en el xml de item_piloto
            //ahora en lugar de Pnombre/Pdorsal/Pescuderia/Pimagen .text, con el binding ponemos binding.TVNombrePiloto.text
            binding.TVNombrePiloto.text = piloto.nombre
            binding.TVDorsal.text = piloto.dorsal.toString()
            binding.TVEscuderia.text = piloto.escuderia
            Glide.with(binding.IVPilotoImg.context).load(piloto.imagen).into(binding.IVPilotoImg)
            //ya está implementado el viewBinding en nuestro recyclerview
            //al no estar en una activity no tenemos contexto  ni podemos poner el this, pero podemos sacarlo de cualquier otro sitio (en mi caso lo saco de la imagen con el Pimagen.context)

            itemView.setOnClickListener(){ //de esta forma cada vez que se haga click vamos a llamar a la funcion lambda
                onClickListener(piloto) //a la lambda le pasamos el objeto piloto con el que estemos //el que le hayamos hecho click
            }


        }

        private fun onClickDelItem(piloto: Piloto) {
            itemView.setOnClickListener()
            {
                Toast.makeText(binding.IVPilotoImg.context,
                    piloto.toString(),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        private fun onClickDeLaFoto(piloto: Piloto) {
            //voy a meterle un onclick listener a la foto para que se le pueda hacer click
            //poner el componente al que queremos acceder y darle el atributo de clickable
            binding.IVPilotoImg.setOnClickListener() //cada listener va en relacion a cada item sobre el que se hace el click
            {
                Toast.makeText(binding.IVPilotoImg.context,
                    "El nombre es " + piloto.nombre,
                    Toast.LENGTH_SHORT)
                    .show() //context, mensaje, duracion
            }
        }


    }
}