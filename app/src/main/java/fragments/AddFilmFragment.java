package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.filmdrawer.MainActivity;
import com.example.filmdrawer.R;

import controllers.APIController;

/**
 * Created by klaus on 23.03.15.
 */
public class AddFilmFragment extends Fragment {

    private EditText filmTitle;
    private Button filmSeekButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.film_add_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpObjects();
        setListeners();
    }

    private void setListeners() {
        filmSeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findMovie(filmTitle.getText().toString());
            }
        });
    }

    private void findMovie(String s) {
        APIController.downloadMovie(s,
                ((MainActivity)getActivity()).getDbManager(),
                getActivity().getApplicationContext());
    }


    private void setUpObjects() {
        filmTitle = (EditText)getView().findViewById(R.id.filmTitle);
        filmSeekButton = (Button)getView().findViewById(R.id.filmSeekButton);
    }

}
