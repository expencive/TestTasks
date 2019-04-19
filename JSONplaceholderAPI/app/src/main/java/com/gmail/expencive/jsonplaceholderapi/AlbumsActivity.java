package com.gmail.expencive.jsonplaceholderapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import android.widget.ProgressBar;


import com.gmail.expencive.jsonplaceholderapi.PhotosListRecyclerView.PhotosListAdapter;
import com.gmail.expencive.jsonplaceholderapi.PhotosListRecyclerView.PhotosListItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;


public class AlbumsActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private PhotosListAdapter mPhotosListtAdapter;
    private ArrayList<Integer> mUserList = new ArrayList<>();
    private ArrayList<PhotosListItem> mPhotoList = new ArrayList<>();
    private int mGetUserNameId;
    ProgressBar pbLoadIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);


        pbLoadIndicator = (ProgressBar) findViewById(R.id.pbLoadIndicator);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_albums);

        int getUserNameId = Integer.parseInt(getIntent().getStringExtra("intent_userid"));
        mGetUserNameId = getUserNameId;





        NetworkUtilsAlbums process = new NetworkUtilsAlbums();
        process.execute();
    }

    public class NetworkUtilsAlbums extends AsyncTask<Void, Void, Void> {

        String data = "";
        String dataPhotos = "";
        String userName = "";
        String userId = "";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pbLoadIndicator.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... voids) {




            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/albums");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();

                    data = data + line;

                }




                JSONArray jsonArray = new JSONArray(data);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject userInfo = jsonArray.getJSONObject(i);

                    int userId = userInfo.getInt("userId");
                    int albumId = userInfo.getInt("id");
                    String titleAlbums = userInfo.getString("title");

                    if (userId == mGetUserNameId) {


                        mUserList.add(albumId);

                    }


                }

                URL urlPhotos = new URL("https://jsonplaceholder.typicode.com/photos");
                HttpURLConnection httpURLConnectionPhotos = (HttpURLConnection) urlPhotos.openConnection();
                httpURLConnectionPhotos.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) httpURLConnectionPhotos.getContent()));

                //Без использования библиотеки - этот код выполняется около пяти минут. Возможно есть другой способ. Но я ничего толкового
                //найти не смог, поэтому использовал для этого куска библиотеку GSON. Использовал ее в качестве костыля


                //InputStream inputStreamPhotos = httpURLConnectionPhotos.getInputStream();
                //BufferedReader bufferedReaderPhotos = new BufferedReader(new InputStreamReader(inputStreamPhotos));
                //String linePhotos = "";
                //while (linePhotos != null) {
                    //linePhotos = bufferedReaderPhotos.readLine();

                    //dataPhotos = dataPhotos + linePhotos;
                //}




                JsonArray jsonArrayPhotosss = root.getAsJsonArray();

                dataPhotos = jsonArrayPhotosss.toString();

                JSONArray jsonArrayPhotos = new JSONArray(dataPhotos);



                   for (int j = 0; j<mUserList.size(); j++) {

                       for (int i = 0; i < jsonArrayPhotos.length(); i++) {
                           JSONObject photoInfo = jsonArrayPhotos.getJSONObject(i);

                           int albumId = photoInfo.getInt("albumId");

                           int photoId = photoInfo.getInt("id");

                           String title = photoInfo.getString("title");

                           String imageUrl = photoInfo.getString("url");

                           if (albumId == mUserList.get(j)) {
                               mPhotoList.add(new PhotosListItem(imageUrl, title, photoId));
                           }


                       }

                   }







            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pbLoadIndicator.setVisibility(View.INVISIBLE);




            mRecyclerView.setLayoutManager(new LinearLayoutManager(AlbumsActivity.this));



            mRecyclerView.setAdapter(mPhotosListtAdapter);

            mRecyclerView.setHasFixedSize(true);

            mPhotosListtAdapter = new PhotosListAdapter(AlbumsActivity.this, mPhotoList);
            mRecyclerView.setAdapter(mPhotosListtAdapter);








        }

    }





}


