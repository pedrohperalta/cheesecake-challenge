package com.cheesecake.articles;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Pedro Henrique on 10/01/2015.
 */
public class Article {

    /*
        The annotation @SerializedName are not required when the class attributes names match
        with the JSON field. However, in this case it will be used for a good practice matter.
     */

    @SerializedName("website")
    private String website;

    @SerializedName("authors")
    private String authors;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("date")
    private Date date;

    /**
     * Represents an article object
     * @param website Article's origin website
     * @param authors Article's authors
     * @param title Article's title
     * @param content Article's content
     * @param date Article's publish date
     */
    public Article(String website, String authors, String title, String content, Date date) {
        this.website = website;
        this.authors = authors;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
