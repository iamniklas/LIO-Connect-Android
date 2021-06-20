package de.niklasenglmeier.lioconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import de.niklasenglmeier.lioconnect.lio.LEDDataBundle
import de.niklasenglmeier.lioconnect.lio.ProcedureBundleFields
import de.niklasenglmeier.lioconnect.lio.ProcedureTypes
import de.niklasenglmeier.lioconnect.lio.colors.ColorRGB
import de.niklasenglmeier.lioconnect.network.NetworkCallbacks
import de.niklasenglmeier.lioconnect.network.NetworkManager

class MainActivity : AppCompatActivity(), View.OnClickListener, NetworkCallbacks {

    private lateinit var buttonRed: ImageView
    private lateinit var buttonGreen: ImageView
    private lateinit var buttonBlue: ImageView
    private lateinit var buttonYellow: ImageView
    private lateinit var buttonMagenta: ImageView
    private lateinit var buttonTorquiose: ImageView
    private lateinit var buttonWhite: ImageView
    private lateinit var buttonBlack: ImageView

    private var modulo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkManager.instance.setListener(this)

        buttonRed = findViewById(R.id.buttonRed)
        buttonRed.setOnClickListener(this)

        buttonGreen = findViewById(R.id.buttonGreen)
        buttonGreen.setOnClickListener(this)

        buttonBlue = findViewById(R.id.buttonBlue)
        buttonBlue.setOnClickListener(this)

        buttonYellow = findViewById(R.id.buttonYellow)
        buttonYellow.setOnClickListener(this)

        buttonMagenta = findViewById(R.id.buttonMagenta)
        buttonMagenta.setOnClickListener(this)

        buttonTorquiose = findViewById(R.id.buttonTorquiose)
        buttonTorquiose.setOnClickListener(this)

        buttonWhite = findViewById(R.id.buttonWhite)
        buttonWhite.setOnClickListener(this)

        buttonBlack = findViewById(R.id.buttonBlack)
        buttonBlack.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        if(!NetworkManager.instance.available) return

        val dataBundle = LEDDataBundle()
        dataBundle[ProcedureBundleFields.COLOR_PRIMARY] = ColorRGB.black
        dataBundle[ProcedureBundleFields.COLOR_SECONDARY] = ColorRGB(128, 128, 128)
        dataBundle[ProcedureBundleFields.DURATION] = 0.25f
        val data = Gson().toJson(dataBundle)
        Log.e("lioconnect", data)
        NetworkManager.instance.queueMessage("${ProcedureTypes.FadeToUniformColor}:${data}")
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.buttonRed -> {
                onResume()
                //NetworkManager.instance.queueMessage("$modulo 255 0 0")
            }
            R.id.buttonGreen -> {
                NetworkManager.instance.queueMessage("$modulo 0 255 0")
            }
            R.id.buttonBlue -> {
                NetworkManager.instance.queueMessage("$modulo 0 0 255")
            }
            R.id.buttonYellow -> {
                NetworkManager.instance.queueMessage("$modulo 128 128 0")
            }
            R.id.buttonMagenta -> {
                NetworkManager.instance.queueMessage("$modulo 128 0 128")
            }
            R.id.buttonTorquiose -> {
                NetworkManager.instance.queueMessage("$modulo 0 128 128")
            }
            R.id.buttonWhite -> {
                NetworkManager.instance.queueMessage("$modulo 128 128 128")
            }
            R.id.buttonBlack -> {
                NetworkManager.instance.queueMessage("$modulo 0 0 0")
            }
        }
        modulo++
    }

    override fun onConnect() {
        runOnUiThread{
            Toast.makeText(applicationContext, "Connected", Toast.LENGTH_SHORT).show()
            onResume()
        }
    }

    override fun onReceiveMessage(_message: String?) {

    }
}