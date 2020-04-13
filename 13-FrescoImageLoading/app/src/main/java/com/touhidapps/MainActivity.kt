package com.touhidapps

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView

class MainActivity : AppCompatActivity() {

    var imageViewOne: SimpleDraweeView? = null
    var imageViewTwo: SimpleDraweeView? = null
    var imageViewThree: SimpleDraweeView? = null
    var imageViewFour: SimpleDraweeView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewOne = findViewById(R.id.imageViewOne)
        imageViewTwo = findViewById(R.id.imageViewTwo)
        imageViewThree = findViewById(R.id.imageViewThree)
        imageViewFour = findViewById(R.id.imageViewFour)

        // normal loading
        imageViewOne?.setImageURI(MyLinks.IMAGE_ONE)

        // with various options form xml
        imageViewTwo?.setImageURI(MyLinks.IMAGE_TWO)

        // Round image
        imageViewThree?.setImageURI(MyLinks.IMAGE_THREE)

        // GIF animated image automatically animated (Also possible manual start animation)
        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(MyLinks.IMAGE_FOUR))
                .setAutoPlayAnimations(true)
                .build()

        imageViewFour?.controller = controller

    } // onCreate
}