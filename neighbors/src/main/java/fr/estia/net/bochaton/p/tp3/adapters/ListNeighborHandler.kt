package fr.estia.net.bochaton.p.tp3.adapters

import fr.estia.net.bochaton.p.tp3.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}
