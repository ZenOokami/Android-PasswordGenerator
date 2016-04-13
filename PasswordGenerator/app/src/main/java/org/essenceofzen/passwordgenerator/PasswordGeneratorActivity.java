/* Designed to be an open project to help create a base for future app development.
*  For support in documentation of this project please contact:
*       Support@EssenceOfZen.org
*
*  This project will remain to be open for public use.
*  If you happened to enjoy or gain use from this project, please consider supporting EoZ via
*  Http://EssenceOfZen.org/ and our YouTube Channel, Http://Youtube.com/EssenceOfZen/
*
*  This project will also be open for discussion at the forums, http://forum.EssenceOfZen.org/
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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PasswordGeneratorActivity extends AppCompatActivity {

    DrawerLayout navigationDrawerLayout; //    For the hamburger menu icon
    ActionBarDrawerToggle actionBarDrawerToggle;

    public String getDate(){
        // Calendar Date stuff
        Calendar date = Calendar.getInstance();
        Log.i("DATE", String.valueOf(date.getTime())); // Log the data for what's being pumped out into the program

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatted_date = date_format.format(date.getTime());
        // Date and time
        Toast.makeText(this, formatted_date, Toast.LENGTH_SHORT).show();

        return formatted_date;
    }

    public String getLength(EditText length_input){
        String length = length_input.getText().toString();

        Toast.makeText(this, length, Toast.LENGTH_LONG).show();
        Log.i("LENGTH", length);

        return length;
    }

    public String convertDateType(String date){
        // Should export 6 variables: year, month, day, hour, minute, second
        // remember the format is yyyy-mm-dd hh:mm:ss
        //                        0123456789012345678
        //String year = String.valueOf(date.charAt(0)+date.charAt(1)+date.charAt(2)+date.charAt(3)+date.charAt(4)); DON'T USE THIS, THIS WAS WRONG - KEEPING AS REFERENCE FOR NOTES!

        String year = date.substring(0,4); // First is inclusive, last is not. This grabs index 0,1,2, and 3
        //Toast.makeText(this, year, Toast.LENGTH_SHORT).show(); // For testing purposes

        String month= date.substring(5,7);
        // We need to convert the month into text now
        switch(month){ // I could organize this on shorter lines, but I like to keep clarity
            case "01":
                month = "Janurary";
                break;
            case "02":
                month = "Februrary";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
        }

        String day = date.substring(8, 10);
        String hour = date.substring(11, 13);
        String minute = date.substring(14, 16);
        String second = date.substring(17, 19);

        String seed_date =  year+month+day+hour+minute+second;

        Toast.makeText(this, seed_date, Toast.LENGTH_SHORT).show();

        return seed_date;
    }

    // Let's generate some passwords, yeah?
    public String generatePassword(String seed_date){
        String new_password = "";

        String key = "9857516874AByaxijasdfIOJASDiojasdoij8778412989asdfn8912ho87y8123jkashdfzxc8192as;odifj91289o9a8usd9fjo9zuxcjo891j23liniasdfrlguih0asdf0129or8giausdfrgn";

        for(int index=0;index < key.length();index++){
            int seed_date_value = ((int)seed_date.charAt(index % seed_date.length()));
            int key_value = ((int)key.charAt(index % key.length()));

            int combined = ((seed_date_value+key_value) % 123);

            char encoded_value = (char)combined;
            new_password += encoded_value;
        }
        // Let's remove all esacpe, null, space, newline, nextline etc.
        new_password = new_password.replaceAll("(\\r|\\n|\\s|[\\u0000-\\u001f])",""); // this uses regex

        return new_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { // OnCreate is exactly that, on creation.
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


        //Twitter Image Button
        final ImageView twitterHex = (ImageView) findViewById(R.id.twitterHex);
        twitterHex.setOnClickListener(new View.OnClickListener(){
            public void onClick(View this_view){
                // Show snackbar for the twitter
                Snackbar.make(this_view, "Follow @EssenceOfZen for development on this project",
                        Snackbar.LENGTH_LONG).show();
            }
        });


        //Toggle buttons
        final ToggleButton lowercase_toggleButton = (ToggleButton) findViewById(R.id.lowercase_toggleButton);
        final ToggleButton uppercase_toggleButton = (ToggleButton) findViewById(R.id.uppercase_toggleButton);
        final ToggleButton numbers_toggleButton = (ToggleButton) findViewById(R.id.numbers_toggleButton);
        final ToggleButton special_toggleButton = (ToggleButton) findViewById(R.id.lowercase_toggleButton);


        //EditText
        final EditText length_input = (EditText) findViewById(R.id.length_input);

        // Generate Password button *******
        Button generatePassword = (Button) findViewById(R.id.generatePassword_button);
        final TextView generatedPassword = (TextView) findViewById(R.id.generatedPassword);
        generatePassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View this_view){
                // Our action
                String date = getDate();
                Log.i("GOTdate","Successfully grabbed the date.");
                // The date is going to be in the format of yyyy-mm-dd hh-mm-ss
                // What we want to do now is take this, and format it where the spaces and dashes are gone
                // and we turn mm into an actual month

                if(lowercase_toggleButton.isChecked()==false && uppercase_toggleButton.isChecked()==false
                        && numbers_toggleButton.isChecked()==false && special_toggleButton.isChecked()==false){ // Can simplify the check with !lowercase_toggleButton.isChecked()
                    Snackbar.make(this_view, "Please select at least one condition!", Snackbar.LENGTH_LONG).show();

                }else{
                    String seed_date = convertDateType(date);
                    //Snackbar.make(this_view, seed_date, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    String length = getLength(length_input); // Pull down the length as a string

                    // Now we're going to want to take that length and set up another check
                    //todo: Fix the empty length generator
                    int length_number = Integer.parseInt(length);
                    if(length.matches("") || length_number<5 || length_number>10 ){
                        Snackbar.make(this_view, "Please input a valid length: 5 to 10", Snackbar.LENGTH_LONG).show();
                    }else{
                        // So we have our Length, we have our seed
                        // Next step calls for us to call our encryption/generation function followed by
                        // checking each character in the results and removing any null, nextLine, or spaces

                        String password = generatePassword(seed_date); // We have our full password source
                        String final_password = "";
                        Log.i("VALIDlength","Valid length foudnd.");

                        // Now we have to build our last password from the user selection choice:
                        // replaceAll uses Regex
                        if(lowercase_toggleButton.isChecked()== false){
                            // If lowercase is NOT checked: Remove all lowercase characters
                            password = password.replaceAll("[a-z]","");
                        }
                        if(uppercase_toggleButton.isChecked()==false){
                            // If uppercase is NOT checked: Remove all uppercase characters
                            password = password.replaceAll("[A-Z]","");
                        }
                        if(numbers_toggleButton.isChecked()==false){
                            // If numbers is NOT checked: Remove all numbers
                            password = password.replaceAll("[0-9]","");
                        }
                        if(special_toggleButton.isChecked()==false){
                            // If specials is NOT checked: Remove all special characters
                            password = password.replaceAll("[\\u0021-\\u002F]",""); // unicode
                        }

                        // Now, we have to check to see how long our password can be
                        for(int index=0; index < length_number; index++ ){
                            final_password += password.charAt(index);
                        }

                        // Set the password text to the new password
                        generatedPassword.setText(final_password);
                    }
                }
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
