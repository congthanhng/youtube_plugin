package com.github.congthanhng.youtubeplugin.persistents

import com.github.congthanhng.youtubeplugin.states.HelloState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage


@State(
    name = "Hello",
    storages = [Storage("hello-demo.xml")]
)
class HelloSetting: PersistentStateComponent<HelloState> {

    private var pluginState = HelloState()
    override fun getState(): HelloState {
        return pluginState
    }

    override fun loadState(state: HelloState) {
        pluginState = state
    }

    companion object{
        @JvmStatic
        fun getInstance():PersistentStateComponent<HelloState>{
            return ServiceManager.getService(HelloSetting::class.java)
        }
    }
}