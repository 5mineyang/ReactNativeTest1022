package com.dragger2.reactnativetest1022.base.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wr
 * Date: 2018/9/9  17:21
 * describe:
 */
@Entity
public class CacheInfoBean {
    @Id(autoincrement = true)
    Long _id;
    String key;
    String version;
    String data;
    String ext;
    Long timeCreate;
    Long lengthTime;

    @Generated(hash = 1932011069)
    public CacheInfoBean(Long _id, String key, String version, String data,
            String ext, Long timeCreate, Long lengthTime) {
        this._id = _id;
        this.key = key;
        this.version = version;
        this.data = data;
        this.ext = ext;
        this.timeCreate = timeCreate;
        this.lengthTime = lengthTime;
    }

    @Generated(hash = 999059599)
    public CacheInfoBean() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Long getTimeCreate() {
        return this.timeCreate;
    }

    public void setTimeCreate(Long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Long getLengthTime() {
        return this.lengthTime;
    }

    public void setLengthTime(Long lengthTime) {
        this.lengthTime = lengthTime;
    }

}
