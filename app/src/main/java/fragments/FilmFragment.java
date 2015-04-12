package fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmdrawer.MainActivity;
import com.example.filmdrawer.R;

import controllers.DatabaseController;
import types.Film;

/**
 * Created by klaus on 23.03.15.
 */
public class FilmFragment extends Fragment {

    private long id;
    private Film film;
    private MainActivity activity;

    private TextView tvFilm, tvGenre, tvDirector, tvWriter, tvActors, tvCountry, tvPlot, tvAwards;

    public FilmFragment(long id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity)getActivity();


        film = DatabaseController.getFilm(activity.getDbManager(), id);

        return inflater.inflate(R.layout.film_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpObjects();

        if(film!=null)
            showData();
    }

    private void setUpObjects() {
        tvFilm = (TextView)getView().findViewById(R.id.tvFilm);
        tvGenre = (TextView)getView().findViewById(R.id.tvGenre);
        tvDirector = (TextView)getView().findViewById(R.id.tvDirector);
        tvWriter = (TextView)getView().findViewById(R.id.tvWriter);
        tvActors = (TextView)getView().findViewById(R.id.tvActors);
        tvCountry = (TextView)getView().findViewById(R.id.tvCountry);
        tvPlot = (TextView)getView().findViewById(R.id.tvPlot);
        tvAwards = (TextView)getView().findViewById(R.id.tvAwards);
    }

    private void showData() {
        //TODO: show film data
        String rate = "";   //w sumie jakieś ruskie wyniki, więc rezygnuję
        tvFilm.setText(film.toString()+""+rate);
        tvGenre.setText(film.genre);
        tvDirector.setText(film.director);
        tvWriter.setText(film.writer);
        tvActors.setText(film.actors);
        tvCountry.setText(film.country);
        tvPlot.setText(film.plot);
        tvAwards.setText(film.awards);
    }

    private void alert(String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
