package com.dragger2.reactnativetest1022.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import com.dragger2.reactnativetest1022.R

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/8
 */
class ViewPageFragment : Fragment() {
    private var sampleLayoutRes: Int = 0
    private var practiceLayoutRes: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_customview_practice, container, false)

        view.findViewById<ViewStub>(R.id.sampleStub).apply {
            layoutResource = sampleLayoutRes
            inflate()
        }

        view.findViewById<ViewStub>(R.id.practiceStub).apply {
            layoutResource = practiceLayoutRes
            inflate()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes")
            practiceLayoutRes = args.getInt("practiceLayoutRes")
        }
    }

    companion object {
        fun newInstance(sampleLayoutRes: Int, practiceLayoutRes: Int): ViewPageFragment {
            val fragment = ViewPageFragment()
            val args = Bundle()
            args.putInt("sampleLayoutRes", sampleLayoutRes)
            args.putInt("practiceLayoutRes", practiceLayoutRes)
            fragment.arguments = args
            return fragment
        }
    }
}