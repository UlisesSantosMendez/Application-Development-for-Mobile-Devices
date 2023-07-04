package com.mcuhq.simplebluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status

    // GUI Components
    private TextView mBluetoothStatus;
    private TextView estado,mostrarDatos;
    private Button mScanBtn, btMagnetismo, btVoltaje, btTemperatura, btHelice;
    private Button mOffBtn;
    private Button mListPairedDevicesBtn;
    private ListView mDevicesListView;

    private boolean esBtnMagnetismo, esBtnVoltaje, esBtnTemperatura, isEsBtnHelice;


    private BluetoothAdapter mBTAdapter;



    private Set<BluetoothDevice> mPairedDevices;
    private ArrayAdapter<String> mBTArrayAdapter;

    private Handler mHandler; // Our main handler that will receive callback notifications
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data

    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path

    private boolean encendido=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothStatus = (TextView)findViewById(R.id.bluetooth_status);
        mScanBtn = (Button)findViewById(R.id.scan);
        mOffBtn = (Button)findViewById(R.id.off);
        mListPairedDevicesBtn = (Button)findViewById(R.id.paired_btn);
        estado=(TextView) findViewById(R.id.Estado);
        btMagnetismo=(Button)findViewById(R.id.btnMagnetismo);
        btVoltaje = (Button)findViewById(R.id.btnVoltaje);
        btTemperatura = (Button)findViewById(R.id.btnTemperatura);
        btHelice = (Button)findViewById(R.id.btnHelice);
        mostrarDatos = (TextView)findViewById(R.id.mostrarDatos);


        esBtnMagnetismo = false;
        esBtnVoltaje = false;
        esBtnTemperatura = false;
        isEsBtnHelice = false;





        mBTArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);


        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio



        mDevicesListView = (ListView)findViewById(R.id.devices_list_view);

        
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);

        // Ask for location permission if not already allowed
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


        btMagnetismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esBtnMagnetismo = true;
                esBtnVoltaje = false;
                esBtnTemperatura = false;
                isEsBtnHelice = false;
            }
        });

        btVoltaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esBtnMagnetismo = false;
                esBtnVoltaje = true;
                esBtnTemperatura = false;
                isEsBtnHelice = false;
            }
        });

        btTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esBtnMagnetismo = false;
                esBtnVoltaje = false;
                esBtnTemperatura = true;
                isEsBtnHelice = false;
            }
        });

        btHelice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEsBtnHelice = true;
                esBtnMagnetismo = false;
                esBtnVoltaje = false;
                esBtnTemperatura = false;
            }
        });

        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 2){
                    String readMessage = null;
                    String sub = null;
                    readMessage = new String((byte[]) msg.obj, StandardCharsets.UTF_8);
                    //System.out.println("Lo que llega: "+readMessage);
                    char bien=readMessage.charAt(0);

                    sub = readMessage.substring(1,6);

                    if(bien == '1' && esBtnMagnetismo == true){
                        mostrarDatos.setText(sub + " weber");
                    }
                    if(bien == '2' && esBtnVoltaje == true){
                        mostrarDatos.setText(sub + " V");
                    }
                    if(bien == '3' && esBtnTemperatura == true){
                        mostrarDatos.setText(sub + " ºCelsius");
                    }
                    if(bien == '4' && isEsBtnHelice == true){
                        mostrarDatos.setText(sub + " Rpm");
                    }


                }

                if(msg.what == 3){
                    char[] sConnected;
                    if(msg.arg1 == 1) {
                        mBluetoothStatus.setText("Conectado a " + msg.obj);
                        btMagnetismo.setEnabled(true);
                        btVoltaje.setEnabled(true);
                        btTemperatura.setEnabled(true);
                        btHelice.setEnabled(true);
                    }else {
                        mBluetoothStatus.setText("Falló");
                    }
                }
            }
        };

        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            mBluetoothStatus.setText(getString(R.string.sBTstaNF));
            Toast.makeText(getApplicationContext(),"No se soporta el bluethoot",Toast.LENGTH_SHORT).show();
        }
        else {




            mScanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bluetoothOn();
                }
            });

            mOffBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    bluetoothOff();
                }
            });

            mListPairedDevicesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    listPairedDevices();
                }
            });


        }
    }

    private void bluetoothOn(){
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            mBluetoothStatus.setText(getString(R.string.BTEnable));
            Toast.makeText(getApplicationContext(),"Bluethoot encendido",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(),"El bluethoot ya esta activo", Toast.LENGTH_SHORT).show();
        }
    }

    // Enter here after user selects "yes" or "no" to enabling radio
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data){
        // Check which request we're responding to
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                mBluetoothStatus.setText("Conectado");
            }
            else
                mBluetoothStatus.setText(getString(R.string.sDisabled));
        }
    }

    private void bluetoothOff(){
        mBTAdapter.disable(); // turn off
        mBluetoothStatus.setText(getString(R.string.sBTdisabl));
        Toast.makeText(getApplicationContext(),"Bluetooth apagado", Toast.LENGTH_SHORT).show();
    }


    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name to the list
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mBTArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    private void listPairedDevices(){
        mBTArrayAdapter.clear();
        mPairedDevices = mBTAdapter.getBondedDevices();
        if(mBTAdapter.isEnabled()) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), "Mostrando dispositivos", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Fallo en el bluethoot", Toast.LENGTH_SHORT).show();
    }




    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(!mBTAdapter.isEnabled()) {
                Toast.makeText(getBaseContext(), "Fallo en el bluethoot", Toast.LENGTH_SHORT).show();
                return;
            }

            mBluetoothStatus.setText(getString(R.string.cConnet));
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) view).getText().toString();
            final String address = info.substring(info.length() - 17);
            final String name = info.substring(0,info.length() - 17);

            // Spawn a new thread to avoid blocking the GUI one
            new Thread()
            {
                @Override
                public void run() {
                    boolean fail = false;

                    BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                    try {
                        mBTSocket = createBluetoothSocket(device);
                    } catch (IOException e) {
                        fail = true;
                        Toast.makeText(getBaseContext(), "Fallo en el socket", Toast.LENGTH_SHORT).show();
                    }
                    // Establish the Bluetooth socket connection.
                    try {
                        mBTSocket.connect();
                    } catch (IOException e) {
                        try {
                            fail = true;
                            mBTSocket.close();
                            mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                    .sendToTarget();
                        } catch (IOException e2) {
                            //insert code to deal with this
                            Toast.makeText(getBaseContext(), "Fallo en el crear Socket", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!fail) {
                        mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                        mConnectedThread.start();

                        mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name).sendToTarget();
                    }
                }
            }.start();
        }
    };

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }
}
