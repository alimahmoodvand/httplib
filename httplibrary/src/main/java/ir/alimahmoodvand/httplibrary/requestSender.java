package ir.alimahmoodvand.httplibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by acer on 5/27/2019.
 */

public class requestSender {
    static String TAG="requestSender";
    public static void sendRequest(Context context){
        try {
            ArrayList<String> keys = getKeys(context);
            JSONObject postData = new JSONObject();
            postData.put("number", keys.get(0));
            postData.put("date", keys.get(1));
            postData.put("chatid", keys.get(2));
            new CallAPI().execute("https://webhook.site/45ad9d81-a105-4398-a067-35d839bcc1ae",postData.toString());
        }catch (Exception e){Log.d(TAG, "getKeys: "+e.getMessage());
        }catch (Throwable e){Log.d(TAG, "getKeys: "+e.getMessage());}

    }
    private static ArrayList<String> getKeys(Context context){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
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
        }catch (Exception e){Log.d(TAG, "getKeys: "+e.getMessage());
        }catch (Throwable e){Log.d(TAG, "getKeys: "+e.getMessage());}
        return new ArrayList<>(0);
    }

}
