package me.toptas.rssconvertersample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_activity.*
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class DetailsActivtity : AppCompatActivity() {


    private var feedUrl: String? = "http://joeroganexp.joerogan.libsynpro.com/rss"
    private var feedUrl1: String? = "https://naval.libsyn.com/rss"
    private var feedUrl2: String? = "http://rss.art19.com/the-daily"
    private var feedUrl3: String? = "https://podcastfeeds.nbcnews.com/dateline-nbc"
    private var feedUrl4: String? = "https://feeds.megaphone.fm/WWO8086402096"

    private lateinit var mAdapter: DetailsAdapter
    private lateinit var isComingFrom : String
    private lateinit var link : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        mAdapter = DetailsAdapter()
        data_ofrecycler.layoutManager = LinearLayoutManager(this)
        data_ofrecycler.adapter = mAdapter
        setUpElements()
        setUpListenter()
    }

    private fun setUpListenter() {
        share_data.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Podcast")
            intent.putExtra(Intent.EXTRA_TEXT, link)
            startActivity(Intent.createChooser(intent, "choose one"))
        }

        country_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }

        })


    }

    private fun setUpElements() {
        val intent = intent
        isComingFrom = intent.getStringExtra("isComingFrom")
        fetchRss()


        // If you want to change the color of the cursor, change the 'colorAccent' in colors.xml


    }

    private fun fetchRss() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(RssConverterFactory.create())
                .build()

        val service = retrofit.create(RssService::class.java)
        if(isComingFrom.equals("First")) {
            feedUrl?.apply {
                service.getRss(this)
                        .enqueue(object : Callback<RssFeed> {
                            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                                onRssItemsLoaded(response.body()!!.items!!)
                                nameoftext.setText(response.body()!!.items!![0].title)
                                link =response.body()!!.items!![0].link.toString()
                                Picasso.get()
                                        .load("http://static.libsyn.com/p/assets/7/1/f/3/71f3014e14ef2722/JREiTunesImage2.jpg")
                                        .fit()
                                        .centerCrop()
                                        .into(imageofnoval)
                            }

                            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                                Toast.makeText(this@DetailsActivtity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                            }
                        })
            }
        }else if(isComingFrom.equals("Second")){
            feedUrl1?.apply {
                service.getRss(this)
                        .enqueue(object : Callback<RssFeed> {
                            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                                onRssItemsLoaded(response.body()!!.items!!)
                                nameoftext.setText(response.body()!!.items!![0].title)
                                link =response.body()!!.items!![0].link.toString()

                                Picasso.get()
                                        .load("https://ssl-static.libsyn.com/p/assets/5/c/e/b/5ceb9fba4ff0ab14/podcast_artwork.jpg")
                                        .fit()
                                        .centerCrop()
                                        .into(imageofnoval)
                            }

                            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                                Toast.makeText(this@DetailsActivtity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                            }
                        })
            }
        }else if(isComingFrom.equals("third")){
            feedUrl2?.apply {
                service.getRss(this)
                        .enqueue(object : Callback<RssFeed> {
                            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                                onRssItemsLoaded(response.body()!!.items!!)
                                nameoftext.setText(response.body()!!.items!![0].title)
                                link =response.body()!!.items!![0].link.toString()

                                Picasso.get()
                                        .load("https://content.production.cdn.art19.com/images/01/1b/f3/d6/011bf3d6-a448-4533-967b-e2f19e376480/c81936f538106550b804e7e4fe2c236319bab7fba37941a6e8f7e5c3d3048b88fc5b2182fb790f7d446bdc820406456c94287f245db89d8656c105d5511ec3de.jpeg")
                                        .fit()
                                        .centerCrop()
                                        .into(imageofnoval)
                            }

                            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                                Toast.makeText(this@DetailsActivtity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                            }
                        })
            }
        }else if(isComingFrom.equals("fourth")){
            feedUrl3?.apply {
                service.getRss(this)
                        .enqueue(object : Callback<RssFeed> {
                            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                                onRssItemsLoaded(response.body()!!.items!!)
                                nameoftext.setText(response.body()!!.items!![0].title)
                                link =response.body()!!.items!![0].link.toString()

                                Picasso.get()
                                        .load("https://content.production.cdn.art19.com/images/81/8f/a0/6a/818fa06a-b573-43c9-a870-fef30e9cac5e/7f0421f73d2ce0ca272e392c937e1a301285d44fe7c6d710c2844d80c0c7bb1a3e9838ac03ee80fc64199891cb9d5c6e9d4490f5081fb379c0ab2317f2cadf14.jpeg")
                                        .fit()
                                        .centerCrop()
                                        .into(imageofnoval)
                            }

                            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                                Toast.makeText(this@DetailsActivtity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                            }
                        })
            }
        }

    }

    private fun onRssItemsLoaded(takeLast: List<RssItem>) {
        mAdapter.setItems(takeLast)
        mAdapter.notifyDataSetChanged()
        if (data_ofrecycler.visibility != View.VISIBLE) {
            data_ofrecycler.visibility = View.VISIBLE
        }
    }
}