package com.hugokallstrom.hugodemo.database

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.hugokallstrom.hugodemo.models.Food
import io.reactivex.Flowable


@Dao
interface FoodDao {
    @get:Query("SELECT * FROM favouriteFood")
    val all: Flowable<List<FoodEntity>>

    @Query("SELECT * FROM favouriteFood WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): FoodEntity

    @Insert
    fun insertAll(vararg food: FoodEntity)

    @Delete
    fun delete(food: FoodEntity)
}