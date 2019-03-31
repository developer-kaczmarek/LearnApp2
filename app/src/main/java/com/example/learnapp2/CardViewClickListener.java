package com.example.learnapp2;

import androidx.core.util.Pair;
import android.view.View;

public interface CardViewClickListener {

    void onClick(Model model, Pair<View, String> pairOfImage, Pair<View, String> pairOfTitle);
}
