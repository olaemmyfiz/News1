package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private TextView desc;
    private TextView url;
    private ImageView image;
    private TextView textClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        desc = findViewById(R.id.desc);
        url = findViewById(R.id.link);
        image = findViewById(R.id.image);
        textClick = findViewById(R.id.textClick);

        String description = getIntent().getExtras().getString("description");
        String link =  getIntent().getExtras().getString("url");
        String pic = getIntent().getExtras().getString("image");

        textClick.setText("Click the link below to get full gist..");
        desc.setText(description);
        url.setText(link);
        Linkify.addLinks(url, Linkify.WEB_URLS);

        Glide.with(this)
                .load(pic)
                .into(image);

    }
}
