package me.toptas.rssconvertersample

import android.content.Context
import android.content.Intent
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
internal class RssItemsAdapter(private val context: Context) : RecyclerView.Adapter<RssItemsAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItemsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_rss_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RssItemsAdapter.ViewHolder, position: Int) {
        holder.apply {
            textTitle.text = itemList[position].title
            textPubDate.text = itemList[position].publishDate

            if (itemList[position].image != null) {
                Picasso.get()
                        .load("http://static.libsyn.com/p/assets/7/1/f/3/71f3014e14ef2722/JREiTunesImage2.jpg")
                        .fit()
                        .centerCrop()
                        .into(imageThumb)
            }
            itemView.setOnClickListener {
                val intent = Intent(context,DetailsActivtity::class.java)
                intent.putExtra("isComingFrom","First")
                context.startActivity(intent)
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
