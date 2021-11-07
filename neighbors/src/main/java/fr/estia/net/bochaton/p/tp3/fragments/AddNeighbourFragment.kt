package fr.estia.net.bochaton.p.tp3.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import fr.estia.net.bochaton.p.tp3.NavigationListener
import fr.estia.net.bochaton.p.tp3.R
import fr.estia.net.bochaton.p.tp3.data.NeighborRepository
import fr.estia.net.bochaton.p.tp3.data.service.DummyNeighborApiService
import fr.estia.net.bochaton.p.tp3.databinding.AddNeighborBinding
import fr.estia.net.bochaton.p.tp3.models.Neighbor

class AddNeighbourFragment : Fragment(), View.OnClickListener, TextWatcher {
    private var _binding: AddNeighborBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = AddNeighborBinding.inflate(inflater, container, false)
        val view = binding.root
        with(binding) {
            floatingActionButton.isEnabled = false
            fieldSTxtImage.addTextChangedListener(this@AddNeighbourFragment)
            fieldSTxtAdr.addTextChangedListener(this@AddNeighbourFragment)
            fieldSTxtApm.addTextChangedListener(this@AddNeighbourFragment)
            fieldSTxtNom.addTextChangedListener(this@AddNeighbourFragment)
            fieldSTxtTel.addTextChangedListener(this@AddNeighbourFragment)
            fieldSTxtWeb.addTextChangedListener(this@AddNeighbourFragment)
            var conditionUrlImage = false
            var conditionUrlWeb = false
            var conditionTel = false
            var conditionMail = false
            floatingActionButton.setOnClickListener {
                val adr = fieldSTxtAdr.text.toString()
                val image = fieldSTxtImage.text.toString()
                val nom = fieldSTxtNom.text.toString()
                val tel = fieldSTxtTel.text.toString()
                val web = fieldSTxtWeb.text.toString()
                val apm = fieldSTxtApm.text.toString()
                val id = NeighborRepository.getInstance().getNeighbours().last().id + 1
                if (URLUtil.isValidUrl(image)) {
                    conditionUrlImage = true
                } else {
                    fieldSTxtImage.error = "URL invalid"
                }
                if (URLUtil.isValidUrl(web)) {
                    conditionUrlWeb = true
                } else {
                    fieldSTxtWeb.error = "URL invalid"
                }
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(adr).matches()) {
                    conditionMail = true
                } else {
                    fieldSTxtAdr.error = "adresse email invalide"
                }
                if (tel.isDigitsOnly() &&
                    tel.length == 10 &&
                    (tel.substring(0, 2) == "06") || (tel.substring(0, 2) == "07")
                ) {
                    conditionTel = true
                } else {
                    fieldSTxtTel.error = "format expected 0X XX XX XX XX XX"
                }
                if (conditionMail && conditionTel && conditionUrlImage && conditionUrlWeb) {
                    val neighbor = Neighbor(id, nom, image, adr, tel, apm, false, web)
                    val dummyNeighborApiService = DummyNeighborApiService()
                    dummyNeighborApiService.createNeighbour(neighbor)
                    NeighborRepository.getInstance().createNeighbour(neighbor)
                    (activity as? NavigationListener)?.let {
                        it.showFragment(ListNeighborsFragment())
                        it.updateTitle(R.string.ListNeighborsFragment_name)
                    }
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun afterTextChanged(s: Editable?) {
        binding.floatingActionButton.isEnabled =
            binding.fieldSTxtAdr.text!!.isNotBlank() &&
            binding.fieldSTxtApm.text!!.isNotBlank() &&
            binding.fieldSTxtImage.text!!.isNotBlank() &&
            binding.fieldSTxtNom.text!!.isNotBlank() &&
            binding.fieldSTxtTel.text!!.isNotBlank() &&
            binding.fieldSTxtWeb.text!!.isNotBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun onClick(v: View?) {}
}
