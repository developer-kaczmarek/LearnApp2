package com.example.learnapp2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class Adapter extends PagerAdapter {
    private List<Model> models;
    private Context context;
    private LayoutInflater layoutInflater;
    private CardViewClickListener cardViewClickListener;

    public Adapter(List<Model> models, Context context, CardViewClickListener cardViewClickListener) {
        this.models = models;
        this.context = context;
        this.cardViewClickListener = cardViewClickListener;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container,false);
        ImageView photo = view.findViewById(R.id.photo);
        TextView fullnameAuthor = view.findViewById(R.id.fullnameAuthor);
        CardView cardView = view.findViewById(R.id.cardView);

        photo.setImageResource(models.get(position).getImage());
        fullnameAuthor.setText(models.get(position).getAuthor());
        cardView.setOnClickListener(v -> {
            final Pair<View,String> pairOfImage = Pair.create(photo,"photo");
            final Pair<View,String> pairOfTitle = Pair.create(fullnameAuthor,"title");
            cardViewClickListener.onClick(models.get(position), pairOfImage, pairOfTitle);
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
