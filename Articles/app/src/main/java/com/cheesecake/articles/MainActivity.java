package com.cheesecake.articles;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.quentindommerc.superlistview.SuperListview;
import com.quentindommerc.superlistview.SwipeDismissListViewTouchListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener  {

    // Github library that makes listview easier to use.
    // More info in: https://github.com/dommerq/SuperListview
    private SuperListview mListArticles;

    // List of articles.
    ArrayList<Article> articlesList;

    // Custom adapter for showing articles information.
    private ArticlesAdapter articlesAdapter;

    // Application object.
    protected MyApplication application;

    // Cache singleton instance.
    protected Cache cache;


    /*
        Effect for showing the modal dialog window with some article content.
        More info in: https://github.com/sd6352051/NiftyDialogEffects.
     */
    private Effectstype effect;


    @SuppressWarnings("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application instance.
        application = (MyApplication)getApplication();

        // Get the cache instance.
        cache = Cache.getSharedInstance();

        articlesList = new ArrayList<>();

        articlesAdapter = new ArticlesAdapter(this, articlesList);

        // Instance of SuperListview from the xml view.
        mListArticles = (SuperListview)findViewById(R.id.list_articles);

        // Sleeps for 2 seconds and then add the list of dummy articles to the screen.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Number of times the list will try to retrieve data from the cache.
                int attempts = 10;

                while(attempts > 0) {
                    // Waits for a second in order to give the Api time to get the JSON array.
                    try {
                        Thread.sleep(1000);

                        // If the articles have been successfully got from the web...
                        if(cache.getArticlesCache().size() > 0) {
                            for(Article article : cache.getArticlesCache()) {
                                articlesList.add(article);
                            }
                            break;
                        } else {
                            attempts--;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Adds the articles list to the adapter.
                        articlesAdapter.addAll(articlesList);
                        // Set the adapter to the SuperListview.
                        mListArticles.setAdapter(articlesAdapter);
                    }
                });
            }
        });

        thread.start();
        configSuperListview();

        effect = Effectstype.Fliph;
    }


    /**
     * Sets up the working parameters for the list.
     */
    private void configSuperListview() {
        // Setting the refresh listener will enable the refresh progressbar.
        mListArticles.setRefreshListener(this);

        // The 2nd parameter is true if you want SuperListView to automatically
        // delete the item from the listview or false if you don't.
        mListArticles.setupSwipeToDismiss(new SwipeDismissListViewTouchListener.DismissCallbacks() {
            @Override
            public boolean canDismiss(int position) {
                return true;
            }

            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            }
        }, true);

        // Configures the listener for the click event.
        mListArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Article article = (Article)parent.getItemAtPosition(position);
                dialogShow(article);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * Methos to deal with the menu button click.
     * @param item Item chose from the menu
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Tells the adapter to sort its data sorce liste based on the user choice.
        articlesAdapter.sortList(id);

        return super.onOptionsItemSelected(item);
    }


    /**
     * Handles the swype down action to the user.
     */
    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(cache.getArticlesCache().size() > 0) {
                    articlesAdapter.clear();
                    articlesAdapter.addAll(cache.getArticlesCache());
                }
            }
        }, 2000);
    }


    /**
     * Method to invoke the modal screen.
     * @param article The article in the position clicked.
     */
    public void dialogShow(Article article) {
        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);

        dialogBuilder
                .withTitle(article.getAuthors())
                .withTitleColor("#FFFFFF")
                .withDividerColor("#11000000")
                .withMessage(article.getContent())
                .withMessageColor("#FFFFFFFF")
                .withDialogColor("#FF287AA9")
                .isCancelableOnTouchOutside(true)
                .withDuration(400)
                .withEffect(effect)
                .setCustomView(R.layout.article_detail, this.getApplicationContext())
                .show();
    }
}
