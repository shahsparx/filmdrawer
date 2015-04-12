package controllers;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import sql.DatabaseManager;
import types.Film;

/**
 * Created by klaus on 23.03.15.
 */
public class DatabaseController {

    public DatabaseController(){
        //constructor
    }

    public static void addMovie(DatabaseManager dbManager, Film film){
        if(film.title!="null")
            dbManager.addMovie(
                    film.title,
                    film.year,
                    film.released,
                    film.duration,
                    film.genre,
                    film.director,
                    film.writer,
                    film.actors,
                    film.plot,
                    film.country,
                    film.awards,
                    film.poster,
                    film.rate
            );
    }

    public static ArrayList<Film> getAllFilms(DatabaseManager dbManager){
        ArrayList<Film> films = new ArrayList<Film>();

        Cursor c = dbManager.getAllMovies();
        while(c.moveToNext()){
            Film film = new Film(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("released")),
                    c.getString(c.getColumnIndex("genre")),
                    c.getString(c.getColumnIndex("director")),
                    c.getString(c.getColumnIndex("writer")),
                    c.getString(c.getColumnIndex("country")),
                    c.getString(c.getColumnIndex("awards")),
                    c.getString(c.getColumnIndex("poster")),
                    c.getString(c.getColumnIndex("rate")),
                    c.getString(c.getColumnIndex("actors")),
                    c.getString(c.getColumnIndex("plot")),
                    c.getInt(c.getColumnIndex("year")),
                    c.getInt(c.getColumnIndex("duration"))
            );
            films.add(film);
        }
        return films;
    }

    public static ArrayList<String> getFilmTitles(DatabaseManager dbManager){
        ArrayList<Film> films = DatabaseController.getAllFilms(dbManager);
        ArrayList<String> titles = new ArrayList<String>();
        for(Film film: films){
            titles.add(film.toString());
        }
        return titles;
    }

    public static Film getFilm(DatabaseManager dbManager, long id) {
        Cursor c = dbManager.getFilm(id);

        Film film = null;

        while(c.moveToNext()) {
            film = new Film(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("released")),
                    c.getString(c.getColumnIndex("genre")),
                    c.getString(c.getColumnIndex("director")),
                    c.getString(c.getColumnIndex("writer")),
                    c.getString(c.getColumnIndex("country")),
                    c.getString(c.getColumnIndex("awards")),
                    c.getString(c.getColumnIndex("poster")),
                    c.getString(c.getColumnIndex("rate")),
                    c.getString(c.getColumnIndex("actors")),
                    c.getString(c.getColumnIndex("plot")),
                    c.getInt(c.getColumnIndex("year")),
                    c.getInt(c.getColumnIndex("duration"))
            );
        }
        return film;
    }

    public static int getCountFilms(DatabaseManager dbManager){
        int count=0;

        Cursor c = dbManager.getCountFilms();
        while(c.moveToNext()){
            count = c.getInt(0);
        }

        return count;
    }

    public static Film getRandomFilm(DatabaseManager dbManager){
        Film film = null;

        Cursor c = dbManager.getRandomFilm();

        while(c.moveToNext()) {
            film = new Film(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("released")),
                    c.getString(c.getColumnIndex("genre")),
                    c.getString(c.getColumnIndex("director")),
                    c.getString(c.getColumnIndex("writer")),
                    c.getString(c.getColumnIndex("country")),
                    c.getString(c.getColumnIndex("awards")),
                    c.getString(c.getColumnIndex("poster")),
                    c.getString(c.getColumnIndex("rate")),
                    c.getString(c.getColumnIndex("actors")),
                    c.getString(c.getColumnIndex("plot")),
                    c.getInt(c.getColumnIndex("year")),
                    c.getInt(c.getColumnIndex("duration"))
            );
        }
        return film;
    }
}
