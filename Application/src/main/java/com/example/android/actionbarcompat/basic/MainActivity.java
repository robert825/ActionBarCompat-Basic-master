/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.actionbarcompat.basic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Add any fields you think you need up here.
    boolean serviceOn;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);


        // You may need to add some lines here!

        serviceOn = false;

        Intent intent = getIntent();
        try {
            String[] message = intent.getStringArrayExtra(NewActivity.EXTRA_MESSAGE);
            TextView displayMessage = (TextView) findViewById(R.id.message);
            displayMessage.setText("You entered:\n" + message[0] + "\n" + message[1] + " " + message[2] + "\n\n" +
            "I had trouble making a SnackBar, but here is just a TextView containing the values the user entered.");
        } catch (NullPointerException n) {
            System.out.println("Error");
        }
    }


    /**
     * This method creates the menu in the activity from the menu/main.xml file.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * This method is called when one of the menu items to selected. These items
     * can be on the Action Bar, the overflow menu, or the standard options menu. You
     * should return true if you handle the selection.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                // Use this menu item to alternate starting and stopping the service
                Intent intent1 = new Intent(this, TimeDateService.class);
                if(serviceOn) {
                    stopService(intent1);
                    serviceOn = false;
                }
                else{
                    startService(intent1);
                    serviceOn = true;
                }

                return true;

            case R.id.menu_location:
                // Use this menu item to open a URL in a browser using an Intent
                url = ((EditText) findViewById(R.id.url)).getText().toString();
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent2);

                return true;

            case R.id.menu_settings:
                // Use this menu item to open another activity
                Intent intent3 = new Intent(this, NewActivity.class);
                startActivity(intent3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
