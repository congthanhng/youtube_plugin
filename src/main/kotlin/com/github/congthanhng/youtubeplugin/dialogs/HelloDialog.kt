package com.github.congthanhng.youtubeplugin.dialogs

import com.github.congthanhng.youtubeplugin.persistents.HelloSetting
import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JPasswordField
import javax.swing.JTextField

class HelloDialog : DialogWrapper(true) {

    private val panel = JPanel(GridBagLayout())
    private val txtMode = JTextField()
    private val txtUserName = JTextField()
    private val txtPassword = JPasswordField()

    init {
        init()
        title = "My Dialog"
        val state = HelloSetting.getInstance().state
        if(state != null){
            txtMode.text = state.mode
        }
        val credentialAttributes = CredentialAttributes("Hello", )
        val credentials = PasswordSafe.instance.get(credentialAttributes)
        txtPassword.text = credentials?.getPasswordAsString().toString()
        txtUserName.text = credentials?.userName
    }
    override fun createCenterPanel(): JComponent? {
        val gb = GridBag()
            .setDefaultInsets(Insets(0,0,AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)
        panel.add(label("MOde"), gb.nextLine().next().weightx(0.2))
        panel.add(txtMode, gb.nextLine().next().weightx(0.8))
        panel.add(label("UserName"), gb.nextLine().next().weightx(0.2))
        panel.add(txtUserName, gb.nextLine().next().weightx(0.8))
        panel.add(label("Password"), gb.nextLine().next().weightx(0.2))
        panel.add(txtPassword, gb.nextLine().next().weightx(0.8))
        return panel
    }

    private fun label(text: String): JComponent{
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0,5,2,0)
        return label
    }

    override fun doOKAction() {
        val mode = txtMode.text
        val userName = txtUserName.text
        val password = txtPassword.password
        val state = HelloSetting.getInstance().state
        state?.mode = mode

        val credentialAttributes = CredentialAttributes("Hello", "MyKey")
        val credential = Credentials(userName, password)
        PasswordSafe.instance.set(credentialAttributes, credential)
        super.doOKAction()
    }
}