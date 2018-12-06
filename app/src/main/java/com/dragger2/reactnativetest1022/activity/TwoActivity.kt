package com.dragger2.reactnativetest1022.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.base.adapter.CommonAdapter
import com.dragger2.reactnativetest1022.base.api.ApiService
import com.dragger2.reactnativetest1022.base.api.ApiSubscriber
import com.dragger2.reactnativetest1022.base.api.DefaultTransformer
import com.dragger2.reactnativetest1022.base.api.RequestManager
import com.dragger2.reactnativetest1022.base.bean.BaseBean
import com.dragger2.reactnativetest1022.base.bean.DataVideoBean
import com.dragger2.reactnativetest1022.base.view.SwipeMenuLayout
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_two.*
import java.util.*

/**
 *  Description :消息列表界面

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/10/24
 */
class TwoActivity : BaseActivity() {
    private lateinit var mAdapterLayout: LinearLayoutManager
    private lateinit var mAdapter: CommonAdapter<String>
    private val mList = ArrayList<String>()
    private lateinit var mAnimationDrawable: AnimationDrawable      //动画
    private var mAnimalPlay = false                                 //动画是否总是播放
    private var mNum = 0

    override fun bindLayout(): Int {
        return R.layout.activity_two
    }

    override fun initView() {

        ivTwoActivityRefresh.setBackgroundResource(R.drawable.book)
        mAnimationDrawable = ivTwoActivityRefresh.background as AnimationDrawable
        mAnimationDrawable.start()

        initAdapter()

        srlTwoActivity.autoRefresh()
        httpLoad()
    }

    override fun initListener() {

        srlTwoActivity.setOnRefreshListener {
            mAnimationDrawable.start()
            httpLoad()
        }

        //下拉刷新事件监听
        srlTwoActivity.setOnMultiPurposeListener(object : OnMultiPurposeListener {
            override fun onFooterPulling(footer: RefreshFooter?, percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
                Log.d("test", "========================onFooterPulling")
            }

            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                Log.d("test", "========================onLoadmore")
            }

            override fun onHeaderReleasing(header: RefreshHeader?, percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
                Log.d("test", "========================onHeaderReleasing")
            }

            //回弹
            override fun onHeaderStartAnimator(header: RefreshHeader?, headerHeight: Int, extendHeight: Int) {
                Log.d("test", "========================onHeaderStartAnimator")
            }

            //刚拉起或到底或松开或结束
            override fun onStateChanged(refreshLayout: RefreshLayout?, oldState: RefreshState?, newState: RefreshState?) {
                if (oldState == RefreshState.None) {
                    mAnimationDrawable.selectDrawable(0)
                }
                Log.d("test", "========================onStateChanged")
            }

            override fun onFooterFinish(footer: RefreshFooter?, success: Boolean) {
                Log.d("test", "========================onFooterFinish")
            }

            override fun onFooterStartAnimator(footer: RefreshFooter?, footerHeight: Int, extendHeight: Int) {
                Log.d("test", "========================onFooterStartAnimator")
            }

            override fun onFooterReleasing(footer: RefreshFooter?, percent: Float, offset: Int, footerHeight: Int, extendHeight: Int) {
                Log.d("test", "========================")
            }

            //下拉
            override fun onHeaderPulling(header: RefreshHeader?, percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
                Log.d("test", "========================onHeaderPulling")
            }

            //刷新
            override fun onRefresh(refreshlayout: RefreshLayout?) {
                Log.d("test", "========================onRefresh")
            }

            //头部结束
            override fun onHeaderFinish(header: RefreshHeader?, success: Boolean) {
                Log.d("test", "========================onHeaderFinish")
            }
        })

        //切换下拉方式
        cbTwoActivityContentSwitch.setOnClickListener {
            if (cbTwoActivityContentSwitch.isChecked) {
                srlTwoActivity.setEnableHeaderTranslationContent(true)
            } else {
                srlTwoActivity.setEnableHeaderTranslationContent(false)
            }
        }

        //切换动画播放方式
        cbTwoActivityAnimalSwitch.setOnClickListener {
            if (cbTwoActivityAnimalSwitch.isChecked) {
                mAnimationDrawable.start()
                mAnimalPlay = true
            } else {
                mAnimationDrawable.stop()
                mAnimalPlay = false
            }
        }
    }

    //适配器
    private fun initAdapter() {
        mAdapterLayout = LinearLayoutManager(this)
        rvTwoActivity.layoutManager = mAdapterLayout
        mAdapter = CommonAdapter(this, R.layout.activity_two_item, mList, holderConvert = { holder, t, position, payloads ->
            holder.apply {
                getView<SwipeMenuLayout>(R.id.swipeMenuLayout).apply {
                    //设置是否开启IOS阻塞式交互
                    isIos = false
                    //true往左滑动,false为往右侧滑动
                    isLeftSwipe = true
                    //设置侧滑功能开关
                    isSwipeEnable = true
                }
                //设置内容
                setText(R.id.tvTwoActivityRvMessage, t)

                //item点击
                getView<RelativeLayout>(R.id.rlTwoActivityItem).setOnClickListener {
                    //跳转到聊天界面
                    toNewAty(this@TwoActivity, ChatActivity::class.java, mList[position])
//                    Toast.makeText(this@TwoActivity, mList[position], Toast.LENGTH_SHORT).show()
                }

                //置顶
                getView<Button>(R.id.btnTop).setOnClickListener {
                    val str = mList[position]
                    mList.remove(mList[position])
                    mList.add(0, str)
                    getView<SwipeMenuLayout>(R.id.swipeMenuLayout).quickClose()
//                    Toast.makeText(this@TwoActivity, "置顶", Toast.LENGTH_SHORT).show()
                    mAdapter.notifyDataSetChanged()
                }

                //删除
                getView<Button>(R.id.btnDelete).setOnClickListener {
                    mList.remove(mList[position])
                    getView<SwipeMenuLayout>(R.id.swipeMenuLayout).quickClose()
//                    Toast.makeText(this@TwoActivity, "删除", Toast.LENGTH_SHORT).show()
                    mAdapter.notifyDataSetChanged()
                }
            }
            //item点击
        }, onItemClick = { view, holder, position ->

        })
        rvTwoActivity.adapter = mAdapter
    }

    //网络请求
    private fun httpLoad() {
        RequestManager.instance
                .createService(ApiService::class.java)
                .getOssDataBean()
                .compose(DefaultTransformer.create<BaseBean<DataVideoBean>>())
                .subscribe(object : ApiSubscriber<BaseBean<DataVideoBean>>(this) {
                    override fun onNext(t: BaseBean<DataVideoBean>) {
                        srlTwoActivity.finishRefresh()
                        if (!mAnimalPlay) {
                            mAnimationDrawable.stop()
                        }
                        setDate()
                        Toast.makeText(this@TwoActivity, "成功", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSubscribe(d: Disposable) {
                        super.onSubscribe(d)
                        addSubscription(d)

                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        srlTwoActivity.finishRefresh()
                        if (!mAnimalPlay) {
                            mAnimationDrawable.stop()
                        }
                        Toast.makeText(this@TwoActivity, "失败", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    //添加模拟数据
    private fun setDate() {
        val num = mNum + 20
        mList.clear()
        for (i in mNum..num) {
            mList.add("message" + i)
            mNum = i
        }
        mAdapter.notifyDataSetChanged()
    }

    //带参数跳转aty
    private fun toNewAty(context: Context, c: Class<*>, sort: String) {
        val intent = Intent(context, c)
        intent.putExtra("sort", sort)
        startActivity(intent)
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
    }
}