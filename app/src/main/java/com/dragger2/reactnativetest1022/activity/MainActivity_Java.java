package com.dragger2.reactnativetest1022.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dragger2.reactnativetest1022.R;
import com.zqzn.idauth.sdk.DetectEngine;
import com.zqzn.idauth.sdk.ErrorCode;
import com.zqzn.idauth.sdk.FaceResultCallback;
import com.zqzn.idauth.sdk.IdResultCallback;

public class MainActivity_Java extends Activity implements IdResultCallback, FaceResultCallback, View.OnClickListener {
    TextView ocr_detect, liveness_detect, versionView;
    private ImageView idCardView, idCardBackView, idCardFaceView;//正面、反面和头像照
    DetectEngine detectEngine = new DetectEngine();
    String app_key = "xxx";
    String secret_key = "xxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        ocr_detect = (TextView) findViewById(R.id.ocr_detect);
        liveness_detect = (TextView) findViewById(R.id.liveness_detect);
        ocr_detect.setOnClickListener(this);
        liveness_detect.setOnClickListener(this);
        idCardView = (ImageView) findViewById(R.id.id_card_front);
        idCardBackView = (ImageView) findViewById(R.id.id_card_back);
        idCardFaceView = (ImageView) findViewById(R.id.id_card_face);
        versionView = (TextView) findViewById(R.id.main_version);
//        String versionString = "版本号:";
//        versionString += BuildConfig.VERSION_CODE;
//        versionString += "_";
//        versionString += BuildConfig.DEBUG ? "d" : "r";
//        versionString += "\n编译时间:";
//        versionString += BuildConfig.BUILD_TIMESTAMP;
//        versionView.setText(versionString);
    }

    @Override
    public void notifyResult(IdResult result) {
        if (result.result_code == ErrorCode.SUCCESS.getCode()) {
            idCardView.setImageBitmap(result.front_image);
            idCardBackView.setImageBitmap(result.back_image);
            idCardFaceView.setImageBitmap(result.face_image);
        } else {
            idCardView.setImageResource(R.drawable.super_ic_identity_front);
            idCardBackView.setImageResource(R.drawable.super_ic_identity_back);
            idCardFaceView.setImageBitmap(null);
            Toast.makeText(getApplicationContext(), String.format("OCR扫描失败:%s", result.result_code), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ocr_detect) {
            try {
                if (detectEngine.id_ocr(MainActivity_Java.this, app_key, secret_key, MainActivity_Java.this) != ErrorCode.SUCCESS.getCode())
                    Toast.makeText(getApplicationContext(), "接口调用失败", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.liveness_detect) {
            try {
                if (detectEngine.face_liveness(MainActivity_Java.this, app_key, secret_key, 1, this) != ErrorCode.SUCCESS.getCode())
                    Toast.makeText(getApplicationContext(), "接口调用失败", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.main_check_upgrade) {
//            Beta.checkUpgrade();
        }
    }

    @Override
    public void notifyResult(FaceResult result) {
        if (result.result_code == ErrorCode.SUCCESS.getCode()) {
            idCardFaceView.setImageBitmap(result.face_image);
        } else {
            idCardFaceView.setImageBitmap(null);
            Toast.makeText(getApplicationContext(), String.format("活体检测失败:%s", result.result_code), Toast.LENGTH_LONG).show();
        }
    }
}
