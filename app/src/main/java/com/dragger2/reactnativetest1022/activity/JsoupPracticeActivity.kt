package com.dragger2.reactnativetest1022.activity

import android.util.Log
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.bean.JsoupActivityBean
import org.jsoup.Jsoup
import org.jsoup.nodes.Document




/**
 *  Description :jsoup爬去html页面数据

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/12/17
 */
class JsoupPracticeActivity : BaseActivity() {
    private val url = "http://www.cwl.gov.cn/kjxx/ssq/kjgg/"

    override fun bindLayout(): Int {
        return R.layout.activity_jsouppractice
    }

    override fun initView() {
        super.initView()

//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//        }

        //开启子线程
        Thread(runnable).start()
    }

//    private var handler: Handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            val data = msg.data
//            val value = data.getString("value")
//            Log.i("yang", "请求结果:" + value)
//        }
//    }

    private var runnable: Runnable = Runnable {
        //获取网页Document对象
        val mDoc = Jsoup.connect(url).get()
        parseHtml(mDoc)
        val mDoc2 = Jsoup.connect("http://www.xianmiapp.com:9999/web/ge/job/search_job?type=3&cityId=&keyword=%E4%B8%8A%E6%B5%B7").get()
        parseHtml2(mDoc2)

//        val msg = Message()
//        val data = Bundle()
//        data.putString("value", "请求结果")
//        msg.data = data
//        handler.sendMessage(msg)
    }

    //通过Document对象返回一个实体类参数
    private fun parseHtml(doc: Document): List<JsoupActivityBean> {
        //先获取页面的elements
        val elements = doc.select("div[class = bgzt] > table > tbody > tr")
        val list = ArrayList<JsoupActivityBean>()
        //遍历elements
        var bean: JsoupActivityBean
        elements.forEach {
            bean = JsoupActivityBean()
            //设置期数
            bean.lotteryPeriod = it.select("td").first().text()
            //设置日期
            bean.lotteryData = it.select("td")[1].text()
            val tds = it.select("td")[2]
            //tds数据 里面有许多span
            val strs = arrayOfNulls<String>(6)
            //从tds里遍历获取
            for (i in 0..5) {
                strs[i] = tds.select("span")[i].text()
            }
            strs[strs.size - 1] = it.select("td[class = bq1]").text()
            //遍历strs 拼接成字符串
            val sb = StringBuilder()
            strs.forEach {
                sb.append(it)
            }
            //设置号码
            bean.lotteryNumber = sb.toString()
            list.add(bean)
        }
        Log.i("yang", list.toString())
        return list
    }

    //通过Document对象返回一个实体类参数
    private fun parseHtml2(doc: Document): List<JsoupActivityBean> {
        //先获取页面的elements
        val elements = doc.select("ul[class = list-content]")[0]

        val list = ArrayList<JsoupActivityBean>()
//        //遍历elements
//        var bean: JsoupActivityBean
//        elements.forEach {
//            bean = JsoupActivityBean()
//            //设置期数
//            bean.lotteryPeriod = it.select("td").first().text()
//            //设置日期
//            bean.lotteryData = it.select("td")[1].text()
//            val tds = it.select("td")[2]
//            //tds数据 里面有许多span
//            val strs = arrayOfNulls<String>(6)
//            //从tds里遍历获取
//            for (i in 0..5) {
//                strs[i] = tds.select("span")[i].text()
//            }
//            strs[strs.size - 1] = it.select("td[class = bq1]").text()
//            //遍历strs 拼接成字符串
//            val sb = StringBuilder()
//            strs.forEach {
//                sb.append(it)
//            }
//            //设置号码
//            bean.lotteryNumber = sb.toString()
//            list.add(bean)
//        }
//        Log.i("yang", list.toString())
        return list
    }
}