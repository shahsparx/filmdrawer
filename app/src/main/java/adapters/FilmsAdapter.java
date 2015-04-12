package adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import types.Film;

/**
 * Created by klaus on 24.03.15.
 */
public class FilmsAdapter extends BaseAdapter implements Filterable{

    public static ArrayList<Film> temporaryList;
    public static ArrayList<Film> originalList;
    private Activity activity;

    public FilmsAdapter(Activity activity, ArrayList<Film> films) {
        super();
        this.activity=activity;
        this.originalList = films;
        temporaryList=originalList;

    }

    @Override
    public int getCount() {
        return temporaryList.size();
    }

    @Override
    public Film getItem(int position) {
        return temporaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(activity);
        tv.setText(getItem(position).toString());
        tv.setTextSize(20);

        tv.setLayoutParams(
                new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        return tv;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                temporaryList=(ArrayList<Film>)results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Film> FilteredList= new ArrayList<Film>();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = originalList;
                    results.count = originalList.size();
                }
                else {
                    for (int i = 0; i < originalList.size(); i++) {
                        Film data = originalList.get(i);
                        if (data.toString().toLowerCase().contains(constraint.toString()))  {
                            FilteredList.add(data);
                        }
                    }
                    results.values = FilteredList;
                    results.count = FilteredList.size();
                }
                return results;
            }
        };
        return filter;
    }
}
