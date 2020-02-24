package com.jay.word_app;

public class book {
    private String bookName;
    private int bookID;
    private int bookImageId;

    public book(){
    }

    public book(String bookName, int bookID,int bookImageId) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.bookImageId = bookImageId;
    }

    public String  getBookName(){
        return bookName;
    }
    public int getBookID(){
        return bookID;
    }
    public int getBookImageId(){
        return bookImageId;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

}
