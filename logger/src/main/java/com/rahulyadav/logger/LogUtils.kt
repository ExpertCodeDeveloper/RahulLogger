package com.rahulyadav.logger

import android.util.Log

class LogUtils(var classTag : Class<Any>, var mLevel : LevelLogger = LevelLogger.NIL) {

    private var tagger : String = String.format("[%s:%s]", classTag.simpleName, line());

    /**
     * Set the print lowest level. It will set [LevelLogger.NIL] if the level is null.
     *
     * @param level The lowest level can print log.
     */
    fun setLevel(level: LevelLogger) {
        mLevel = level
    }

    /**
     * Send a [LevelLogger.V] log message.
     *
     * @param msg The message you would like logged.
     */
    fun v(msg: String?) {
        Log.e(mLevel.name, "$tagger $msg")
    }

    companion object {

        fun log(levelLogger: LevelLogger, line: String, vararg log: Any) {
            val logging = StringBuilder()
            for (i in log.indices) {
                if ((i + 1) == log.size) {
                    logging.append(log[i])
                } else {
                    logging.append(log[i]).append("\n")
                }
            }
           Log.e(levelLogger.name, "$line :: $logging")
        }

        fun line(): String {
            val calling = Thread.currentThread().stackTrace[3]
            return String.Companion.format("(%s:%s)", calling.fileName, calling.lineNumber)
        }
    }
}