package com.example.avej2;

import android.app.*;
import android.net.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {
    Button jbn;
    VideoView vvw;
    Uri uri;
    MediaController mcr;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        vvw = (VideoView) findViewById(R.id.xvv1);
        uri = Uri.parse("android.resource://com.example.avej2/"+
                R.raw.this_is_love);
        mcr = new MediaController(this);
        vvw.setMediaController(mcr);
        vvw.setVideoURI(uri);
        vvw.start();
    }
}