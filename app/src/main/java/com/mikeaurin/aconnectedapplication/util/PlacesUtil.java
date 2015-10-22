package com.mikeaurin.aconnectedapplication.util;


import android.util.Log;

import com.mikeaurin.aconnectedapplication.data.Places;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class PlacesUtil {

    private static final String FEED_URL ="https://api.foursquare.com/v2/venues/search?client_id=ZZB3MEONLFA5ZOSLIEQ13ERKQMHOHPGPFH4QYKKGAZLY4IWC&client_secret=CTFBKXM14JXA1J1CUY4MUUL2LWXKES3VL11D1MC5XBIXZFWA&v=20130815&near=orlando,FL&query=sushi";

    private static final String TAG = PlacesUtil.class.getSimpleName();

    public static String getPlacesFeedData() {

        try {
            URL url = new URL(FEED_URL);

            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.connect();

            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);

           Log.d(TAG, String.valueOf(connection.getResponseCode()));

            is.close();

            connection.disconnect();

            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Places> parsePlacesFromFeed(String _feedData) {

        ArrayList<Places> places = new ArrayList<>();

        try {

            JSONObject first = new JSONObject(_feedData);
            JSONObject outter = first.getJSONObject("meta");
            JSONObject inner = outter.getJSONObject("response");
            JSONArray placesArr = inner.getJSONArray("venues");
//            JSONObject obj = new JSONObject(_feedData);
//            JSONArray placesArr = obj.getJSONArray("meta");

            for(int i = 0; i <placesArr.length(); i++) {
                JSONObject placesObj = placesArr.getJSONObject(i);
                String name = placesObj.getString("name");
                String id = placesObj.getString("shortName");

                places.add(new Places(name, id));
            }

        } catch(JSONException e){
            e.printStackTrace();
        }
        return places;
    }
}
