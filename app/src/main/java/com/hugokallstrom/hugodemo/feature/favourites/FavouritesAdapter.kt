package com.hugokallstrom.hugodemo.feature.favourites

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hugokallstrom.hugodemo.R
import com.hugokallstrom.hugodemo.models.Food
import com.hugokallstrom.hugodemo.models.formatContents

class FavouritesAdapter(
    private val context: Context
) : RecyclerView.Adapter<FavouritesAdapter.FavouriteViewHolder>() {

    var items: List<Food> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: FavouriteViewHolder, position: Int) {
        val item = items[position]
        viewHolder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FavouriteViewHolder {
        return LayoutInflater.from(context)
            .inflate(R.layout.item_food_details, parent, false)
            .let { FavouriteViewHolder(it) }
    }

    class FavouriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleView = view.findViewById<TextView>(R.id.title)
        private val categoryView = view.findViewById<TextView>(R.id.category)
        private val contentsView = view.findViewById<TextView>(R.id.contents)

        fun bind(item: Food) {
            with(item) {
                titleView.text = title
                categoryView.text = category
                contentsView.text = formatContents(itemView.context)
            }
        }
    }
}
