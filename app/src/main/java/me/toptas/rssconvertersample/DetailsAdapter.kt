package me.toptas.rssconvertersample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.squareup.picasso.Picasso

import me.toptas.rssconverter.RssItem
import java.util.*

/**
 * Adapter for listing [RssItem]
 */
internal class DetailsAdapter() : RecyclerView.Adapter<DetailsAdapter.ViewHolder>(), Filterable {

    private val itemList = ArrayList<RssItem>()

    var itemListFilterList = ArrayList<RssItem>()

    init {
        itemListFilterList = itemList
    }

    /**
     * Set [RssItem] list
     *
     * @param items item list
     */
    fun setItems(items: List<RssItem>) {
        itemList.clear()
        itemList.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_layout_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        holder.apply {
            textTitle.text = itemList[position].title
            textPubDate.text = itemList[position].publishDate
            itemView.tag = itemList[position]
        }
    }

    override fun getItemCount() = itemList.size

    internal interface OnItemClickListener {
        fun onItemSelected(rssItem: RssItem)
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.detailTitle)
        val textPubDate: TextView = itemView.findViewById(R.id.textViewDate)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemListFilterList = itemList
                } else {
                    val resultList = ArrayList<RssItem>()
                    for (row in itemList) {
                        if (row.title?.toLowerCase(Locale.ROOT)!!.contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    itemListFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemListFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemListFilterList = results?.values as ArrayList<RssItem>
                notifyDataSetChanged()
            }

        }
    }
}
