package me.toptas.rssconvertersample


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_rss.*
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * Fragment for listing fetched [RssItem] list
 */
class RssFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, RssItemsAdapter.OnItemClickListener {

    private var feedUrl: String? = null
    private var feedUrl1: String? = null
    private var feedUrl2: String? = null
    private var feedUrl3: String? = null
    private var feedUrl4: String? = null
    private lateinit var mAdapter: RssItemsAdapter
    private lateinit var mAdapter1: RssItems1Adapter
    private lateinit var mAdapter2: RssItems2Adapter
    private lateinit var mAdapter3: RssItems3Adapter
    private lateinit var mAdapter4: RssItems4Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedUrl = arguments?.getString(KEY_FEED)
        feedUrl2 = arguments?.getString(KEY_FEED)
        feedUrl3 = arguments?.getString(KEY_FEED)
        feedUrl4 = arguments?.getString(KEY_FEED)
        feedUrl1 = arguments?.getString(KEY_FEED1)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rss, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            setUpListners()
        mAdapter1 = RssItems1Adapter(requireContext())
        recyclerView.layoutManager = GridLayoutManager(activity,1)
        recyclerView.adapter = mAdapter1

        mAdapter = RssItemsAdapter(requireContext())
        recyclerView1.layoutManager = GridLayoutManager(activity,1)
        recyclerView1.adapter = mAdapter

        mAdapter2 = RssItems2Adapter(requireContext())
        recyclerView2.layoutManager = GridLayoutManager(activity,1)
        recyclerView2.adapter = mAdapter2

        mAdapter3 = RssItems3Adapter(requireContext())
        recyclerView4.layoutManager = GridLayoutManager(activity,1)
        recyclerView4.adapter = mAdapter3


        mAdapter4 = RssItems4Adapter()

        fetchRss()
    }

    private fun setUpListners() {

    }

    /**
     * Fetches Rss Feed Url
     */
    private fun fetchRss() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(RssConverterFactory.create())
                .build()

        showLoading()
        val service = retrofit.create(RssService::class.java)

        feedUrl?.apply {
            service.getRss(this)
                    .enqueue(object : Callback<RssFeed> {
                        override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                            onRssItemsLoaded(response.body()!!.items!!.takeLast(1))
                            hideLoading()
                        }

                        override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                            Toast.makeText(activity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                        }
                    })
        }
        feedUrl1?.apply {
            service.getRss(this)
                    .enqueue(object : Callback<RssFeed> {
                        override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                            onRssItems1Loaded(response.body()!!.items!!.takeLast(1))
                            hideLoading()
                        }

                        override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                            Toast.makeText(activity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                        }
                    })
        }

        feedUrl2?.apply {
            service.getRss(this)
                    .enqueue(object : Callback<RssFeed> {
                        override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                            onRssItems2Loaded(response.body()!!.items!!.takeLast(1))
                            hideLoading()
                        }

                        override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                            Toast.makeText(activity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                        }
                    })
        }
        feedUrl3?.apply {
            service.getRss(this)
                    .enqueue(object : Callback<RssFeed> {
                        override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                            onRssItems3Loaded(response.body()!!.items!!.takeLast(1))
                            hideLoading()
                        }

                        override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                            Toast.makeText(activity, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show()

                        }
                    })
        }

    }



    private fun onRssItems3Loaded(takeLast: List<RssItem>) {
        mAdapter3.setItems(takeLast)
        mAdapter3.notifyDataSetChanged()
        if (recyclerView4.visibility != View.VISIBLE) {
            recyclerView4.visibility = View.VISIBLE
        }
    }

    private fun onRssItems2Loaded(takeLast: List<RssItem>) {
        mAdapter2.setItems(takeLast)
        mAdapter2.notifyDataSetChanged()
        if (recyclerView2.visibility != View.VISIBLE) {
            recyclerView2.visibility = View.VISIBLE
        }
    }

    private fun onRssItemsLoaded(takeLast: List<RssItem>) {
        mAdapter.setItems(takeLast)
        mAdapter.notifyDataSetChanged()
        if (recyclerView1.visibility != View.VISIBLE) {
            recyclerView1.visibility = View.VISIBLE
        }
    }

    private fun onRssItems1Loaded(takeLast: List<RssItem>) {
        mAdapter1.setItems(takeLast)
        mAdapter1.notifyDataSetChanged()
        if (recyclerView.visibility != View.VISIBLE) {
            recyclerView.visibility = View.VISIBLE
        }
    }

    /**
     * Loads fetched [RssItem] list to RecyclerView
     * @param rssItems
     */

    /**
     * Shows [SwipeRefreshLayout]
     */
    private fun showLoading() {
        //swRefresh.isRefreshing = true
    }


    /**
     * Hides [SwipeRefreshLayout]
     */
    fun hideLoading() {
       // swRefresh.isRefreshing = false
    }


    /**
     * Triggers on [SwipeRefreshLayout] refresh
     */
    override fun onRefresh() {
        fetchRss()
    }

    /**
     * Browse [RssItem] url
     * @param rssItem
     */
    override fun onItemSelected(rssItem: RssItem) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rssItem.link))
        startActivity(intent)
    }

    companion object {
        private const val KEY_FEED = "FEED"
        private const val KEY_FEED1 = "FEED1"

        /**
         * Creates new instance of [RssFragment]
         * @param feedUrl Fetched Url Feed
         * @return Fragment
         */
        fun newInstance(feedUrl: String): RssFragment {
            val rssFragment = RssFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_FEED, feedUrl)
            rssFragment.arguments = bundle
            return rssFragment
        }

        fun newInstance1(feedUrl1: String): RssFragment {
            val rssFragment = RssFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_FEED1, feedUrl1)
            rssFragment.arguments = bundle
            return rssFragment
        }
        fun newInstance2(feedUrl2: String): RssFragment {
            val rssFragment = RssFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_FEED, feedUrl2)
            rssFragment.arguments = bundle
            return rssFragment
        }

        fun newInstance3(feedUrl3: String): RssFragment {
            val rssFragment = RssFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_FEED, feedUrl3)
            rssFragment.arguments = bundle
            return rssFragment
        }


        fun newInstance4(feedUrl3: String): RssFragment {
            val rssFragment = RssFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_FEED, feedUrl3)
            rssFragment.arguments = bundle
            return rssFragment
        }
    }

}