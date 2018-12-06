package com.dragger2.reactnativetest1022.base.json;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * json数据转对象
 * Created by wangru
 * Date: 2018/4/4  11:08
 * mail: 1902065822@qq.com
 * describe:
 * 主要用于转换有泛型的对象
 */

public class GsonConvert {
    public static <T> T jsonToObj(String jsonStr, Type type) {
        return new Gson().fromJson(jsonStr, type);
    }

    /**
     * json 转对象  对象有一个泛型
     *
     * @param reader    json 数据
     * @param clazz     要转换成的对象类 eg：BaseBean<ResumeTxtBean>()::class.java
     * @param classType 要转换成的对象的泛型类 eg: ResumeTxtBean::class.java
     * @param <T>       要转换成的对象里面的泛型类名 eg: ResumeTxtBean
     * @return 要转换成的对象类  eg:BaseBean<ResumeTxtBean>()
     * eg: MessageBean<ImageBean> message = GsonConvert.jsonToBean(jsonString, MessageBean.class, ImageBean.class);
     */
    public static <T> T jsonToBean(String reader, Class<T> clazz, Class classType) {
        Type typeT = new ParameterizedTypeImpl(clazz, new Class[] {classType});
        return new Gson().fromJson(reader, typeT);
    }

    /**
     * json 转List对象  对象里面有一个泛型
     *
     * @param json      json 数据
     * @param classType List对象的泛型类
     * @param <T>       List对象的泛型类名
     * @return List对象
     * eg: List<I> info=GsonConvert.jsonToBeanList(jsonString,iclass);
     */
    public static <T> List<T> jsonToBeanList(String json, Class<T> classType) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[] {classType});
        List<T> list = new Gson().fromJson(json, listType);
        return list;
    }

    /***
     *
     * @param json json 数据
     * @param clazz eg: SelectTypeBeam::class.java
     * @param classType eg:BaseBean::class.java
     * @return BaseBean<List<SelectTypeBeam>>
     * @deprecated
     *  val resultData = GsonConvert.fromJsonToBeanDataList(result,BaseBean::class.java,SelectTypeTwoBean::class.java) as BaseBean<List<SelectTypeTwoBean>>
     */
    public static <T> Object fromJsonToBeanDataList(String json, Class classType, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[] {clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(classType, new Type[] {listType});
        return new Gson().fromJson(json, type);
    }

    public static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
