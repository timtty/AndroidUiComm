package pw.holocube.lvdev1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    final String TAG = "UiThread";
    ListView lv;
    ArrayAdapter adapter;
    ArrayList<String> list;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUi(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv = findViewById(R.id.mainlv);
        lv.setAdapter(adapter);

        Log.d("TOKEN", FirebaseInstanceId.getInstance().getToken());

        registerReceiver(broadcastReceiver, new IntentFilter(MessagingService.ACTION));
    }

    void updateUi(Intent intent) {
        if (!intent.getStringExtra("msg").equals("")) {
            list.add(intent.getStringExtra("msg"));
            adapter.notifyDataSetChanged();

            Log.d(TAG, "updateUi() fired");
        }
    }
}
