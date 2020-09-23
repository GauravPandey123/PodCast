package me.toptas.rssconvertersample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import me.toptas.rssconverter.RssItem

/**
 * Adapter for listing [RssItem]
 */
internal class RssItems4Adapter() : RecyclerView.Adapter<RssItems4Adapter.ViewHolder>() {

    private val itemList = ArrayList<RssItem>()

    /**
     * Set [RssItem] list
     *
     * @param items item list
     */
    fun setItems(items: List<RssItem>) {
        itemList.clear()
        itemList.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItems4Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rss_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RssItems4Adapter.ViewHolder, position: Int) {
        holder.apply {
            textTitle.text = itemList[position].title
            textPubDate.text = itemList[position].publishDate

            if (itemList[position].image != null) {
                Picasso.get()
                        .load("https://content.production.cdn.art19.com/images/81/8f/a0/6a/818fa06a-b573-43c9-a870-fef30e9cac5e/7f0421f73d2ce0ca272e392c937e1a301285d44fe7c6d710c2844d80c0c7bb1a3e9838ac03ee80fc64199891cb9d5c6e9d4490f5081fb379c0ab2317f2cadf14.jpeg")
                        .fit()
                        .centerCrop()
                        .into(imageThumb)
            }
            itemView.setOnClickListener {
            }

            itemView.tag = itemList[position]
        }
    }

    override fun getItemCount() = itemList.size

    internal interface OnItemClickListener {
        fun onItemSelected(rssItem: RssItem)
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val textPubDate: TextView = itemView.findViewById(R.id.tvPubDate)
        val imageThumb: ImageView = itemView.findViewById(R.id.ivThumb)
        val llTextContainer: RelativeLayout = itemView.findViewById(R.id.llTextContainer)
    }
}
