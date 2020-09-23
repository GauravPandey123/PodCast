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
internal class RssItems2Adapter(val context: Context) : RecyclerView.Adapter<RssItems2Adapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItems2Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rss_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RssItems2Adapter.ViewHolder, position: Int) {
        holder.apply {
            textTitle.text = itemList[position].title
            textPubDate.text = itemList[position].publishDate

            if (itemList[position].image != null) {
                Picasso.get()
                        .load("https://content.production.cdn.art19.com/images/01/1b/f3/d6/011bf3d6-a448-4533-967b-e2f19e376480/c81936f538106550b804e7e4fe2c236319bab7fba37941a6e8f7e5c3d3048b88fc5b2182fb790f7d446bdc820406456c94287f245db89d8656c105d5511ec3de.jpeg")
                        .fit()
                        .centerCrop()
                        .into(imageThumb)
            }
            itemView.setOnClickListener {
                val intent = Intent(context,DetailsActivtity::class.java)
                intent.putExtra("isComingFrom","third")
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
