package com.cheesecake.articles;

/**
 * Created by Pedro Henrique on 10/01/2015.
 */
public class Article {

    private String title;
    private String date;
    private String content;
    private String author;

    /**
     * Represents an article object
     * @param title Article's title.
     * @param date Article's publish date.
     */
    public Article(String title, String date) {
        this.title = title;
        this.date  = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
