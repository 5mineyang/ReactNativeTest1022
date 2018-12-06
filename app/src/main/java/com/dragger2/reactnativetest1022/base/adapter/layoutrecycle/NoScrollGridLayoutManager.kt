package com.dragger2.reactnativetest1022.base.adapter.layoutrecycle

import android.content.Context
import android.support.v7.widget.GridLayoutManager


class NoScrollGridLayoutManager(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {
    var isScrollEnabled = false

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}
