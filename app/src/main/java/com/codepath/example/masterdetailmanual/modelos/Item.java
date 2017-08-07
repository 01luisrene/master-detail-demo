package com.codepath.example.masterdetailmanual.modelos;

import android.os.Parcel;
import android.os.Parcelable;


public class Item implements Parcelable {

	private static final long serialVersionUID = -6099312954099962806L;

    private String id;
    private String title;
    private String body;

    public Item(){}

    public Item(String id, String titulo, String body) {
        this.id = id;
        this.title = titulo;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.body);
    }

    protected Item(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.body = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
