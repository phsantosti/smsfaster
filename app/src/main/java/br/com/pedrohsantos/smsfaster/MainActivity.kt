package br.com.pedrohsantos.smsfaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMessage: EditText = findViewById(R.id.edit_text_message);
        editTextMessage.addTextChangedListener(this)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val textViewCounter: TextView = findViewById(R.id.text_view_counter)
        textViewCounter.text = String.format("%d / %s", s?.length, getString(R.string.max))
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    fun clearMessage(v: View){
        val editTextMessage: EditText = findViewById(R.id.edit_text_message)
        editTextMessage.text.clear()
    }

    fun sendSMS(v: View){
        val editTextDDI: EditText = findViewById(R.id.edit_text_ddi)
        val editTextDDD: EditText = findViewById(R.id.edit_text_ddd)
        val editTextNumber: EditText = findViewById(R.id.edit_text_number)
        val editTextMessage: EditText = findViewById(R.id.edit_text_message)

        try{
            val number: String = String.format("+%s%s%s", editTextDDI.text, editTextDDD.text, editTextNumber.text)
            val message: String = editTextMessage.text.toString()

            val SMSManager: SmsManager = SmsManager.getDefault()
            SMSManager.sendTextMessage(number, null, message, null, null)
            Toast.makeText(this, R.string.sms_sent_successfully, Toast.LENGTH_LONG).show()
        }catch (ex: Exception){
            ex.printStackTrace()
            Toast.makeText(this, R.string.sms_error, Toast.LENGTH_LONG).show()
        }
    }
}
