package com.github.congthanhng.youtubeplugin.actions

import com.github.congthanhng.youtubeplugin.dialogs.HelloDialog
import com.github.congthanhng.youtubeplugin.popups.HelloPopup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory

public class HelloAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val myPpopuP = HelloPopup("My First POpup", mutableListOf("apple", "google", "microSoft"))
        if (e.project != null) {
            JBPopupFactory.getInstance().createListPopup(myPpopuP, 5).showCenteredInCurrentWindow(e.project!!)
        }
    }

    private fun showCustomDialog() {
        val helloDialog = HelloDialog()
        if (helloDialog.showAndGet()) {
            helloDialog.close(23)
        }
    }

    private fun getUserName(e: AnActionEvent) {
        val name =
            Messages.showInputDialog(e.project, "Enter Your name", "My Idea Demo", Messages.getQuestionIcon())
        Messages.showMessageDialog(e.project, "Your name is: $name", "Get INfo", Messages.getInformationIcon())
    }

    private fun showFileDialog(e: AnActionEvent) {
        val fileChooserDescriptor = FileChooserDescriptor(
            false, false, false, false, false, false
        )

        fileChooserDescriptor.title = "Please Pick your Directory"
        fileChooserDescriptor.description = "THis is an demo for Action"

        FileChooser.chooseFile(fileChooserDescriptor, e.project, null) {
            Messages.showMessageDialog(e.project, it.path, "path", Messages.getInformationIcon())
        }
    }
}