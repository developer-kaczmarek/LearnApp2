package com.example.learnapp2;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager container;
    Adapter pagerAdapter;
    ArgbEvaluator argbEvaluatorStartColor = new ArgbEvaluator();
    ArgbEvaluator argbEvaluatorEndColor = new ArgbEvaluator();
    Integer[] startGradientsColors = null;
    Integer[] endGradientsColors = null;
    List<Model> models;
    CardViewClickListener cardViewClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        models = new ArrayList<>();
        //Заполнение списка
        models.add(new Model(R.drawable.image1, getResources().getString(R.string.author1),
                getResources().getString(R.string.desc1), getResources().getString(R.string.tags1)));
        models.add(new Model(R.drawable.image6, getResources().getString(R.string.author2),
                getResources().getString(R.string.desc2),getResources().getString(R.string.tags2)));
        models.add(new Model(R.drawable.image3, getResources().getString(R.string.author3),
                getResources().getString(R.string.desc3),getResources().getString(R.string.tags3)));
        models.add(new Model(R.drawable.image5, getResources().getString(R.string.author4),
                getResources().getString(R.string.desc4),getResources().getString(R.string.tags4)));

        //Метод обработки нажатий на CardView
        cardViewClickListener = (model, pairOfImage, pairOfTitle) -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.MODEL, model);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairOfImage, pairOfTitle);
            startActivity(intent,options.toBundle());
        };

        //Инициализация ViewPager и его адаптера
        pagerAdapter = new Adapter(models,this, cardViewClickListener);
        container = findViewById(R.id.viewPager);
        container.setAdapter(pagerAdapter);
        container.setPadding(130,0,130,0);

        //Инициализация временных массивов с цветами для начальной точки градиента и конечной
        Integer[] startGradientsColorsTmp = {
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color10),
                getResources().getColor(R.color.color12)
        };

        Integer[] endGradientsColorsTmp = {
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color11)
        };

        startGradientsColors = startGradientsColorsTmp;
        endGradientsColors = endGradientsColorsTmp;

        container.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            if (i < pagerAdapter.getCount() - 1 && i < startGradientsColors.length - 1){
                //Расчет цвета стартовой точки градиента
                int startColor = (Integer)argbEvaluatorStartColor.evaluate(
                        v,
                        startGradientsColors[i],
                        startGradientsColors[i + 1]
                );
                //Расчет цвета финишной точки градиента
                int endColor = (Integer)argbEvaluatorEndColor.evaluate(
                        v,
                        endGradientsColors[i],
                        endGradientsColors[i + 1]
                );
                //Заполнение линейного градиента цветами
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, new int[]{startColor, endColor});
                gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                //Установка градиента в качестве фона
                container.setBackground(gradientDrawable);
            } else {
                //Заполнение линейного градиента цветами
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{
                                startGradientsColors[startGradientsColors.length - 1],
                                endGradientsColors[endGradientsColors.length - 1]
                        });
                gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                //Установка градиента в качестве фона
                container.setBackground(gradientDrawable);
            }
        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    });
    }
}
