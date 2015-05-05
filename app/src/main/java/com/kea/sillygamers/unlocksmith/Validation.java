package com.kea.sillygamers.unlocksmith;

import android.widget.TextView;

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
    public static String checkLock(String scanInput, String userLevel)
    {
        String lock;
        int userLevelInt = Integer.parseInt(userLevel);
        int scanInputInt = Integer.parseInt(scanInput);

        if(isNumber(scanInput) == false || scanInputInt < 0){
            return "not a lock";
        }
        else if(scanInputInt > userLevelInt){

            return "door locked";
        }
        else if(scanInputInt < userLevelInt)
        {
            return "door unlocked";
        }
        else{
            return "I'm sorry master, I'm not sure what this is";
        }

    }

}
