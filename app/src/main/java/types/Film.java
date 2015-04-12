package types;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by klaus on 23.03.15.
 */
public class Film {
    public String title, released, genre, director, writer, country, awards, poster, rate, actors, plot;
    public int year, duration;
    public int id;

    public Film(int id,
                String title,
                String released,
                String genre,
                String director,
                String writer,
                String country,
                String awards,
                String poster,
                String rate,
                String actors,
                String plot,
                int year,
                int duration){
        this.id = id;
        this.released = released;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.rate = rate;
        this.actors = actors;
        this.plot = plot;
        this.year = year;
        this. duration = duration;
    }

    public Film(String jsonString) {
        try {
            JSONObject jO = new JSONObject(jsonString);
            this.title = jO.getString("Title");
            this.released = jO.getString("Released");
            this.genre = jO.getString("Genre");
            this.director = jO.getString("Director");
            this.writer = jO.getString("Writer");
            this.country = jO.getString("Country");
            this.awards = jO.getString("Awards");
            this.poster = jO.getString("Poster");
            this.rate = jO.getString("Rated");
            this.actors = jO.getString("Actors");
            this.plot = jO.getString("Plot");
            this.year = jO.getInt("Year");

            // zamiana 121 min na inta
            String dur = jO.getString("Runtime");
            Pattern p = Pattern.compile("[0-9]");
            Matcher m =p.matcher(dur);
            while(m.find()){
                dur = m.group();
            }

            this.duration = Integer.parseInt(dur);
        } catch (JSONException e) {
            Log.e("elo", e.toString());
            e.printStackTrace();
        }
    }

    public String toString(){
        return this.title+" ("+this.year+")";
    }

    public int getId(){
        return this.id;
    }
}
