package com.ike.resto.ui.data

import androidx.annotation.DrawableRes

data class Food(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
    val description: String
)
