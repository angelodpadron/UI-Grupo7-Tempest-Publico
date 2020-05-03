package org.unqflixabm.transformers

import domain.ContentState
import org.uqbar.arena.bindings.ValueTransformer

// Made for WindowUNQflix, for the State column
class StateToStringTransformer : ValueTransformer<ContentState, String> {
    //TODO: private val pattern = ...
    //TODO: private val formatter = ...

    override fun getModelType() = ContentState::class.java
    override fun getViewType() = String::class.java

    override fun modelToView(valueFromModel: ContentState): String {
        var state_str: String = valueFromModel.toString()
        if (state_str.contains("Available")) {
            state_str = "Available"
        } else if (state_str.contains("Unavailable")) {
            state_str = "Unavailable"
        }
        return state_str
    }

    override fun viewToModel(valueFromView: String): ContentState {
        TODO("ContentState.Available si string es 'Available', ContentState.Unavailable si string es 'Unavailable'")
    }
}