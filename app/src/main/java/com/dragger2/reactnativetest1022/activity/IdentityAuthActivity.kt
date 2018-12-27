package com.dragger2.reactnativetest1022.activity

import android.widget.Toast
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.zqzn.idauth.sdk.DetectEngine
import com.zqzn.idauth.sdk.ErrorCode
import com.zqzn.idauth.sdk.FaceResultCallback
import com.zqzn.idauth.sdk.IdResultCallback
import kotlinx.android.synthetic.main.activity_identityauth.*

/**
 *  Description :扫描

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/12/20
 */
class IdentityAuthActivity : BaseActivity(), IdResultCallback, FaceResultCallback {
    private var app_key = "xxx"     //2个key
    private var secret_key = "xxx"
    private val mDetectEngine = DetectEngine()  //通过此对象调想要扫描方法

    override fun bindLayout(): Int {
        return R.layout.activity_identityauth
    }

    override fun initListener() {
        super.initListener()

        //身份证按钮
        btnIdentity.setOnClickListener {
            //id_cor 身份证识别方法
            if (mDetectEngine.id_ocr(this, app_key, secret_key, this) != ErrorCode.SUCCESS.code) {
                Toast.makeText(this, "接口调用失败", Toast.LENGTH_SHORT).show()
            }
            println("1")
        }

        //人脸识别按钮
        btnFaceRecognition.setOnClickListener {
            //face_liveness 人脸识别方法
            if (mDetectEngine.face_liveness(this, app_key, secret_key, 0, this) != ErrorCode.SUCCESS.code) {
                Toast.makeText(this, "接口调用失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //身份证识别回调
    override fun notifyResult(result: IdResultCallback.IdResult) {
        if (result.result_code == ErrorCode.SUCCESS.code) {
            ivIdentityFront.setImageBitmap(result.front_image)
            ivIdentityBack.setImageBitmap(result.back_image)
            ivFaceRecognition.setImageBitmap(result.face_image)
        } else {
            ivIdentityFront.setImageResource(R.drawable.super_ic_identity_front)
            ivIdentityBack.setImageResource(R.drawable.super_ic_identity_back)
            ivFaceRecognition.setImageResource(R.mipmap.ic_launcher)
            Toast.makeText(this, String.format("OCR扫描失败:%s", result.result_code), Toast.LENGTH_SHORT).show()
        }
    }

    //人脸识别识别回调
    override fun notifyResult(result: FaceResultCallback.FaceResult) {
        if (result.result_code == ErrorCode.SUCCESS.code) {
            ivFaceRecognition.setImageBitmap(result.face_image)
        } else {
            ivFaceRecognition.setImageResource(R.mipmap.ic_launcher)
            Toast.makeText(this, String.format("活体检测失败:%s", result.result_code), Toast.LENGTH_SHORT).show()
        }
    }
}