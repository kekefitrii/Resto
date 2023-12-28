package com.ike.resto.ui.data

import android.content.res.Resources
import com.ike.resto.R

fun foodList(resources: Resources): List<Food> {
    return listOf(
        Food(
            id = 1,
            name = resources.getString(R.string.food1_name),
            image = R.drawable.food1,
            description = resources.getString(R.string.food1_description)
        ),
        Food(
            id = 2,
            name = resources.getString(R.string.food2_name),
            image = R.drawable.food2,
            description = resources.getString(R.string.food2_description)
        ),
        Food(
            id = 3,
            name = resources.getString(R.string.food3_name),
            image = R.drawable.food3,
            description = resources.getString(R.string.food3_description)
        ),
        Food(
            id = 4,
            name = resources.getString(R.string.food4_name),
            image = R.drawable.food4,
            description = resources.getString(R.string.food4_description)
        ),
        Food(
            id = 5,
            name = resources.getString(R.string.food5_name),
            image = R.drawable.food5,
            description = resources.getString(R.string.food5_description)
        ),
        Food(
            id = 6,
            name = resources.getString(R.string.food6_name),
            image = R.drawable.food6,
            description = resources.getString(R.string.food6_description)
        ),
        Food(
            id = 7,
            name = resources.getString(R.string.food7_name),
            image = R.drawable.food7,
            description = resources.getString(R.string.food7_description)
        ),
        Food(
            id = 8,
            name = resources.getString(R.string.food8_name),
            image = R.drawable.food8,
            description = resources.getString(R.string.food8_description)
        ),
        Food(
            id = 9,
            name = resources.getString(R.string.food9_name),
            image = R.drawable.food9,
            description = resources.getString(R.string.food9_description)
        ),
        Food(
            id = 10,
            name = resources.getString(R.string.food10_name),
            image = R.drawable.food10,
            description = resources.getString(R.string.food10_description)
        ),
        Food(
            id = 11,
            name = resources.getString(R.string.food11_name),
            image = R.drawable.food11,
            description = resources.getString(R.string.food11_description)
        )
    )
}
