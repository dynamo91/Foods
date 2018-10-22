package com.hugokallstrom.hugodemo.database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database

@Database(entities = [(FoodEntity::class)], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}
