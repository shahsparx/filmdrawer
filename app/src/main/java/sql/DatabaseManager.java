package sql;

import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    
	private SQLiteDatabase db;

	public DatabaseManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
        //
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL("CREATE TABLE IF NOT EXISTS movies ("
	    		+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
	    		+ "title VARCHAR(128), "
	    		+ "year NUMBER, "
	    		+ "released VARCHAR(16), "
	    		+ "duration NUMBER, "
	    		+ "genre VARCHAR(128), "
	    		+ "director VARCHAR(128), "
	    		+ "writer VARCHAR(256), "
	    		+ "actors TEXT, "
	    		+ "plot TEXT, "
	    		+ "country VARCHAR(128), "
	    		+ "awards VARCHAR(128), "
	    		+ "poster VARCHAR(512), "
	    		+ "rate VARCHAR(16) "
	    		+ ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
		
	}
	
	public void deleteMovie(int id){
		db = this.getWritableDatabase();
		db.execSQL("DELETE FROM movies WHERE id = "+id);
	}
	
	public void addMovie(String title, int year, String released, int duration, String genre, String director, String writer, String actors, String plot, String country, String awards, String poster, String rate ){
		db = this.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put("title", title);
		content.put("year", year);
		content.put("released", released);
		content.put("duration", duration);
		content.put("genre", genre);
		content.put("director", director);
		content.put("writer", writer);
		content.put("actors", actors);
		content.put("plot", plot);
		content.put("country", country);
		content.put("awards", awards);
		content.put("poster", poster);
		content.put("rate", rate);
		db.insert("movies", null, content);
		db.close();
	}
	
	public Cursor getFilm(long id){
	    db = getReadableDatabase();
	    //zapytanie 

	    String sql = "SELECT * FROM movies WHERE id = "+id;
	    Cursor cursor = db.rawQuery(sql, null);        

	    return cursor;
	}
	
	public Cursor getAllMovies(){
	    db = getReadableDatabase();
	    //zapytanie 

	    String sql = "SELECT * FROM movies ORDER BY title ASC";
	    Cursor cursor = db.rawQuery(sql, null);        

	    return cursor;
	}

    public Cursor getCountFilms(){
        db = getReadableDatabase();
        //zapytanie

        String sql = "SELECT count(*) as count FROM movies";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }


    public Cursor getRandomFilm(){
        db = getReadableDatabase();
        //zapytanie

        String sql = "SELECT * FROM movies ORDER BY RANDOM() LIMIT 1;";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public void insertSampleData(){
		addMovie(
				"tytul",
				2015, 
				"2015-01-01", 
				103, 
				"Comedy", 
				"Sean Connery", 
				"Jakis writer",
				"Jacys Aktorzy, Sean Connery",
				"Cos sie dzieje, duzo akcji, masa strzelania. Ogolnie straszna masakra",
				"Polska", 
				"Chopie oskar",
				"https://placekitten.com/320/40", 
				"10.0"
				);
	}

}
