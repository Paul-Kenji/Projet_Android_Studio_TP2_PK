package fr.estia.net.bochaton.p.tp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var clickButton: Button
    private lateinit var computeButton: Button
    private var nbClick = 0
    private lateinit var textV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textV = findViewById(R.id.txt_click_me)
        clickButton = findViewById(R.id.btn_click_me)
        computeButton = findViewById(R.id.btn_compute)
        // Qu'avez-vous observez ? -> Pas d'erreur de compilation mais à l'execution( en comentant la ligne d'initialisation de "clickButton").
        // Pourquoi l'application a planté ? -> Car on utilise clickButton sans l'avoir initialisé.
        // Avez-vous compris le danger des lateInit ? -> On peut compiler mais ça fera planter l'appli
        clickButton.setOnClickListener {
            nbClick++
            if (nbClick <= 5) {
                textV.text = getString(R.string.click_Me, nbClick)
            } else {
                clickButton.isEnabled = false
            }
        }
        computeButton.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }
}
