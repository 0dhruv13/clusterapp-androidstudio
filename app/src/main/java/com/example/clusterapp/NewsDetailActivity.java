package com.example.clusterapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        NewsArticle article = (NewsArticle) getIntent().getSerializableExtra("article");

        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView titleView = findViewById(R.id.detailTitle);
        TextView categoryView = findViewById(R.id.detailCategory);
        TextView contentView = findViewById(R.id.detailContent);

        if (article != null) {
            titleView.setText(article.getTitle());
            categoryView.setText(article.getCategory());
            contentView.setText(article.getFullContent());
        }
    }
}
