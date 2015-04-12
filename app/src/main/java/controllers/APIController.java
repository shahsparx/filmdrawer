package controllers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import sql.DatabaseManager;
import types.Film;

/**
 * Created by klaus on 23.03.15.
 */
public class APIController {

    private static String URI = "http://www.omdbapi.com/?y=&plot=short&r=json&t=";
    private static Context context;
    private static JSONArray jarr;
    private static DatabaseManager dbManager;

    public APIController(){
        // constructor
    }

    public static String downloadMovie(String title, DatabaseManager dbManager, Context context){
        APIController.context = context;
        APIController.dbManager = dbManager;
        new AsyncFilmGetter().execute(title);
        return null;
    }

    private static class AsyncFilmGetter extends AsyncTask<String, Void, Film>{

        @Override
        protected Film doInBackground(String... params) {

            String test = "";
            for(String s: params){
                test+=s;
            }

            test = Uri.encode(test);

            JSONObject jsonObj = new JSONObject();

            try {
                HttpGet httpPost = new HttpGet(URI+test);
                Log.d("elo",URI+test);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpResponse httpResponse = null;
                httpResponse = httpClient.execute(httpPost);
                String jsonString = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
                //jesli jsonString nie jest pusty wtedy parsuje go na obiekt JSON
                Log.d("elo","content: "+jsonString);
//                jsonObj = new JSONObject(jsonString);
                return new Film(jsonString);
            } catch (Exception e) {
                Log.e("elo",e.toString());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Film film) {
            if(film!=null && film.year>0) {
                DatabaseController.addMovie(dbManager, film);
                alert("\"" + film.toString() + "\" film dodano");
            }else{
                alert("nie znaleziono");
            }
        }
    }

    private static void alert(String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
