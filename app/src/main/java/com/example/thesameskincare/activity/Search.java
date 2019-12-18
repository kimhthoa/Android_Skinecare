package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.db.db_shop;
import com.example.thesameskincare.fragment.TrangChu_Fragment;
import com.example.thesameskincare.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    int mashop1 = 0;
    String tenshop ="";
    String diachi ="";
    String sodienthoai ="";

    List<Suggestion> msuggestion = new ArrayList<>();
    public static ArrayList<db_shop>  shop;
    FloatingSearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        getShop();
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
//            for (int i=0; i<shop.size(); i++){
//                msuggestion.add(new Suggestion(shop.get(i).getTenshop()));
////                Toast.makeText(this, "Shop:"+shop.get(i).getTenshop(), Toast.LENGTH_SHORT).show();
//            }
        }

    public void getShop() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanShop, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    shop = new ArrayList<>();
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            mashop1 = jsonObject.getInt("mashop");
                            tenshop = jsonObject.getString("tenshop");
                            diachi = jsonObject.getString("diachi");
                            sodienthoai = jsonObject.getString("sodienthoai");
                            shop.add(new db_shop(mashop1, tenshop, diachi, sodienthoai));
//                            Toast.makeText(Search.this, "shop:" + shop.get(0).getTenshop(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }



}
