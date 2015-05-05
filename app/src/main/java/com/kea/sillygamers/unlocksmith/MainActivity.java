package com.kea.sillygamers.unlocksmith;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View; //the import for the onClick listeners. allows us to use items on screen
import android.widget.EditText;
import android.widget.TextView;

import com.kea.sillygamers.unlocksmith.Model.PlayerCharacter;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends ActionBarActivity {

    //PlayerCharacter thisCharacter = new PlayerCharacter();

    TextView locksmithLevel;
    public String skillLevelCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locksmithLevel = (TextView) findViewById(R.id.tvLevelCounter);

        //Sets skill level from sharedPreferences
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        skillLevelCount = sharedPreferences.getString("locksmithLevel", "0");

        locksmithLevel.setText(skillLevelCount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void setSkillLevel (View v)
    {
        Intent intent = new Intent(this, SetSkillActivity.class);

        //Activates activity_set_skill, when a result is approved in activity_set_skill
        //We jump back and run onAtivityResult()
        startActivityForResult(intent, 0);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(resultCode == RESULT_OK){

                TextView tvLevelCounter = (TextView) findViewById(R.id.tvLevelCounter);

                SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
                skillLevelCount = sharedPreferences.getString("locksmithLevel", "0");
                locksmithLevel.setText(skillLevelCount);
            }


    }//onActivityResult

    public void scanQrCode (View v)
    {
        TextView tvLevelCounter = (TextView) findViewById(R.id.tvLevelCounter);
        String level = (String) tvLevelCounter.getText();
        String scanInput = "5";

        if(Validation.checkLock(scanInput, level).equals("not a lock")){

            AlertDialog.Builder aD = new AlertDialog.Builder(MainActivity.this);
            aD.setMessage("not a lock");
            AlertDialog dialog = aD.create();
            dialog.show();
        }
        else if(Validation.checkLock(scanInput, level).equals("door locked")){
            AlertDialog.Builder aD = new AlertDialog.Builder(MainActivity.this);
            aD.setMessage("You may not enter");
            AlertDialog dialog = aD.create();
            dialog.show();
        }
        else if(Validation.checkLock(scanInput, level).equals("door unlocked")){
            AlertDialog.Builder aD = new AlertDialog.Builder(MainActivity.this);
            aD.setMessage("Please enter");
            AlertDialog dialog = aD.create();
            dialog.show();

       }
        else{
            AlertDialog.Builder aD = new AlertDialog.Builder(MainActivity.this);
            aD.setMessage("Not sure what to do here... god speed my friend");
            AlertDialog dialog = aD.create();
            dialog.show();
        }
    }
}
