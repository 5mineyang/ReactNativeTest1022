package com.dragger2.reactnativetest1022.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.dragger2.reactnativetest1022.base.adapter.CommonAdapter
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.bean.ChatActivityBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.concurrent.TimeUnit

/**
 *  Description :聊天界面

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/2
 */
class ChatActivity : BaseActivity() {
    private lateinit var mAdapterLayout: LinearLayoutManager
    private lateinit var mAdapter: CommonAdapter<ChatActivityBean>
    private val mList = ArrayList<ChatActivityBean>()
    private var sort = ""       //上个页面带过来的参数
    private var mDisposable: Disposable? = null

    override fun bindLayout(): Int {
        return R.layout.activity_chat
    }

    override fun initView() {
        super.initView()
        //上个页面参数赋值
        sort = intent.getStringExtra("sort")

        initAdapter()
        //模拟数据
        setData()
    }

    override fun initListener() {
        super.initListener()
        //发送点击事件
        tvChatActivitySendMessage.setOnClickListener {
            mList.add(ChatActivityBean(etChatActivityContent.text.toString(), 1))
            //情况输入框内容
            etChatActivityContent.setText("")
            //设置按钮不可点击
            tvChatActivitySendMessage.isEnabled = false
            mAdapter.notifyDataSetChanged()
            //开启一个线程，2秒后机器人回信息
            mDisposable = Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        mList.add(ChatActivityBean("1", 0))
                        mAdapter.notifyDataSetChanged()
                    })
        }

        //文本内容改变事件
        etChatActivityContent.addTextChangedListener(object : TextWatcher {
            //输入文字后的状态
            override fun afterTextChanged(s: Editable?) {

            }

            //输入文本之前的状态
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            //输入文字中的状态，count是一次性输入字符数
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //有内容了才可以点击发送按钮
                if(etChatActivityContent.text.toString() != ""){
                    tvChatActivitySendMessage.isEnabled = true
                    tvChatActivitySendMessage.setTextColor(resources.getColor(R.color.sendTextColor))
                }else{
                    tvChatActivitySendMessage.isEnabled = false
                    tvChatActivitySendMessage.setTextColor(resources.getColor(R.color.notSendTextColor))
                }
            }
        })
    }

    //适配器
    private fun initAdapter() {
        mAdapterLayout = LinearLayoutManager(this)
        rvChatActivity.layoutManager = mAdapterLayout
        mAdapter = CommonAdapter(this, R.layout.activity_chat_item, mList, holderConvert = { holder, t, position, payloads ->
            holder.apply {
                if (t.chatType == 0) {
                    setText(R.id.tvChatActivityRcLeft, t.chatContent)
                    getView<TextView>(R.id.tvChatActivityRcRight).visibility = View.GONE
                } else {
                    setText(R.id.tvChatActivityRcRight, t.chatContent)
                    getView<TextView>(R.id.tvChatActivityRcLeft).visibility = View.GONE
                }
            }
        })
        rvChatActivity.adapter = mAdapter
    }

    private fun setData() {
        mList.add(ChatActivityBean("你好!", 0))
        mList.add(ChatActivityBean("我是机器人${sort}", 0))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //返回加动画
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right)
    }
}