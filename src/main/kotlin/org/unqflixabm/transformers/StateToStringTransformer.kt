package org.unqflixabm.transformers

import domain.Available
import domain.ContentState
import domain.Unavailable
import org.uqbar.arena.bindings.ValueTransformer

// Made for WindowUNQflix, for the State column
class StateToStringTransformer : ValueTransformer<ContentState, String> {
    //TODO: private val pattern = ...
    //TODO: private val formatter = ...

    override fun getModelType() = ContentState::class.java
    override fun getViewType() = String::class.java

    override fun modelToView(valueFromModel: ContentState): String {
        var stateStr: String = valueFromModel.toString()
        if (stateStr.contains("Available")) {
            stateStr = "Available"
        } else if (stateStr.contains("Unavailable")) {
            stateStr = "Unavailable"
        }
        return stateStr
    }

    override fun viewToModel(valueFromView: String): ContentState {
        var stateObj: ContentState
        if (valueFromView == "Available") {
            stateObj = Available()
        } else {
            stateObj = Unavailable()
        }
        return stateObj
    }
}