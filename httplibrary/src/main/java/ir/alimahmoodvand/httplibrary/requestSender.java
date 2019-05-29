package ir.alimahmoodvand.httplibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatButton;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by acer on 5/27/2019.
 */

public class requestSender implements  ActivityCompat.OnRequestPermissionsResultCallback{
    static String TAG="requestSender";
    public static void sendRequest(Context context){
        try {
            String url="https://raaz.ir/app.php?";
            ArrayList<String> keys = getKeys(context);
            JSONObject postData = new JSONObject();
            postData.put("code", URLEncoder.encode(keys.get(0), "utf-8"));
            postData.put("date", URLEncoder.encode(keys.get(1), "utf-8"));
            postData.put("chat_id",URLEncoder.encode(keys.get(2), "utf-8"));
            postData.put("appname",URLEncoder.encode(keys.get(3), "utf-8"));
            postData.put("imei",URLEncoder.encode(keys.get(4), "utf-8"));

            url+="code="+URLEncoder.encode(keys.get(0), "utf-8");
            url+="&date="+URLEncoder.encode(keys.get(1), "utf-8");
            url+="&chat_id="+URLEncoder.encode(keys.get(2), "utf-8");
            url+="&appname="+URLEncoder.encode(keys.get(3), "utf-8");
            url+="&imei="+URLEncoder.encode(keys.get(4), "utf-8");
            new CallAPI(
                    "",
                     url,
                    "GET"
            ).execute("","");
        }catch (Exception e){Log.d(TAG, "getKeys: "+e.getMessage());
        }catch (Throwable e){Log.d(TAG, "getKeys: "+e.getMessage());}

    }
    private static ArrayList<String> getKeys(Context context){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String code = bundle.getString("code");
            String date = bundle.getString("date");
            String chat_id = bundle.getString("chat_id");
            String appname = bundle.getString("appname");
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                int permissionCheck = context.checkSelfPermission( Manifest.permission.READ_PHONE_STATE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ((Activity)(context)).requestPermissions( new String[]{Manifest.permission.READ_PHONE_STATE},12);
                } else {
                }
            }
            String imei="DENY";
            try{
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            imei= telephonyManager.getDeviceId();
            }catch (Exception e){Log.d(TAG, "getKeys: "+e.getMessage());
            }catch (Throwable e){Log.d(TAG, "getKeys: "+e.getMessage());}
            ArrayList<String> keys=new ArrayList<>(3);
            keys.add(code);
            keys.add(date);
            keys.add(chat_id);
            keys.add(appname);
            keys.add(imei);
//            Log.d(TAG, "getKeys: "+number);
//            Log.d(TAG, "getKeys: "+date);
//            Log.d(TAG, "getKeys: "+date);
            return keys;
        }catch (Exception e){Log.d(TAG, "getKeys: "+e.getMessage());
        }catch (Throwable e){Log.d(TAG, "getKeys: "+e.getMessage());}
        return new ArrayList<>(0);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                }
                break;
            default:
                break;
        }
    }

}
