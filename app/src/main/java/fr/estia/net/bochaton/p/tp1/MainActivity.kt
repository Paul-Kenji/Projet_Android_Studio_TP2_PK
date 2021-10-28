package fr.estia.net.bochaton.p.tp1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imageView: ImageView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.my_button)
        button.setOnClickListener(this)
        loadImage("https://cdn.futura-sciences.com/buildsv6/images/wide1920/6/5/2/652a7adb1b_98148_01-intro-773.jpg")
    }

    override fun onClick(view: View?) {
        val links = listOf(
            "http://www.meetingsnmore.com/iages2019.com/img/tour/nandankanan.jpg",
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
            "https://www.gettyimages.fr/gi-resources/images/500px/983794168.jpg"
        )
        loadImage(links.random())
        Toast.makeText(this, "You click me", Toast.LENGTH_LONG).show()
    }

    private fun loadImage(url: String) {
        Picasso.get()
            .load(url)
            .resize(500, 500)
            .centerCrop()
            .into(imageView)
    }
}
