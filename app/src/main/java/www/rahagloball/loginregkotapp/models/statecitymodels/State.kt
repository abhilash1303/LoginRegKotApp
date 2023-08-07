package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class State(
    @SerializedName("statebl")
    val states: ArrayList<States>?
): Serializable



//class State : Serializable {
//    @SerializedName("statebl")
//    var states: ArrayList<States>? = null
//
//    constructor() {}
//    constructor(states: ArrayList<States>?) {
//        this.states = states
//    }
//}