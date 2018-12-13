package com.ricohbaja.dev.sistemadeactivosfijos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    int intValue;
    String stridAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        stridAsset = rawResult.getText();
        if(isNumberInt(stridAsset))
        {

            Intent intent = new Intent();
            intent.putExtra("editTextId", stridAsset);
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Ha escaneado algo erróneo, por favor vuelva a intentarlo.", Toast.LENGTH_LONG).show();
            mScannerView.resumeCameraPreview(this);
        }
    }

    private boolean isNumberInt(String stridAsset){
        try {
            intValue = Integer.parseInt(stridAsset);
            return  true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
