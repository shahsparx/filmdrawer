package fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmdrawer.MainActivity;
import com.example.filmdrawer.R;

import controllers.DatabaseController;
import types.Film;

/**
 * Created by klaus on 23.03.15.
 */
public class DashboardFragment extends Fragment {

    private MainActivity activity;
    private TextView filmCount, randomFilm;
    private Film randFilm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity)getActivity();
        clearBackStack();

        return inflater.inflate(R.layout.dashboard_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initObjects();
        initElements();
        setUpElements();
        initListeners();

    }

    private void initObjects() {
        randFilm = DatabaseController.getRandomFilm(activity.getDbManager());
    }

    private void initListeners() {
        randomFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openFragment(
                        new FilmFragment(randFilm.getId())
                );
            }
        });
    }

    private void setUpElements() {
        filmCount.setText(DatabaseController.getCountFilms(activity.getDbManager())+"");
        if(randFilm!=null)
            randomFilm.setText(randFilm.toString());
        else
            randomFilm.setText("");
    }

    private void initElements() {
        filmCount = (TextView) getView().findViewById(R.id.filmsCountTextView);
        randomFilm = (TextView) getView().findViewById(R.id.randomFilmTextView);
    }

    private void clearBackStack() {
        //TODO: wychodzenie w dashboard
        FragmentManager fm = activity.fManager;
        for(int i = 1; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }


}
