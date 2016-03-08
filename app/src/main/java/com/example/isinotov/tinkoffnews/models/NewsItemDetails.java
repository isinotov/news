package com.example.isinotov.tinkoffnews.models;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsItemDetails {
    NewsItem title;
    DateHolder creationDate;
    DateHolder lastModificationDate;
    String content;
    long bankInfoTypeId;
    String typeId;

    public long getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(long bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DateHolder getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateHolder creationDate) {
        this.creationDate = creationDate;
    }

    public DateHolder getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(DateHolder lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }


    public NewsItem getTitle() {
        return title;
    }

    public void setTitle(NewsItem title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
