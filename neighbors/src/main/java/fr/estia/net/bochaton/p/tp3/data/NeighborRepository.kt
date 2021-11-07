package fr.estia.net.bochaton.p.tp3.data

import fr.estia.net.bochaton.p.tp3.data.service.DummyNeighborApiService
import fr.estia.net.bochaton.p.tp3.data.service.NeighborApiService
import fr.estia.net.bochaton.p.tp3.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours
    fun deleteNeighbour(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)
    fun createNeighbour(neighbor: Neighbor) = apiService.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
