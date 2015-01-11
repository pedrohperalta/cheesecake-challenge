package com.cheesecake.articles;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        public TextView dateView;
    }


    /**
     * Adapter constructor.
     * @param context Object containing the current state of the MainActivity state.
     * @param articles List of objects of type article.
     */
    public ArticlesAdapter(Context context, ArrayList<Article> articles) {
        super(context, R.layout.article_line);
        this.context = context;
        this.articles = articles;
    }


    /**
     * Method to get the view that will display the data in an specified position.
     * @param position Index of the row.
     * @param convertView ScrapView that will be recycled
     * @param parent
     * @return
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
            viewHolder.dateView  = (TextView)convertView.findViewById(R.id.article_date);

            convertView.setTag(viewHolder);
        }

        // Fill the field of the view with the desired data.
        ViewHolder holder = (ViewHolder)convertView.getTag();
        holder.titleView.setText(articles.get(position).getTitle());
        holder.dateView.setText(articles.get(position).getDate());

        return convertView;
    }


    /**
     * Add another article object to the resource data.
     * @param article List of objects with the relevant data to be displayed.
     */
    @Override
    public void add(Article article) {
        super.add(article);
    }
}
