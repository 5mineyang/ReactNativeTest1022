package com.dragger2.reactnativetest1022.activity

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import kotlinx.android.synthetic.main.activity_glide.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/5
 */
class GlideActivity : BaseActivity() {
//    private lateinit var requestManage: RequestManager
    private val options = RequestOptions()

    override fun bindLayout(): Int {
        return R.layout.activity_glide
    }

    override fun initView() {
        super.initView()

        //硬件加速
        viewLine.setLayerType(View.LAYER_TYPE_SOFTWARE ,null)

//        requestManage = Glide.with(this)
    }

    override fun initListener() {
        super.initListener()

        //加载静态图片
        btnGlideLoadImage.setOnClickListener {
            val url = "http://cn.bing.com/sa/simg/hpb/NorthMale_EN-US8782628354_1920x1080.jpg"
            options.placeholder(R.drawable.maps)                    //占位图
            options.diskCacheStrategy(DiskCacheStrategy.NONE)       //不缓存
            Glide.with(this)
                    .load(url)                                      //路径（可以是网路地址，本地路径）
                    .apply(options)
                    .into(ivGlide)                                  //要加载的控件

//            requestManage?.load(url)?.apply {
//                apply(options)
//            }?.into(ivGlide)
        }

        //加载gif图片
        btnGlideLoadGif.setOnClickListener {
            val url = "http://p1.pstatp.com/large/166200019850062839d3"
            options.placeholder(R.drawable.music)
            options.diskCacheStrategy(DiskCacheStrategy.NONE)
            Glide.with(this)
                    .load(url)
                    .apply(options)
                    .into(ivGlide)
        }
    }
}