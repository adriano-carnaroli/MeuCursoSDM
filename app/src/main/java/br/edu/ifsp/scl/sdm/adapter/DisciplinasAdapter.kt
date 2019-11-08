package br.edu.ifsp.scl.sdm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.sdm.R
import br.edu.ifsp.scl.sdm.model.Disciplina
import br.edu.ifsp.scl.sdm.view.MainActivity

class DisciplinasAdapter(val activity: MainActivity, // Contexto
                         val leiauteItem: Int, // Leiaute a ser inflado
                         val listaDisciplinas: MutableList<Disciplina>): // Dados
    ArrayAdapter<Disciplina>(activity, leiauteItem, listaDisciplinas) {
    // Referência para o inflador de novas view
    val layoutInflater: LayoutInflater
    init {
        // Usando MainActivity como filha de Context
        layoutInflater =
            activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                    as LayoutInflater
    }
    /* Chamado automaticamente sempre que uma nova view for necessária, ou seja,
    quando uma View sai da tela e outra aparece, rolagem do ListView */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup):
            View {
        // Recuperando disciplina da lista segundo a posição
        val disciplina = listaDisciplinas.get(position)
        var view = convertView // Porque convertView é value
        // Se a view ainda não existe
        if (view == null) {
            // Inflando nova view
            view = layoutInflater.inflate(leiauteItem, parent, false)
            /* Guardando na Tag da View um referência para um ItemHolder que
            guarda referências para os seus próprios compentes visuais */
            view.setTag(ItemHolder(
                view.findViewById(R.id.codigoDisciplinaTv),
                view.findViewById(R.id.nomeDisciplinaTv)
            ))
        }
        // Pegando o ItemHolder na Tag de uma View "reciclada" ou nova
        val itemHolder = view?.getTag() as ItemHolder
        /* Usando as referências do ItemHolder para setar os valores
        dos compenentes visuais da view */
        itemHolder.codigoDisciplinaTv.text = disciplina.codigo
        itemHolder.nomeDisciplinaTv.text = disciplina.nome
        // Retornando view devidamente preenchida
        return view
    }
    /* Boilerplate
    Código que quase sempre é necessário e mesmo assim criamos.
    Dica: RecyclerView ao invés de ListView para listas mais complexas */
    private data class ItemHolder(
        val codigoDisciplinaTv: TextView,
        val nomeDisciplinaTv: TextView
    )
}