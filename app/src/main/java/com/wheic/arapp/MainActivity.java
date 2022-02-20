package com.wheic.arapp;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button send_button;
    EditText send_text;

    public ArFragment arCam; //object of ArFragment Class

    public int clickNo = 0; //helps to render the 3d model only once when we tap the screen

    public static boolean checkSystemSupport(Activity activity) {

        //checking whether the API version of the running Android >= 24 that means Android Nougat 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            //checking whether the OpenGL version >= 3.0
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_button = (Button)findViewById(R.id.send_button_id);
        send_text = (EditText)findViewById(R.id.send_text_id);
        // add the OnClickListener in sender button
        // after clicked this button following Instruction will run
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                // get the value which input by user in EditText
                // and convert it to string
                String str = send_text.getText().toString();

                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), modelShow.class);

                // now by putExtra method put the value in key, value pair
                // key is message_key by this key we will receive the value, and put the string

                intent.putExtra("message_key", str);

                // start the Intent
                startActivity(intent);
            }
        });
    }

}
