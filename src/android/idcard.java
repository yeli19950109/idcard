package org.apache.cordova.idcard;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rsk.api.ICard;
import com.rsk.api.ICardInfo;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class echoes a string called from JavaScript.
 */
public class idcard extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if(action.equals("read"))
        {
            int mResult;
            try {
                mResult = ICard.Open();
            }
            catch (Throwable e) {
                callbackContext.error("无法打开设备");
                return true;
            }

            if(mResult == 0)
            {
                ICardInfo info = new ICardInfo();
                int nResult = ICard.Read(info);
                String message;
                if (nResult == 0) {
                    message = info.name+"|"+info.id_num;
                    callbackContext.success(message);
                    return true;
                } else {
                    callbackContext.error("无法读取");
                    //Log.w(TAG, "execute: ", );
                    return true;
                }
            }
            else{
                callbackContext.error("无法打开设备");
                return true;
            }

        }
        else{
            callbackContext.error("error");
            return false;
        }

    }
}
