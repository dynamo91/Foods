package com.hugokallstrom.hugodemo.feature.favourites

import com.hugokallstrom.hugodemo.database.FoodRepository
import com.hugokallstrom.hugodemo.feature.favourites.Error.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavouritesPresenter(
    val view: FavouritesView,
    private val foodRepository: FoodRepository
) {

    fun getFavourites() {
        foodRepository.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.reversed() }
            .subscribe(view::setFavourites, { view.showError(DATABASE_ERROR) })
    }
}

