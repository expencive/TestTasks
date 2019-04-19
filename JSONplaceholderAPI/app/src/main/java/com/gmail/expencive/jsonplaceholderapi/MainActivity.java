package com.gmail.expencive.jsonplaceholderapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.gmail.expencive.jsonplaceholderapi.UserListRecyclerView.UserListAdapter_add;
import com.gmail.expencive.jsonplaceholderapi.UserListRecyclerView.UserListItem;

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


public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private UserListAdapter_add mUserListAdapter;
    private ArrayList<UserListItem> mUserList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        NetworkUtils process = new NetworkUtils();
        process.execute();


    }

    public class NetworkUtils extends AsyncTask<Void, Void, Void> {

        String data = "";
        String userName = "";
        String userId = "";



        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/users");

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
                    String name = userInfo.getString("name");
                    String id = userInfo.getString("id");
                    mUserList.add(new UserListItem(name, id));

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

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            mUserListAdapter = new UserListAdapter_add(mUserList, MainActivity.this);
            mRecyclerView.setAdapter(mUserListAdapter);



        }


    }









}
