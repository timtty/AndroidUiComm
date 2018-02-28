package pw.holocube.lvdev1;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

public class MessagingService extends FirebaseMessagingService {
    final String TAG = "MessagingService";
    public final static String ACTION = "pw.holocube.broadcast";

    final Handler handler = new Handler();
    Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Message received from messaging_service");

        if (remoteMessage.getNotification() != null) {
            String msg = remoteMessage.getNotification().getBody();
            intent = new Intent();
            intent.setAction(ACTION);
            intent.putExtra("id", "" + remoteMessage.getMessageId());
            intent.putExtra("time", (new Date()).toString());
            intent.putExtra("msg", msg);

            Log.d(TAG, msg);
            sendBroadcast(intent);
        }
    }
}
