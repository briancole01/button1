package au.brian.button1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ExerciseFragment : Fragment() {

    var currentState = mutableMapOf<Int, Int>()
    var countButtons = listOf<Int>(R.id.countButton1, R.id.countButton2, R.id.countButton3, R.id.countButton4, R.id.countButton5)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            Log.i("ButtonOnClick", "** onClick for button Back")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        for (buttonId in countButtons) {
            currentState.put(buttonId, 0)
            val button = view.findViewById<Button>(buttonId)
            button.setOnClickListener {
                if (it is Button) {
                    currentState.put(buttonId, currentState[buttonId]!! + 1)
                    Log.i("ButtonOnClick", "** $buttonId new text is ${currentState[buttonId]}")
                    it.setText(currentState[buttonId].toString())
                }
            }
            button.setOnLongClickListener {
                if (it is Button) {
                    if (currentState[buttonId] != null && currentState[buttonId]!! > 0) {
                        currentState.put(buttonId, 0)
                    }
                    else {
                        currentState.put(buttonId, 5)
                    }
                    Log.i("ButtonOnClick", "** $buttonId new text is ${currentState[buttonId]}")
                    it.setText(currentState[buttonId].toString())
                    true
                }
                else { false }
            }
        }
    }
        /*
        var currentState = mutableMapOf<Int, Int>()
        for (buttonId in listOf<Int>(R.id.countButton1, R.id.countButton2)) {
            currentState[buttonId] = 0
            view.findViewById<Button>(buttonId).setOnClickListener {
                View.OnClickListener { view ->
                    Log.i("ButtonOnClick","** onClick for button $buttonId")
                    if (view is Button) {
                        val state = currentState[buttonId]
                        if (state == 0) {
                            currentState[buttonId] = 1
                        } else {
                            currentState[buttonId] = 0
                        }
                        view.setText(currentState[buttonId].toString());
                    }
                }
            }
        }
        */
}