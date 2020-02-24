package com.jay.word_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyFragment_book extends Fragment {

    private List<book> bookList = new ArrayList<book>();
    private ArrayAdapter<book> arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.book_layout,container,false);

        initBooks();
        ListView ListView_book=view.findViewById(R.id.ListView_book);

        ListView_book.setAdapter(new MyListViewAdapter(getLayoutInflater(),bookList));
        return view;
    }

    private void initBooks() {
        book apple = new book("Apple",1, R.drawable.add);
        bookList.add(apple);
        book banana = new book("Banana", 2,R.drawable.add);
        bookList.add(banana);
        book orange = new book("Orange",3, R.drawable.add);
        bookList.add(orange);
    }
}
