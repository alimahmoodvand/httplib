package ir.alimahmoodvand.httplibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by acer on 5/27/2019.
 */

public class requestSender {
    static String TAG="requestSender";
    public static ArrayList<String> getKeys(Context cnxt){
        try {
            ApplicationInfo ai = cnxt.getPackageManager().getApplicationInfo(cnxt.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String number = bundle.getString("number");
            String date = bundle.getString("date");
            String chatid = bundle.getString("chatid");
            ArrayList<String> keys=new ArrayList<>(3);
            keys.add(number);
            keys.add(date);
            keys.add(chatid);
//            Log.d(TAG, "getKeys: "+number);
//            Log.d(TAG, "getKeys: "+date);
//            Log.d(TAG, "getKeys: "+date);
            return keys;
        }catch (Exception e){
            Log.d(TAG, "getKeys: "+e.getMessage());
        }catch (Throwable e){Log.d(TAG, "getKeys: "+e.getMessage());}
        return new ArrayList<>(0);
    }
}
