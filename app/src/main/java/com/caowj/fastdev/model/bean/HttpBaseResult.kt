package com.caowj.fastdev.model.bean

class HttpBaseResult<T> {
    var status = 0
    private val cost: Long = 0
    private val timestamp: Long = 0
    var message: String? = null
    var result: T? = null

    val isSuccess: Boolean
        get() = status == STATUS_OK

    companion object {
        @JvmField
        var STATUS_EXCEPTION = -1

        @JvmField
        var STATUS_FAILURE = -2

        @JvmField
        var STATUS_NETWORK_UNCONNECTED = -10

        @JvmField
        var STATUS_NETWORK_READTIME_OUT = -11

        @JvmField
        var STATUS_OK = 200
    }
}
