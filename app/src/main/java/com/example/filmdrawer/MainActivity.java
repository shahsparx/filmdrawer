package com.example.filmdrawer;

import controllers.DatabaseController;
import fragments.AboutFragment;
import fragments.AddFilmFragment;
import fragments.DashboardFragment;
import fragments.FilmsCollectionFragment;
import sql.DatabaseManager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DatabaseManager dbManager;
    private DrawerLayout drawer;
	private ListView leftDrawer;
    private FrameLayout contentFrame;
    private ArrayAdapter<String> menuAdapter;
    //
    public FragmentManager fManager = getFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        setUpElements();

        setUpMenu();

		setUpDB();
//		alertMovies();
	}

    public void openFragment(Fragment fragment){
        fManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
        drawer.closeDrawer(leftDrawer);
    }

    private void setUpMenu() {
        String[] menuItems = new String[] {
                "Dashboard",
                "Lista filmów",
                "Dodaj film",
                "About"
        };
        menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
        leftDrawer.setAdapter(menuAdapter);
        /*
            STYLE
         */


        /*
            OPEN AT STARTUP
         */

        openFragment(new DashboardFragment());
        leftDrawer.setSelection(0);

        /*
            ON CLICK binding
         */

        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }

            private void selectItem(int pos){
                Fragment fragment;

                switch (pos){
                    case 0: //dashboard
                        fragment = new DashboardFragment();
                        break;
                    case 1: //select film
                        fragment = new FilmsCollectionFragment();
                        break;
                    case 2: //about
                        fragment = new AddFilmFragment();
                        break;
                    case 3: //about
                        fragment = new AboutFragment();
                        break;
                    default:    //default - dashboard
                        fragment = new DashboardFragment();
                        break;
                }

                openFragment(fragment);
                leftDrawer.setItemChecked(pos, true);

            }
        });
    }

    private void setUpElements() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (ListView) findViewById(R.id.left_drawer);
        contentFrame = (FrameLayout) findViewById(R.id.content_frame);
    }

    private void alertMovies() {
		Cursor c = dbManager.getAllMovies();
		while(c.moveToNext()){
			String filmInfo = "";
			for(int i=0; i< c.getColumnCount(); i++){
				filmInfo += c.getColumnName(i)+": "+c.getString(i)+"\n";
			}
			alert(filmInfo);
		}
	}

	private void setUpDB() {
		dbManager = new DatabaseManager (MainActivity.this, "nazwa bazy", null, 1);
	}

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public DatabaseManager getDbManager(){
        return dbManager;
    }
	
	private void alert(String text){
		Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
	}

}
