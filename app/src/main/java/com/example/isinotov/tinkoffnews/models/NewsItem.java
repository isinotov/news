package com.example.isinotov.tinkoffnews.models;

import io.realm.RealmObject;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsItem extends RealmObject {
    private long id;
    private String name;
    private String text;
    private int bankInfoTypeId;
    private DateHolder publicationDate;

    public int getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public long getId() {
        return id;
    }

    public void setBankInfoTypeId(int bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublicationDate(DateHolder publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public DateHolder getPublicationDate() {
        return publicationDate;
    }

    public String getText() {
        return text;
    }
}
