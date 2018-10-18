package project.com.qrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import project.com.observerdemo.R;

public class QrActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    /* ****************************
     * Override methods of barcord reder
     * **************************** */

    @Override
    public void onScanned(Barcode barcode) {

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }
}
