package com.hugokallstrom.hugodemo.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.hugokallstrom.hugodemo.models.Food


@Entity(tableName = "favouriteFood")
data class FoodEntity(
    @PrimaryKey
    val id: String = "",
    val title: String,
    val category: String,
    val gramsperserving: Double,
    val calories: Int,
    val cholesterol: Double,
    val sugar: Double,
    val fiber: Double,
    val unsaturatedfat: Double,
    val potassium: Double,
    val sodium: Double,
    val protein: Double,
    val fat: Double,
    val carbohydrates: Double,
    val saturatedfat: Double
) {
    constructor(foodModel: Food) : this(
        id = foodModel.id,
        title = foodModel.title,
        category = foodModel.category,
        gramsperserving = foodModel.gramsperserving,
        calories = foodModel.calories,
        cholesterol = foodModel.cholesterol,
        sugar = foodModel.sugar,
        fiber = foodModel.fiber,
        unsaturatedfat = foodModel.unsaturatedfat,
        potassium = foodModel.potassium,
        sodium = foodModel.sodium,
        protein = foodModel.protein,
        fat = foodModel.fat,
        carbohydrates = foodModel.carbohydrates,
        saturatedfat = foodModel.saturatedfat
    )
}