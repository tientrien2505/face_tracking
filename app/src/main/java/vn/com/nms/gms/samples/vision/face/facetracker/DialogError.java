package vn.com.nms.gms.samples.vision.face.facetracker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.gms.samples.vision.face.facetracker.R;

public class DialogError extends Dialog implements View.OnClickListener {
    private Button buttonDongY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_error);
        buttonDongY = findViewById(R.id.button_dongy);
        buttonDongY.setOnClickListener(this);
    }

    public DialogError(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onClick(View v) {
        this.cancel();
    }
}
