package com.dragger2.reactnativetest1022.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dragger2.reactnativetest1022.R;
import com.zqzn.idauth.sdk.DetectEngine;
import com.zqzn.idauth.sdk.ErrorCode;
import com.zqzn.idauth.sdk.FaceResultCallback;
import com.zqzn.idauth.sdk.IdResultCallback;

public class IdentityAuthActivityJava extends Activity implements IdResultCallback, FaceResultCallback, View.OnClickListener {
    private Button btnIdentity, btnFaceRecognition;
    private ImageView ivIdentityFront, ivIdentityBack, ivFaceRecognition;//正面、反面和头像照
    DetectEngine detectEngine = new DetectEngine();
    String app_key = "xxx";
    String secret_key = "xxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identityauth);
        btnIdentity = findViewById(R.id.btnIdentity);
        btnFaceRecognition = findViewById(R.id.btnFaceRecognition);
        ivIdentityFront = findViewById(R.id.ivIdentityFront);
        ivIdentityBack = findViewById(R.id.ivIdentityBack);
        ivFaceRecognition = findViewById(R.id.ivFaceRecognition);

        btnIdentity.setOnClickListener(this);
        btnFaceRecognition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnIdentity) {
            try {
                if (detectEngine.id_ocr(this, app_key, secret_key, this) != ErrorCode.SUCCESS.getCode()) {
                    Toast.makeText(getApplicationContext(), "接口调用失败", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.btnFaceRecognition) {
            try {
                if (detectEngine.face_liveness(this, app_key, secret_key, 1, this) != ErrorCode.SUCCESS.getCode()) {
                    Toast.makeText(getApplicationContext(), "接口调用失败", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void notifyResult(IdResult result) {
        if (result.result_code == ErrorCode.SUCCESS.getCode()) {
            ivIdentityFront.setImageBitmap(result.front_image);
            ivIdentityBack.setImageBitmap(result.back_image);
            ivFaceRecognition.setImageBitmap(result.face_image);
        } else {
            ivIdentityFront.setImageResource(R.drawable.super_ic_identity_front);
            ivIdentityBack.setImageResource(R.drawable.super_ic_identity_back);
            ivFaceRecognition.setImageResource(R.mipmap.ic_launcher);
            Toast.makeText(getApplicationContext(), String.format("OCR扫描失败:%s", result.result_code), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void notifyResult(FaceResult result) {
        if (result.result_code == ErrorCode.SUCCESS.getCode()) {
            ivFaceRecognition.setImageBitmap(result.face_image);
        } else {
            ivFaceRecognition.setImageResource(R.mipmap.ic_launcher);
            Toast.makeText(getApplicationContext(), String.format("活体检测失败:%s", result.result_code), Toast.LENGTH_LONG).show();
        }
    }
}
