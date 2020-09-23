package me.toptas.rssconvertersample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.rlContainer, RssFragment.newInstance("http://joeroganexp.joerogan.libsynpro.com/rss"))
                .commit()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.rlContainer, RssFragment.newInstance1("https://naval.libsyn.com/rss"))
                .commit()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.rlContainer, RssFragment.newInstance2("http://rss.art19.com/the-daily "))
                .commit()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.rlContainer, RssFragment.newInstance3("https://podcastfeeds.nbcnews.com/dateline-nbc "))
                .commit()


        supportFragmentManager
                .beginTransaction()
                .add(R.id.rlContainer, RssFragment.newInstance4("https://feeds.megaphone.fm/WWO8086402096"))
                .commit()
    }
}
