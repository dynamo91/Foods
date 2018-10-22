package com.hugokallstrom.hugodemo.database

import com.hugokallstrom.hugodemo.models.Food
import io.reactivex.Completable
import io.reactivex.Flowable

class FoodRepository(private val dao: FoodDao) {

    fun persist(food: Food): Completable {
        return Completable.fromCallable {
            dao.insertAll(FoodEntity(food))
        }
    }

    fun delete(food: Food): Completable {
        return Completable.fromCallable {
            dao.delete(FoodEntity(food))
        }
    }

    fun getAll(): Flowable<List<Food>> = dao.all.map {
        it.map { it.toFoodModel() }
    }

    private fun FoodEntity.toFoodModel() = Food(
        id,
        title,
        category,
        gramsperserving,
        calories,
        cholesterol,
        sugar,
        fiber,
        unsaturatedfat,
        potassium,
        sodium,
        protein,
        fat,
        carbohydrates,
        saturatedfat
    )
}