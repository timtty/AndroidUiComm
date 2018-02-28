package pw.holocube.lvdev1;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class IdService extends FirebaseInstanceIdService {
    final String TAG = "InstanceIdService";
    String token;

    @Override
    public void onTokenRefresh() {
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
    }

    public String getToken() {
        return token;
    }
}
