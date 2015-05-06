package com.kea.sillygamers.unlocksmith;

import android.widget.ImageView;

/**
 * Created by Nicholas on 5/5/2015.
 */
public class Validation {

    private static boolean isNumber(String input){

        try{
            Integer.parseInt(input);

        }catch (NumberFormatException nfe){

            return false;
        }
        return true;
    }
    public static ImageView checkLock(String scanInput, String userLevel, MainActivity mainActivity)
    {
        //Maybe return string array. Position 0 would be message, position 1 would be image location
        //or return image view
        int userLevelInt = Integer.parseInt(userLevel);
        int scanInputInt = Integer.parseInt(scanInput);
       ImageView lock = new ImageView(mainActivity);

        if(isNumber(scanInput) == false || scanInputInt < 0){
            lock.setImageResource(R.drawable.whitelock);
            return lock;
        }
        else if(scanInputInt > userLevelInt){
            lock.setImageResource(R.drawable.redlock);
            return lock;
        }
        else if(scanInputInt <= userLevelInt)
        {
            lock.setImageResource(R.drawable.greenlock);
            return lock;
        }
        else{
            lock.setImageResource(R.drawable.whitelock);
            return lock;
        }


    }

}
