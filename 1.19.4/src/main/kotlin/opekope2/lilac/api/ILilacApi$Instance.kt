package opekope2.lilac.api

import opekope2.lilac.impl.LilacApi

@Suppress("unused", "ClassName")
private object `ILilacApi$Instance` {
    @JvmStatic
    fun get(): ILilacApi = LilacApi
}
