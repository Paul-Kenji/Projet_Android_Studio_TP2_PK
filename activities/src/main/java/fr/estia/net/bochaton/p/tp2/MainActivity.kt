package fr.estia.net.bochaton.p.tp2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.estia.net.bochaton.p.tp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Qu'avez-vous observez ? -> Pas d'erreur de compilation mais à l'execution( en comentant la ligne d'initialisation de "clickButton").
        // Pourquoi l'application a planté ? -> Car on utilise clickButton sans l'avoir initialisé.
        // Avez-vous compris le danger des lateInit ? -> On peut compiler mais ça fera planter l'appli
        binding.btnClickMe.setOnClickListener {
            nbClick++
            binding.txtClickMe.text = getString(R.string.click_Me, nbClick)
            binding.btnClickMe.isEnabled = nbClick <= 4
        }
        binding.btnCompute.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }
}
