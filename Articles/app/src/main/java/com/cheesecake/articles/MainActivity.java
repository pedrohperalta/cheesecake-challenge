package com.cheesecake.articles;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.quentindommerc.superlistview.OnMoreListener;
import com.quentindommerc.superlistview.SuperListview;
import com.quentindommerc.superlistview.SwipeDismissListViewTouchListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener  {

    // Github library that makes listview easier to use.
    // More info in: https://github.com/dommerq/SuperListview
    private SuperListview mListArticles;

    // Custom adapter for showing articles information.
    private ArticlesAdapter articlesAdapter;


    @SuppressWarnings("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List of dummy articles.
        final ArrayList<Article> articlesList = new ArrayList<Article>();
        articlesList.add(new Article("Article 1", "10/01/2015"));
        articlesList.add(new Article("Article 2", "11/01/2015"));
        articlesList.add(new Article("Article 3", "12/01/2015"));
        articlesList.add(new Article("Article 4", "13/01/2015"));

        articlesAdapter = new ArticlesAdapter(this, articlesList);

        // Instance of SuperListview from the xml view.
        mListArticles = (SuperListview)findViewById(R.id.list_articles);

        // Sleeps for 2 seconds and then add the list of dummy articles to the screen.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        articlesAdapter.add(articlesList.get(0));
                        articlesAdapter.add(articlesList.get(1));
                        articlesAdapter.add(articlesList.get(2));
                        articlesAdapter.add(articlesList.get(3));

                        // Set the adapter to the SuperListview.
                        mListArticles.setAdapter(articlesAdapter);
                    }
                });
            }
        });

        thread.start();

        // Setting the refresh listener will enable the refresh progressbar.
        mListArticles.setRefreshListener(this);

        // TODO: Find out why it's not working.
        /*
        // Set the colors which will appear when the list is been reloaded.
        mListArticles.setRefreshingColor(android.R.color.holo_blue_bright,
                                         android.R.color.holo_blue_light,
                                         android.R.color.holo_blue_dark,
                                         android.R.color.darker_gray);
        */


        // LoadMore triggered when the list reaches the last item (1)
        // mListArticles.setupMoreListener(this, 1);

        mListArticles.setupSwipeToDismiss(new SwipeDismissListViewTouchListener.DismissCallbacks() {
            @Override
            public boolean canDismiss(int position) {
                return true;
            }

            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            }
        }, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Handles the swype down action to the user.
     */
    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO
                // Request the articles from the url to refresh them just in case they have changed.

                // For now, the swype down action only clears the articles list.
                articlesAdapter.clear();
            }
        }, 2000);
    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) { }
}
