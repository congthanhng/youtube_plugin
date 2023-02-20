package com.github.congthanhng.youtubeplugin.tool_window;

import com.intellij.ui.jcef.JBCefBrowser;

import javax.swing.*;

public class MyToolWindow {

    private JPanel myToolWindowContent;

    MyToolWindow(){
        myToolWindowContent.add(new JBCefBrowser("https://www.youtube.com/").getComponent());
    }
    public JPanel getContent() {
        return myToolWindowContent;
    }

}
