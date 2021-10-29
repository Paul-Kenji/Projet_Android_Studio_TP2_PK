package fr.estia.net.bochaton.p.tp2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import fr.estia.net.bochaton.p.tp2.databinding.ComputeActivityBinding

class ComputeActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ComputeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ComputeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnCalculer.isEnabled = false
            field1.addTextChangedListener(this@ComputeActivity)
            field2.addTextChangedListener(this@ComputeActivity)
            btnCalculer.setOnClickListener {
                val a = field1.text.toString()
                val b = field2.text.toString()
                if (a.isDigitsOnly() && b.isDigitsOnly()) {
                    resultat.text = (a.toInt() + b.toInt()).toString()
                } else {
                    resultat.text = getString(R.string.error_type)
                }
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        binding.btnCalculer.isEnabled = binding.field1.text.isNotBlank() && binding.field2.text.isNotBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
