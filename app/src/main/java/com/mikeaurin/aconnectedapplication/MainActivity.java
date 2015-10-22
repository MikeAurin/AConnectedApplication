package com.mikeaurin.aconnectedapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.mikeaurin.aconnectedapplication.adapter.PlacesAdapter;
import com.mikeaurin.aconnectedapplication.data.Places;
import com.mikeaurin.aconnectedapplication.util.PlacesUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = (Button)findViewById(R.id.enterButton);

        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();


        if(mgr != null) {
            NetworkInfo info = mgr.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
            }

//        if (netInfo.isConnected()) {
//            //Valid Connection
//            Toast.makeText(this, "You are connected!", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "No data connection is available!", Toast.LENGTH_LONG).show();
//        }

            PlacesDataTask task = new PlacesDataTask();
            task.execute();
        }

    }

    private class PlacesDataTask extends AsyncTask<Void, Void, ArrayList<Places>> {

        @Override
        protected ArrayList<Places> doInBackground(Void... voids) {

            String data = PlacesUtil.getPlacesFeedData();
            ArrayList<Places> places = PlacesUtil.parsePlacesFromFeed(data);

            return places;
        }

        @Override
        protected void onPostExecute(ArrayList<Places> places) {
            super.onPostExecute(places);

            ListView lv = (ListView)findViewById(R.id.list_view);
            PlacesAdapter adapter = new PlacesAdapter(MainActivity.this, places);
            lv.setAdapter(adapter);
        }
    }

}
