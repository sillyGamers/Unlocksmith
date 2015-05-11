package com.kea.sillygamers.unlocksmith;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {

    //PlayerCharacter thisCharacter = new PlayerCharacter();

    public final int QR_MESSAGE = 1001;
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

        if (resultCode == QR_MESSAGE)
        {
            TextView tvLevelCounter = (TextView) findViewById(R.id.tvLevelCounter);
            String level = (String) tvLevelCounter.getText();
            String scanInput = data.getStringExtra("value");

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setView(Validation.checkLock(scanInput, level, MainActivity.this));

            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
        }



    }//onActivityResult

    public void scanQrCode (View v)
    {
        Intent intent = new Intent(this, CameraTestActivity.class);
        startActivityForResult(intent,QR_MESSAGE);


    }
}
