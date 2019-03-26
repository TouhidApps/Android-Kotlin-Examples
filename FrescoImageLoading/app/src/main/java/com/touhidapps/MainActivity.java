package com.touhidapps;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.request.ImageRequest;

public class MainActivity extends AppCompatActivity {

    SimpleDraweeView imageViewOne;
    SimpleDraweeView imageViewTwo;
    SimpleDraweeView imageViewThree;
    SimpleDraweeView imageViewFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewOne = findViewById(R.id.imageViewOne);
        imageViewTwo = findViewById(R.id.imageViewTwo);
        imageViewThree = findViewById(R.id.imageViewThree);
        imageViewFour = findViewById(R.id.imageViewFour);

        // normal loading
        imageViewOne.setImageURI(Uri.parse(MyLinks.IMAGE_ONE));

        // with various options form xml
        imageViewTwo.setImageURI(Uri.parse(MyLinks.IMAGE_TWO));

        // Round image
        imageViewThree.setImageURI(Uri.parse(MyLinks.IMAGE_THREE));

        // GIF animated image automatically animated (Also possible manual start animation)
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(MyLinks.IMAGE_FOUR))
                .setAutoPlayAnimations(true)
                .build();
        imageViewFour.setController(controller);



    } // onCreate




}
