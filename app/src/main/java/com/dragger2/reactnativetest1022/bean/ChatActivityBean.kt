package com.dragger2.reactnativetest1022.bean

/**
 *  Description :聊天bean

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/2
 */
class ChatActivityBean {
    var chatContent = ""
    var chatType = 0

    constructor(content:String,type:Int){
        this.chatContent = content
        this.chatType = type
    }
}