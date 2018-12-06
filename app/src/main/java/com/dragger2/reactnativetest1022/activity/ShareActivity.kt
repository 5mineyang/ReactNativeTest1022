package com.dragger2.reactnativetest1022.activity

import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.base.utils.ShareOtherUtil
import kotlinx.android.synthetic.main.activity_share.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/5
 */
class ShareActivity : BaseActivity() {

    override fun bindLayout(): Int {
        return R.layout.activity_share
    }

    override fun initView() {
        super.initView()


    }

    override fun initListener() {
        super.initListener()

        //分享text到qq
        btnShareTextToQQ.setOnClickListener {
            ShareOtherUtil.shareQQ(this, etShareTextToQQ.text.toString())
        }

        //分享image到qq
        tvShareImageToQQ.setOnClickListener {
            //            ShareOtherUtil.shareImageToQQZone(this,)
        }

        //分享text到微信
        btnShareTextToWX.setOnClickListener {
            ShareOtherUtil.shareWechatFriend(this, etShareTextToWX.text.toString())
        }

        //分享image到微信
        tvShareImageToWX.setOnClickListener {
            //            ShareOtherUtil.shareWechatFriend(this, "",)
        }

        //分享text或image到朋友圈
        tvShareImageToWXMoment.setOnClickListener {
            //            ShareOtherUtil.shareWechatMoment(this,etShareTextToWX.text.toString(),)
        }
    }
}