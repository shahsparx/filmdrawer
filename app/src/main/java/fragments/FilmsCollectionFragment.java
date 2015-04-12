package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.filmdrawer.MainActivity;
import com.example.filmdrawer.R;

import java.util.ArrayList;

import adapters.FilmsAdapter;
import controllers.DatabaseController;
import types.Film;

/**
 * Created by klaus on 23.03.15.
 */
public class FilmsCollectionFragment extends Fragment{

    private MainActivity activity;
    private EditText filterInput;
    private ListView filmsDrawer;
    private FilmsAdapter adapter;
    private ArrayList<Film> films;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.film_select_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpObjects();
        setListeners();
        //
        gatherFilmsList();
    }

    private void gatherFilmsList() {
        films = DatabaseController.getAllFilms(activity.getDbManager());
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, films);
        adapter = new FilmsAdapter(getActivity(), films);
        filmsDrawer.setAdapter(adapter);
    }

    private void setListeners() {
        filterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                adapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        filmsDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openFilm(id);
            }
        });
    }

    private void openFilm(long id) {
        Fragment fragment = new FilmFragment(id);
        activity.openFragment(fragment);
    }

    private void setUpObjects() {
        filterInput = (EditText)getView().findViewById(R.id.filmFilter);
        filmsDrawer = (ListView)getView().findViewById(R.id.filmsList);
    }

    private void alert(String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
