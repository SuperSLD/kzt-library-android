package com.example.app_ui.common

import java.util.logging.Level
import java.util.logging.Logger

fun logDebug(text: String) {
    Logger.getLogger("debug logger").log(Level.INFO, text)
}