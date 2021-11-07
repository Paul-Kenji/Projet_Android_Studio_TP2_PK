package fr.estia.net.bochaton.p.tp3.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.estia.net.bochaton.p.tp3.NavigationListener
import fr.estia.net.bochaton.p.tp3.R
import fr.estia.net.bochaton.p.tp3.adapters.ListNeighborHandler
import fr.estia.net.bochaton.p.tp3.adapters.ListNeighborsAdapter
import fr.estia.net.bochaton.p.tp3.data.NeighborRepository
import fr.estia.net.bochaton.p.tp3.data.service.DummyNeighborApiService
import fr.estia.net.bochaton.p.tp3.models.Neighbor

class ListNeighborsFragment : Fragment(), View.OnClickListener, ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: FloatingActionButton

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        button = view.findViewById(R.id.add_button)
        button.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
                it.updateTitle(R.string.AddNeighborFragment_name)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh()
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
            builder.apply {
                setPositiveButton(R.string.oui) { dialog, _ ->
                    val dummyNeighborApiService = DummyNeighborApiService()
                    dummyNeighborApiService.deleteNeighbour(neighbor)
                    dialog.dismiss()
                    NeighborRepository.getInstance().deleteNeighbour(neighbor)
                    refresh()
                }
                setNegativeButton(R.string.non) { dialog, _ ->
                    dialog.dismiss()
                }
            }
            builder.create().show()
        }
    }

    private fun refresh() {
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View?) {}
}
