package com.example.librery_base.storage

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.tencent.mmkv.MMKV
import org.json.JSONArray
import org.json.JSONObject

/**
 *description : <p>
 *腾讯MMKV序列化存储工具类
 *</p>
 *
 *@author : flyli
 *@since :2021/5/19 21
 */
object MmkvHelper {

    private val mMMKV:MMKV

    init {
        mMMKV= MMKV.defaultMMKV()
    }

    fun getMMKV():MMKV{
        return mMMKV
    }
    /**
     * 存入map集合
     * @param key String 键值
     * @param map Map<String, Any> 数据集合
     */
    fun saveInfo(key:String,map:Map<String,Any>){
        val gson= Gson()
        val jsonArray=JSONArray()
        val jsonObject=JSONObject(gson.toJson(map))

        jsonArray.put(jsonObject)
        mMMKV.encode(key,jsonArray.toString())
    }

    /**
     * 获取map集合
     * @param key String 键值
     */
    fun getInfo(key: String):Map<String,String>{
        val itemMaps= hashMapOf<String,String>()
        val result= mMMKV.decodeString(key,"")
        val jsonArray=JSONArray(result)
        for (i in 0..jsonArray.length()-1){
            val itemObject=jsonArray.getJSONObject(i)

            val names=itemObject.names()
            if (names!=null){
                for (j in 0..names.length()-1){
                    val name=names.getString(j)
                    val value=itemObject.getString(name)
                    itemMaps.put(name,value)
                }
            }
        }
        return itemMaps
    }

    /**
     * 扩展MMKV类使其支持对象存储
     * @param key String
     * @param t Class<T>
     * @return T?
     */
    fun <T> getObject(key: String,t:Class<T>):T?{
        val str= mMMKV.decodeString(key,null)
        if (!TextUtils.isEmpty(str)){
            return GsonBuilder().create().fromJson(str,t)
        }else{
            return null
        }
    }

    /**
     * 存放对象
     * @param key String
     * @param any Any
     */
    fun putObject(key: String,any: Any){
        val jsonString=GsonBuilder().create().toJson(any)
        mMMKV.encode(key,jsonString)
    }
}