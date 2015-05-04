package com.kea.sillygamers.unlocksmith;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View; //the import for the onClick listeners. allows us to use items on screen
import android.widget.TextView;

import com.kea.sillygamers.unlocksmith.Model.PlayerCharacter;


public class MainActivity extends ActionBarActivity {

    PlayerCharacter thisCharacter = new PlayerCharacter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        //Passes the players current skill level to the next activity
        intent.putExtra("Locksmith", thisCharacter.getPlayerLockSmithSkillLevel());

        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(resultCode == RESULT_OK){

                TextView tvLevelCounter = (TextView) findViewById(R.id.tvLevelCounter);
                String newLevel = data.getStringExtra("locksmith");

                tvLevelCounter.setText(newLevel);
                thisCharacter.setPlayerLockSmithSkillLevel(newLevel);
            }
    }//onActivityResult

    public void scanQrCode (View v)
    {
        //starts up scanQR code thing
    }

}
