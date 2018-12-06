package com.dragger2.reactnativetest1022.activity

import android.content.Intent
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import kotlinx.android.synthetic.main.activity_customview.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/12
 */
class CustomViewActivity : BaseActivity(){
    override fun bindLayout(): Int {
        return R.layout.activity_customview
    }

    override fun initListener() {
        super.initListener()

        //练习1
        tvCustomViewPractice1.setOnClickListener {
            val intent = Intent(this, CustomViewPractice1::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //练习2
        tvCustomViewPractice2.setOnClickListener {
            val intent = Intent(this, CustomViewPractice2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //练习3
        tvCustomViewPractice3.setOnClickListener {
            val intent = Intent(this, CustomViewPractice3::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //练习4
        tvCustomViewPractice4.setOnClickListener {
            val intent = Intent(this, CustomViewPractice4::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //练习5
        tvCustomViewPractice5.setOnClickListener {
            val intent = Intent(this, CustomViewPractice5::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //练习6
        tvCustomViewPractice6.setOnClickListener {
            val intent = Intent(this, CustomViewPractice6::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //练习7
        tvCustomViewPractice7.setOnClickListener {
            val intent = Intent(this, CustomViewPractice7::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }
    }
}