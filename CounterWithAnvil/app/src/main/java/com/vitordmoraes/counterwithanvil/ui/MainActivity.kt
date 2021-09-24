package com.vitordmoraes.counterwithanvil.ui


import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.vitordmoraes.counterwithanvil.actions.ActionCreator
import com.vitordmoraes.counterwithanvil.model.AppState
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.padding
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView

class MainActivity : AppLifecycleActivity() {

//    private lateinit var countBox: Box<Count>
//    private val actCount = Count(0)
//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(object : RenderableView(this) {
            override fun view() {
                counter()
            }
        })
//        countBox = ObjectBox.store.boxFor(Count::class.java)
//        hasBoxSaved(actCount)
    }

//    private fun hasBoxSaved(count: Count) {
//        if (count.Value == 0) {
//            actCount.Value = countBox[0].Value
//        } else {
//            actCount.Value = 0
//        }
//    }


    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        if (newState != oldState) return true
        return false
    }

    override fun onChanged(state: AppState) {
        runOnUiThread {
            Toast.makeText(
                this, "Your actual score is ${state.count}",
                Toast.LENGTH_SHORT
            ).show()
//            actCount.Value = state.count
        }
//        countBox.put(actCount)
    }


    private fun counter() {
        linearLayout {
            orientation(LinearLayout.VERTICAL)
            size(FILL, FILL)
            linearLayout {
                gravity(CENTER)
                orientation(LinearLayout.HORIZONTAL)
                padding(dip(8))
                button {
                    size(WRAP, WRAP)
                    text("Increment")
                    onClick { ActionCreator.instance.addValue() }
                }
                button {
                    size(WRAP, WRAP)
                    text("Decrement")
                    onClick { ActionCreator.instance.removeValue() }
                }
            }

            textView {
                margin(18)
                size(WRAP, WRAP)
                layoutGravity(CENTER)
                textSize(48f)
                text(state.count.toString())
            }
        }
    }
}