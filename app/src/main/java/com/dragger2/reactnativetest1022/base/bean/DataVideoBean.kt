package com.dragger2.reactnativetest1022.base.bean

import com.google.gson.annotations.SerializedName


/**
 * Created by Administrator on 2018/6/23.
 */

data class DataVideoBean(
        @SerializedName("dataType") val dataType: String,
        @SerializedName("appCode") val appCode: String,
        @SerializedName("pageToken") val pageToken: String,
        @SerializedName("hasNext") val hasNext: Boolean,
        @SerializedName("data") val data: List<VideoBean>,
        @SerializedName("retcode") val retcode: String
)

data class VideoBean(
        @SerializedName("videoUrls") val videoUrls: List<String>,
        @SerializedName("posterScreenName") val posterScreenName: String,
        @SerializedName("likeCount") val likeCount: Int,
        @SerializedName("id") val id: String,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("publishDate") val publishDate: Int,
        @SerializedName("dislikeCount") val dislikeCount: Int,
        @SerializedName("posterId") val posterId: String,
        @SerializedName("url") val url: String,
        @SerializedName("imageUrls") val imageUrls: Any,
        @SerializedName("publishDateStr") val publishDateStr: String,
        @SerializedName("shareCount") val shareCount: Int,
        @SerializedName("content") val content: String,
        @SerializedName("catName1") val catName1: String,
        @SerializedName("commentCount") val commentCount: Int,
        @SerializedName("catId1") val catId1: String,
        @SerializedName("title") val title: String,
        @SerializedName("imageURLs") val imageURLs: Boolean,
        @SerializedName("labels") val labels: List<Any>,
        @SerializedName("pDate") val pDate: Boolean,
        @SerializedName("subtitle") val subtitle: Boolean,
        @SerializedName("commenterScreenName") val commenterScreenName: Boolean,
        @SerializedName("rating") val rating: Boolean,
        @SerializedName("sellerScreenName") val sellerScreenName: Boolean,
        @SerializedName("coverUrl") val coverUrl: Boolean,
        @SerializedName("description") val description: Boolean
)