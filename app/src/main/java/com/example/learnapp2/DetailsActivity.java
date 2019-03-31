package com.example.learnapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class DetailsActivity extends AppCompatActivity {
    public final static String MODEL = "model";
    TextView author;
    TextView description;
    ImageView photo;
    Model post;
    Animation animation;
    ChipGroup group;
    Chip firstTag;
    Chip secondTag;
    Chip thirdTag;
    String[] tagsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        author = findViewById(R.id.fullnameAuthor);
        description = findViewById(R.id.desciption);
        photo = findViewById(R.id.photo);
        group = findViewById(R.id.groupTag);
        firstTag = findViewById(R.id.firstTag);
        secondTag = findViewById(R.id.secondTag);
        thirdTag = findViewById(R.id.thirdTag);

        post = (Model)getIntent().getSerializableExtra(MODEL);

        photo.setImageResource(post.getImage());
        author.setText(post.getAuthor());
        description.setText(post.getDesc());
        tagsArray = post.getTags().split(",");

        firstTag.setText(tagsArray[0]);
        secondTag.setText(tagsArray[1]);
        thirdTag.setText(tagsArray[2]);

        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        description.startAnimation(animation);
        group.startAnimation(animation);
    }
}
