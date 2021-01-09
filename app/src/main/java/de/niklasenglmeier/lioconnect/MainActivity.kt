package de.niklasenglmeier.lioconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import de.niklasenglmeier.lioconnect.network.NetworkCallbacks
import de.niklasenglmeier.lioconnect.network.NetworkManager

class MainActivity : AppCompatActivity(), View.OnClickListener, NetworkCallbacks {

    lateinit var buttonRed: ImageView
    lateinit var buttonGreen: ImageView
    lateinit var buttonBlue: ImageView
    lateinit var buttonYellow: ImageView
    lateinit var buttonMagenta: ImageView
    lateinit var buttonTorquiose: ImageView
    lateinit var buttonWhite: ImageView
    lateinit var buttonBlack: ImageView

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

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.buttonRed -> {
                NetworkManager.instance.queueMessage("255 0 0")
            }
            R.id.buttonGreen -> {
                NetworkManager.instance.queueMessage("0 255 0")
            }
            R.id.buttonBlue -> {
                NetworkManager.instance.queueMessage("0 0 255")
            }
            R.id.buttonYellow -> {
                NetworkManager.instance.queueMessage("128 128 0")
            }
            R.id.buttonMagenta -> {
                NetworkManager.instance.queueMessage("128 0 128")
            }
            R.id.buttonTorquiose -> {
                NetworkManager.instance.queueMessage("0 128 128")
            }
            R.id.buttonWhite -> {
                NetworkManager.instance.queueMessage("128 128 128")
            }
            R.id.buttonBlack -> {
                NetworkManager.instance.queueMessage("0 0 0")
            }
        }
    }

    override fun onConnect() {
        runOnUiThread{
            Toast.makeText(applicationContext, "Connected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onReceiveMessage(_message: String?) {

    }
}