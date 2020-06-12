package vn.com.nms.gms.samples.vision.face.facetracker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.android.gms.samples.vision.face.facetracker.R;

public class DialogLoading extends Dialog {
    private ImageView loadingView;
    private Context context;
    public DialogLoading(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
        loadingView = findViewById(R.id.view_loading);
        Glide.with(this.context).load(R.drawable.loading).into(new DrawableImageViewTarget(loadingView));
    }
}
