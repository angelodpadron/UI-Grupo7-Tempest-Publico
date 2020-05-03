package org.unqflixabm.transformers

import domain.ContentState
import org.uqbar.arena.bindings.ValueTransformer

// Made for WindowUNQflix, for the State column
class StateToBooleanTransformer : ValueTransformer<ContentState, Boolean> {
    //TODO: private val pattern = ...
    //TODO: private val formatter = ...

    override fun getModelType() = ContentState::class.java
    override fun getViewType() = Boolean::class.java

    override fun modelToView(valueFromModel: ContentState): Boolean {
        return valueFromModel.toString().contains("Available")
    }

    override fun viewToModel(valueFromView: Boolean): ContentState {
        TODO("ContentState.Available si boolean es True, ContentState.Unavailable si boolean es False")
    }
}