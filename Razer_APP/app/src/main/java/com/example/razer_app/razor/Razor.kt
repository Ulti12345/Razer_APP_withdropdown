package com.example.razer_app.razor

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.razer_app.R

data class Razor(
    @DrawableRes val imageResourceId: Int,
    @StringRes val stringResourceId: Int,
   @StringRes val descResourceId: Int
)

val razer = listOf(
    Razor(R.drawable.razer_viper, R.string.viper, R.string.viper_description),
    Razor(R.drawable.razer_3, R.string.kishi, R.string.kishi_description),
    Razor(R.drawable.razer_firefly, R.string.firefly, R.string.firefly_description)
)