package com.example.bluetoothapplication

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private var btpermission = false
    private lateinit var pairedDevices: Set<BluetoothDevice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bluetoothOn_btn = findViewById<Button>(R.id.turnOn_btn)
        val bluetoothOff_btn = findViewById<Button>(R.id.turnOff_btn)
        val pairedDevices = findViewById<Button>(R.id.showPairedDevices_btn)
        bluetoothOn_btn.setOnClickListener {
            val bluetoothManager: BluetoothManager =  getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
            if(bluetoothAdapter == null){
                Toast.makeText(this, "Device does not supports bluetooth", Toast.LENGTH_SHORT).show()
            }else{
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){

                    bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
                }else{
                    bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
                }
            }
        }
        bluetoothOff_btn.setOnClickListener {
            val bluetoothManager: BluetoothManager =  getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_ADMIN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@setOnClickListener
            }
            bluetoothAdapter.disable()
            btOFF()
        }
        pairedDevices.setOnClickListener {
            val bluetoothManager: BluetoothManager =  getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
            if (bluetoothAdapter.isEnabled) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@setOnClickListener
                }
                var pairedDevices = bluetoothAdapter.bondedDevices
                val devicelist = ArrayList<String>()
                for (device in pairedDevices) {
                    devicelist.add(device.name + "\n" + device.address)
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, devicelist)
                val list_paired_devices = findViewById<ListView>(R.id.list_paired_devices)
                list_paired_devices.adapter = adapter
            } else {
                Toast.makeText(applicationContext, "Please turn on Bluetooth", Toast.LENGTH_LONG)
                    .show()
            }}
    }
    private val btActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result: androidx.activity.result.ActivityResult ->
        if (result.resultCode == RESULT_OK){
            btON()
        }
    }
    private val bluetoothPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted:Boolean ->
        if (isGranted){
            val bluetoothManager: BluetoothManager =  getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
            btpermission = true
            if(bluetoothAdapter?.isEnabled == false){
                val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                btActivityResultLauncher.launch(enableBTIntent)
            }else{
                btON()
            }
        }else{
            btpermission = false
        }
    }
    private fun btON(){Toast.makeText(this, "Bluetooth has been enabled", Toast.LENGTH_SHORT).show() }
    private fun btOFF(){Toast.makeText(this, "Bluetooth has been disabled", Toast.LENGTH_SHORT).show() }
}