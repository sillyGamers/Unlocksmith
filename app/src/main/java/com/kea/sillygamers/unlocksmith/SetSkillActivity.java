package com.kea.sillygamers.unlocksmith;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SetSkillActivity extends ActionBarActivity {

    public String skillLevelCount;
    TextView skillCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_skill);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        skillLevelCount = sharedPreferences.getString("locksmithLevel", "0");

        skillCounter = (TextView) findViewById(R.id.tvSkillCounter);
        skillCounter.setText(skillLevelCount);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_skill, menu);
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

    public void newSkillLevel(View v){
    try {


        /*
        At first I used to send the informations inputtet via an intent, back to the main activity.
        But I had some design troubles with initializing storing the data for long term storage.
        So now I am changing the approach of sending the skill level from activity to activity,
        I will try to do it with sharedPreferences, and make sure, that the informations is
        stored and available at all times, even if the app has been closed for a prolonged time.
         */

        EditText newSkillLevel = (EditText) findViewById(R.id.tfNewSkillLevel);
        skillLevelCount = newSkillLevel.getText().toString();

        TextView skillCounter = (TextView) findViewById(R.id.tvSkillCounter);
        skillCounter.setText(skillLevelCount);

        //Sets sharedPreferences "MyData" file, finds value "locksmithLevel"
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("locksmithLevel", skillLevelCount);

        editor.commit();

        //returnIntent.putExtra("locksmith", skillLevelCount);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }catch (Exception e){
        e.printStackTrace();
        }
    }
}
