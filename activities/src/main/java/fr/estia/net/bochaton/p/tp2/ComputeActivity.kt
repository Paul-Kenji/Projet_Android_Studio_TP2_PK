package fr.estia.net.bochaton.p.tp2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class ComputeActivity : AppCompatActivity(), TextWatcher {
    private lateinit var f1: EditText
    private lateinit var f2: EditText
    private lateinit var res: TextView
    private lateinit var resultButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        f1 = findViewById(R.id.field_1)
        f2 = findViewById(R.id.field_2)
        resultButton = findViewById(R.id.btn_calculer)
        res = findViewById(R.id.resultat)
        resultButton.isEnabled = false
        f1.addTextChangedListener(this)
        f2.addTextChangedListener(this)
        resultButton.setOnClickListener {
            val a = f1.text.toString()
            val b = f2.text.toString()
            if (a.isDigitsOnly() && b.isDigitsOnly()) {
                val c = a.toInt()
                val d = b.toInt()
                val e = c + d
                res.text = e.toString()
            } else {
                res.text = getString(R.string.error_type)
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        resultButton.isEnabled = f1.text.isNotBlank() && f2.text.isNotBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
