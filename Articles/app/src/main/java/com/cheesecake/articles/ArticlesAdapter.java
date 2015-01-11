package com.cheesecake.articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Pedro Henrique on 10/01/2015.
 */
public class ArticlesAdapter extends ArrayAdapter<Article> {

    private final Context context;
    private final ArrayList<Article> articles;


    /**
     * Implements the ViewHolder patter in order to reduce the "findViewById()" calls
     * in the getView() function. This way, the application will only have to call
     * "findViewById()" when the screen first create the layout.
     */
    private static class ViewHolder {
        public TextView titleView;
        public TextView authorsView;
        public TextView dateView;
        public TextView websiteView;
    }


    /**
     * Adapter constructor.
     * @param context Object containing the current state of the MainActivity state.
     * @param articleModels List of objects of type article.
     */
    public ArticlesAdapter(Context context, ArrayList<Article> articleModels) {
        super(context, R.layout.article_line);
        this.context = context;
        this.articles = articleModels;
    }


    /**
     * Method to get the view that will display the data in an specified position.
     * @param position Index of the row.
     * @param convertView ScrapView that will be recycled
     * @param parent View group parent
     * @return View representing a row in the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflates a new view when there's no other to be recycled. This way the view are reused.
        if(convertView == null) {
            // In this function case, the view is inflated from an xml file instead of being manually created.
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.article_line, parent, false);
            // Configure the view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleView = (TextView)convertView.findViewById(R.id.article_title);
            viewHolder.authorsView = (TextView)convertView.findViewById(R.id.article_authors);
            viewHolder.dateView  = (TextView)convertView.findViewById(R.id.article_date);
            viewHolder.websiteView  = (TextView)convertView.findViewById(R.id.article_website);

            convertView.setTag(viewHolder);
        }

        // Convert the Date to String given the format below.
        SimpleDateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = articles.get(position).getDate();
        String dateText = dateFormat.format(date);

        // Fill the field of the view with the desired data.
        ViewHolder holder = (ViewHolder)convertView.getTag();
        holder.titleView.setText(articles.get(position).getTitle());
        holder.authorsView.setText("Written by: " + articles.get(position).getAuthors());
        holder.dateView.setText(dateText);
        holder.websiteView.setText("Source: " + articles.get(position).getWebsite());

        return convertView;
    }


    /**
     * Add another article object to the resource data.
     * @param articleModel List of objects with the relevant data to be displayed.
     */
    @Override
    public void add(Article articleModel) {
        super.add(articleModel);
        notifyDataSetChanged();
    }


    /**
     * Sort the list based on the user's choice.
     * @param choice Indicates the tye of sorting.
     */
    public void sortList(int choice) {

        switch (choice) {
            // Sort articles by source website.
            case R.id.sort_by_website:
                Collections.sort(articles, Article.articleWebsiteComparator);
                break;

            // Sort articles by authors.
            case R.id.sort_by_authors:
                Collections.sort(articles, Article.articleAuthorsComparator);
                break;

            // Sort articles by title.
            case R.id.sort_by_title:
                Collections.sort(articles, Article.articleTitleComparator);
                break;

            // Sort articles by publish date.
            case R.id.sort_by_date:
                Collections.sort(articles, Article.articleDateComparator);
                break;
        }

        notifyDataSetChanged();
    }
}
