package com.hugokallstrom.hugodemo.feature.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hugokallstrom.hugodemo.R

class SearchResultsAdapter(
    private val context: Context,
    private val listener: SearchAdapterClickListener
) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>() {

    var items: List<SearchResultItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return LayoutInflater.from(context)
            .inflate(R.layout.item_search_result, parent, false)
            .let { SearchResultViewHolder(it) }
    }

    override fun onBindViewHolder(viewHolder: SearchResultViewHolder, position: Int) {
        val item = items[position]
        viewHolder.bind(item, listener)
    }

    class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleView = view.findViewById<TextView>(R.id.title)
        private val favouriteButton = view.findViewById<ImageView>(R.id.favourite_button)

        fun bind(
            searchResultItem: SearchResultItem,
            listener: SearchAdapterClickListener
        ) {
            with(searchResultItem) {
                titleView.text = title

                favouriteButton.setOnClickListener {
                    if (favourite) {
                        listener.onRemoveFavourite(id)
                    } else {
                        listener.onAddFavourite(id)
                    }
                }

                favouriteButton.setImageDrawable(
                    if (favourite) favouriteButton.context.getDrawable(R.drawable.ic_favorite_black_24dp) else favouriteButton.context.getDrawable(R.drawable.ic_favorite_border_black_24dp)
                )

                itemView.setOnClickListener {
                    listener.onFoodClicked(id)
                }
            }
        }
    }

    interface SearchAdapterClickListener {
        fun onFoodClicked(id: String)
        fun onAddFavourite(id: String)
        fun onRemoveFavourite(id: String)
    }
}
