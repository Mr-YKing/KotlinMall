package com.yking.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by  on 2018/7/20.YaoKai
 */
class AppManager private constructor(){

    val activityStack:Stack<Activity> = Stack()
    companion object {
        val instance:AppManager by lazy { AppManager() }
    }

    /**
     *添加activity
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /**
     * 移除activity
     */
    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 获取栈顶activity
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    /**
     * 清理栈
     */
    fun finishAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出app
     */
    fun exitApp(context: Context){
        finishAllActivity()
        val activityManager=context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)

    }
}