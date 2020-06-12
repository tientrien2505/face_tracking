package vn.com.nms.gms.samples.vision.face.facetracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;

import java.util.ArrayList;
import java.util.Calendar;

public class TraCuuActivity extends AppCompatActivity {
    Calendar mCalendar;
    TextView mTimeView;
    int mCurrentMonth;
    int mCurrentYear;
    int month;
    int year;
    ImageButton buttonNext;
    ImageButton buttonPrev;
    RecyclerView mRecyclerview;
    ImageView imageViewAvatar;
    MyAdapter mAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu);

        mTimeView = findViewById(R.id.time_view);
        buttonNext = findViewById(R.id.next_button);
        buttonPrev = findViewById(R.id.prev_button);
        imageViewAvatar = findViewById(R.id.image_view_avatar);
        Matrix m = new Matrix();
        m.postRotate(90);
        Bitmap bmp = Bitmap.createBitmap(CameraActivity.mBitmap, 0, 0, CameraActivity.mBitmap.getWidth(), CameraActivity.mBitmap.getHeight());
        Log.d("TRUONG", "bitmap_size: width: " + String.valueOf(bmp.getWidth()) + ", height: " + String.valueOf(bmp.getHeight()));
        Frame frame = new Frame.Builder().setBitmap(bmp).build();
        Log.d("TRUONG", "frame_size: width: " + String.valueOf(frame.getMetadata().getWidth()) + ", height: " + String.valueOf(frame.getMetadata().getHeight()));
        SparseArray<Face> faces = CameraActivity.mDetector.detect(frame);
        if (faces.size() > 0) {
            Face face = faces.valueAt(0);
            int left = (int)face.getPosition().x;
            int top = (int)face.getPosition().y;

            Bitmap bmp0 = Bitmap.createBitmap(bmp, left, top, (int)face.getWidth(), (int)face.getHeight());
            imageViewAvatar.setImageBitmap(Bitmap.createScaledBitmap(bmp0, 200, 200, true));
        } else {
            imageViewAvatar.setImageBitmap(bmp);
        }

        mCalendar = Calendar.getInstance();
        mCurrentMonth = mCalendar.get(Calendar.MONTH) + 1;
        mCurrentYear = mCalendar.get(Calendar.YEAR);
        month = mCurrentMonth;
        year = mCurrentYear;
        setTime(month, year);
        if (year == mCurrentYear && month == mCurrentMonth){
            buttonNext.setImageDrawable(getDrawable(R.drawable.next_unable));
        }
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ChamCong> listChamCong = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listChamCong.add(new ChamCong(getString(R.string.string_date), getString(R.string.string_checkin), getString(R.string.string_checkout)));
        }
        mAdapter = new MyAdapter(listChamCong);
        mRecyclerview.setAdapter(mAdapter);
    }
    void setTime(int month, int year){
        mTimeView.setText("Tháng "+String.valueOf(month) + ", " + String.valueOf(year));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void prevMonth(View view) {
        month--;
        if (month <= 0){
            month = 12;
            year--;
        }
        setTime(month, year);
        if (!(year == mCurrentYear && month == mCurrentMonth)){
            buttonNext.setImageDrawable(getDrawable(R.drawable.next_enable));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void nextMonth(View view) {
        if (nextAble()){
            setTime(month, year);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean nextAble(){
        int tmpMonth = month;
        int tmpYear = year;
        month++;
        if (month > 12){
            year++;
            month = 1;
        }
        if (year > mCurrentYear){
            year = tmpYear;
            month = tmpMonth;
            return false;
        } else if (year == mCurrentYear && month > mCurrentMonth){
            year = tmpYear;
            month = tmpMonth;
            return false;
        }
        if (year == mCurrentYear && month == mCurrentMonth){
            buttonNext.setImageDrawable(getDrawable(R.drawable.next_unable));
        }
        return true;
    }
}