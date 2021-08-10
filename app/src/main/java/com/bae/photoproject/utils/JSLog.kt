package com.bae.photoproject.utils

import android.util.Log
import com.bae.photoproject.application.JSApplication

object JSLog
{
    /**
     * Log Level Error
     */
    fun e(message: String) {
        if (JSApplication.debug) Log.e(Constants.LOG_TAG, buildLogMsg(message))
    }

    /**
     * Log Level Warning
     */
    fun w(message: String) {
        if (JSApplication.debug) Log.w(Constants.LOG_TAG, buildLogMsg(message))
    }

    /**
     * Log Level Information
     */
    fun i(message: String) {
        if (JSApplication.debug) Log.i(Constants.LOG_TAG, buildLogMsg(message))
    }

    /**
     * Log Level Debug
     */
    fun d(message: String) {
        if (JSApplication.debug) Log.d(Constants.LOG_TAG, buildLogMsg(message))
    }

    /**
     * Log Level Verbose
     */
    fun v(message: String) {
        if (JSApplication.debug) Log.v(Constants.LOG_TAG, buildLogMsg(message))
    }


    fun buildLogMsg(message: String): String {

        val ste = Thread.currentThread().stackTrace[4]

        val sb = StringBuilder()

        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("]")
        sb.append(message)

        return sb.toString()

    }
}