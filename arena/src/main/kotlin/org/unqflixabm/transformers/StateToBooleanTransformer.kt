package org.unqflixabm.transformers

import domain.Available
import domain.ContentState
import domain.Unavailable
import org.uqbar.arena.bindings.ValueTransformer

// Made for WindowUNQflix, for the State column
class StateToBooleanTransformer : ValueTransformer<ContentState, Boolean> {

    override fun getModelType() = ContentState::class.java
    override fun getViewType() = Boolean::class.java

    override fun modelToView(valueFromModel: ContentState): Boolean {
        return valueFromModel.toString().contains("Available")
    }

    override fun viewToModel(valueFromView: Boolean): ContentState {
        var stateObj: ContentState
        if (valueFromView) {
            stateObj = Available()
        } else {
            stateObj = Unavailable()
        }
        return stateObj
    }
}