package com.github.congthanhng.youtubeplugin.popups

import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

class HelloPopup(title: String, fruits: List<String>) : BaseListPopupStep<String>(title, fruits) {
    override fun isSpeedSearchEnabled(): Boolean {
        return true
    }

    override fun onChosen(selectedValue: String?, finalChoice: Boolean): PopupStep<*>? {
        return PopupStep.FINAL_CHOICE
    }
}