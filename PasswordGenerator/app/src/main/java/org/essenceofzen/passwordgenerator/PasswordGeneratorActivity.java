/* Designed to be an open project to help create a base for future app development.
*  For support in documentation of this project please contact:
*       Support@EssenceOfZen.org
*
*
* */
package org.essenceofzen.passwordgenerator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class PasswordGeneratorActivity extends AppCompatActivity {

    DrawerLayout navigationDrawerLayout; //    For the hamburger menu icon
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabMainAction);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Clearing Field Complete.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //For the Navigation drawer ~~~~~~
        navigationDrawerLayout = (DrawerLayout)findViewById(R.id.navigation_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        //todo: Try and and get the animated "DrawerArrowDrawableToggle" working and replace "actionBarDrawerToggle"

        navigationDrawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    // Also for hamburger icon
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_generator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
