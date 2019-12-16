package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.thesameskincare.R;
import com.example.thesameskincare.fragment.TrangChu_Fragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    List<Suggestion> msuggestion = new ArrayList<>();
    FloatingSearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        initData();
        searchView = (FloatingSearchView) findViewById(R.id.search_view);

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    searchView.clearSuggestions();
                } else {
                    searchView.showProgress();
                    searchView.swapSuggestions(getSuggestion(newQuery));
                    searchView.hideProgress();
                }
            }
        });
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                searchView.showProgress();
                searchView.swapSuggestions(getSuggestion(searchView.getQuery()));
                searchView.hideProgress();
            }

            @Override
            public void onFocusCleared() {

            }
        });
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Suggestion suggestion = (Suggestion) searchSuggestion;
                Toast.makeText(getApplicationContext(), "Ban vua chon " + suggestion.getmName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Resuilt_Search.class);
                intent.putExtra("search",searchView.getQuery());
                startActivity(intent);
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });
    }

        private List<Suggestion> getSuggestion(String query){
            List<Suggestion> suggestions=new ArrayList<>();
            for(Suggestion suggestion:msuggestion){
                if(suggestion.getmName().toLowerCase().contains(query.toLowerCase())){
                    suggestions.add(suggestion);
                }
            }
            return suggestions;
        }
        public void initData(){
            for (int i = 0; i< TrangChu_Fragment.sanpham.size(); i++){
                msuggestion.add(new Suggestion(TrangChu_Fragment.sanpham.get(i).getTenSanPham()));
            }
        }


}
