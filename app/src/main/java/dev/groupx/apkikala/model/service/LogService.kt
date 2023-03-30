package dev.groupx.apkikala.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}