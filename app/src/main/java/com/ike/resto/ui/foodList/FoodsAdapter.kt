package com.ike.resto.ui.foodList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ike.resto.R
import com.ike.resto.ui.data.Food

class FoodsAdapter(private val onClick: (Food) -> Unit) :
    ListAdapter<Food, FoodsAdapter.FoodViewHolder>(FoodDiffCallback) {

    class FoodViewHolder(itemView: View, val onClick: (Food) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val foodLabelView: TextView = itemView.findViewById(R.id.food_label)
        private val foodTextView: TextView = itemView.findViewById(R.id.food_text)
        private val foodImageView: ImageView = itemView.findViewById(R.id.food_image)
        private var currentFood: Food? = null

        init {
            itemView.setOnClickListener {
                currentFood?.let {
                    onClick(it)
                }
            }
        }

        fun bind(food: Food) {
            currentFood = food

            foodLabelView.text = food.name
            foodTextView.text = food.description
            if (food.image != null) {
                foodImageView.setImageResource(food.image)
            } else {
                foodImageView.setImageResource(R.drawable.food1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = getItem(position)
        holder.bind(food)

    }
}

object FoodDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }
}
