package com.dragger2.reactnativetest1022.activity

import android.content.Intent
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import kotlinx.android.synthetic.main.activity_customlayout.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/12
 */
class CustomLayoutActivity : BaseActivity(){
    override fun bindLayout(): Int {
        return R.layout.activity_customlayout
    }

    override fun initListener() {
        super.initListener()

        //练习1
        tvCustomLayoutPractice1.setOnClickListener {
            val intent = Intent(this, CustomLayoutPractice1::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

//        //练习2
//        tvCustomLayoutPractice2.setOnClickListener {
//            val intent = Intent(this, CustomViewPractice2::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
//        }
//
//        //练习3
//        tvCustomLayoutPractice3.setOnClickListener {
//            val intent = Intent(this, CustomViewPractice3::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
//        }
    }
}