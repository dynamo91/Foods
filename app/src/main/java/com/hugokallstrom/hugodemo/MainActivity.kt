package com.hugokallstrom.hugodemo

import android.arch.persistence.room.Room
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.hugokallstrom.hugodemo.database.Database
import com.hugokallstrom.hugodemo.database.FoodRepository
import com.hugokallstrom.hugodemo.feature.details.FoodDetailsFragment
import com.hugokallstrom.hugodemo.feature.details.FoodDetailsParameter
import com.hugokallstrom.hugodemo.feature.favourites.FavouritesFragment
import com.hugokallstrom.hugodemo.feature.search.SearchFoodFragment
import kotlinx.android.synthetic.main.activity_main.tablayout

class MainActivity : AppCompatActivity(), RepoActivity {

    private var database: Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        viewPager.adapter = ScreenSlidePagerAdapter(this, supportFragmentManager)
        tablayout.setupWithViewPager(viewPager)
        createDatabase()
    }

    private fun createDatabase(): Database {
        val db = Room.databaseBuilder(
            applicationContext,
            Database::class.java, "food-database"
        ).build()
        database = db
        return db
    }

    fun showDetailsFragment(parameter: FoodDetailsParameter) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, FoodDetailsFragment.newInstance(parameter))
            .addToBackStack(null)
            .commit()

    }

    class ScreenSlidePagerAdapter(
        private val context: Context,
        fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getCount(): Int = NUM_PAGES

        override fun getPageTitle(position: Int) = when (position) {
            0 -> context.getString(R.string.fragment_title_search)
            1 -> context.getString(R.string.fragment_title_favourites)
            else -> throw IllegalArgumentException("Unknown position $position")
        }

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> SearchFoodFragment()
            1 -> FavouritesFragment()
            else -> throw IllegalArgumentException("Unknown position $position")
        }
    }

    override fun getFoodRepo(): FoodRepository {
        val dao = database?.foodDao() ?: createDatabase().foodDao()
        return FoodRepository(dao)
    }

    companion object {
        private const val NUM_PAGES = 2
    }

}

interface RepoActivity {
    fun getFoodRepo(): FoodRepository
}
