package ca.acsea.funstop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class torCamera extends AppCompatActivity {
    SurfaceView cameraPreview;
    TextView txtResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    public static final int RequestCameraPermissionID = 1001;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults){
        switch (requestCode){
            case RequestCameraPermissionID:{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(torCamera.this,
                                new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    }
                }
            }
        }
    public void startTorFunStopActivity(View view){
        Intent intent = new Intent(this, TorFunStop.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, TorFunStop.class);
        //intent.putExtra("isNewUser",false);
        startActivity(intent);
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_camera);
        Intent intent = getIntent();
        cameraPreview = (SurfaceView)findViewById(R.id.TorCameraView);
        txtResult = (TextView)findViewById(R.id.txtResult);
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640,480)
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(torCamera.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2){

            }
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder){
                cameraSource.stop();
            }

        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>(){
            @Override
            public void release() {
            }
            @Override
            public void receiveDetections(Detector.Detections < Barcode > detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            //txtResult.setText(qrcodes.valueAt(0).displayValue);
                            Intent intent = new Intent(torCamera.this, TorFunStop.class);
                            intent.putExtra("barcode", qrcodes.valueAt(0));
                            startActivity(intent);
                        }
                    });
                    //finish();
                 /*txtResult.post(new Runnable() {
                    @Override
                    public void run() {
                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        //vibrator.vibrate(VibrationEffect.DEFAULT_AMPLITUDE);

                        try{
                            stationID = Integer.parseInt(qrcodes.valueAt(0).rawValue);
                            txtResult.setText(stationID);


                        }catch(Exception e){
                            stationID = 0;
                            txtResult.setText(qrcodes.valueAt(0).displayValue);
                        }


                            }
                        });*/
                }
            }
        });
    }
}

