/**
 * Copyright (c) 2016 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of idempierewsc.
 * 
 * idempierewsc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * idempierewsc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with idempierewsc.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.idempiere.sandbox_android;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import org.idempiere.webservice.client.base.DataRow;
import org.idempiere.webservice.client.base.Enums;
import org.idempiere.webservice.client.base.Field;
import org.idempiere.webservice.client.base.LoginRequest;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.CompositeOperationRequest;
import org.idempiere.webservice.client.request.CreateDataRequest;
import org.idempiere.webservice.client.request.QueryDataRequest;
import org.idempiere.webservice.client.request.ReadDataRequest;
import org.idempiere.webservice.client.response.CompositeResponse;
import org.idempiere.webservice.client.response.WindowTabDataResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView lblQueryResult;
    private ImageView imgQueryLogo;
    private EditText txtUrl;
    private EditText txtCreateName;
    private EditText txtCreateValue;
    private EditText txtCreateTaxID;
    private EditText txtQueryName;
    private ImageButton btnSelectLogo;
    private byte[] byteImgCreate;
    private byte[] byteImgQuery;

    private static final int ACTIVITY_SELECT_IMG = 100;

    public LoginRequest getLogin() {
        LoginRequest login = new LoginRequest();
        login.setUser("SuperUser");
        login.setPass("System");
        login.setClientID(11);
        login.setRoleID(102);
        login.setOrgID(0);
        return login;
    }

    public String getUrlBase() {
        return txtUrl.getText().toString();
    }

    public WebServiceConnection getClient() {
        WebServiceConnection client = new WebServiceConnection();
        client.setAttempts(1);
        client.setTimeout(7000);
        client.setAttemptsTimeout(1000);
        client.setUrl(getUrlBase());
        client.setAppName("Android Test WS Client");
        return client;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // TABS

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabSpec tabCreateBPartner = tabHost.newTabSpec("tabCreateBPartner");
        tabCreateBPartner.setIndicator("Create BPartner");
        tabCreateBPartner.setContent(R.id.tabCreateBPartner);
        tabHost.addTab(tabCreateBPartner);

        TabSpec tabQueryBPartner = tabHost.newTabSpec("tabQueryBPartner");
        tabQueryBPartner.setIndicator("Query BPartner");
        tabQueryBPartner.setContent(R.id.tabQueryBPartner);
        tabHost.addTab(tabQueryBPartner);

        // QUERY

        lblQueryResult = (TextView) findViewById(R.id.lblQueryResult);
        imgQueryLogo = (ImageView) findViewById(R.id.imgQueryLogo);
        txtQueryName = (EditText) findViewById(R.id.txtQueryName);
        txtUrl = (EditText) findViewById(R.id.txtUrl);

        Button btnSendQuery = (Button) findViewById(R.id.btnSendQuery);
        btnSendQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    QueryDataRequest ws = new QueryDataRequest();
                    ws.setWebServiceType("QueryBPartnerTest");
                    ws.setLogin(getLogin());
                    ws.setLimit(1);

                    DataRow data = new DataRow();
                    data.addField("Name", "%" + txtQueryName.getText().toString() + "%");
                    ws.setDataRow(data);

                    WebServiceConnection client = getClient();

                    String temp = "";

                    WindowTabDataResponse response = client.sendRequest(ws);

                    if (response.getStatus() == Enums.WebServiceResponseStatus.Error) {
                        printError(response.getErrorMessage());
                    } else {
                        Log.i("info", "Total rows: " + response.getNumRows());
                        temp = "";
                        for (int i = 0; i < response.getDataSet().getRowsCount(); i++) {
                            Log.i("info", "Row: " + (i + 1));
                            for (int j = 0; j < response.getDataSet().getRow(i).getFieldsCount(); j++) {
                                Field field = response.getDataSet().getRow(i).getFields().get(j);
                                Log.i("info", "Column: " + field.getColumn() + " = " + field.getValue());
                                temp += field.getColumn() + " = " + field.getValue() + "\n";

                                if (field.getColumn().equals("Logo_ID") && !field.getValue().toString().isEmpty()) {
                                    Log.i("info", "Get Logo");

                                    ReadDataRequest readImage = new ReadDataRequest();
                                    readImage.setWebServiceType("ReadImageTest");
                                    readImage.setLogin(getLogin());
                                    readImage.setRecordID(field.getIntValue());

                                    WindowTabDataResponse responseRead = client.sendRequest(readImage);

                                    if (responseRead.getStatus() == Enums.WebServiceResponseStatus.Error) {
                                        printError(responseRead.getErrorMessage());
                                    } else if (responseRead.getStatus() == Enums.WebServiceResponseStatus.Unsuccessful) {
                                        printError("Unsuccessful");
                                    } else {
                                        if (responseRead.getDataSet().getRow(0).getField("BinaryData") != null && !responseRead.getDataSet().getRow(0).getField("BinaryData").toString().isEmpty())
                                            byteImgQuery = responseRead.getDataSet().getRow(0).getField("BinaryData").getByteValue();
                                    }
                                }
                            }
                        }
                    }

                    Log.i("time request", String.valueOf(client.getTimeRequest()));
                    Log.i("attempts", String.valueOf(client.getAttemptsRequest()));

                    lblQueryResult.setText(temp);
                    if (byteImgQuery != null)
                        imgQueryLogo.setImageBitmap(BitmapFactory.decodeByteArray(byteImgQuery, 0, byteImgQuery.length));

                } catch (Exception e) {
                    e.printStackTrace();
                    printError(e.getMessage());
                }
            }
        });

        // CREATE

        txtCreateName = (EditText) findViewById(R.id.txtCreateName);
        txtCreateValue = (EditText) findViewById(R.id.txtCreateValue);
        txtCreateTaxID = (EditText) findViewById(R.id.txtCreateTaxID);

        btnSelectLogo = (ImageButton) findViewById(R.id.btnSelectLogo);
        btnSelectLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("info", "Select Image");
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ACTIVITY_SELECT_IMG);
            }
        });

        Button btnSendCreate = (Button) findViewById(R.id.btnSendCreate);
        btnSendCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("info", "Send Create");

                try {
                    CompositeOperationRequest compositeOperation = new CompositeOperationRequest();
                    compositeOperation.setLogin(getLogin());
                    compositeOperation.setWebServiceType("CompositeBPartnerTest");

                    CreateDataRequest createImage = new CreateDataRequest();
                    createImage.setWebServiceType("CreateImageTest");


                    DataRow data = new DataRow();
                    data.addField("Name", txtCreateName.getText().toString());
                    data.addField("Description", "Image from Android");
                    if (byteImgCreate != null)
                        data.addField("BinaryData", byteImgCreate);

                    createImage.setDataRow(data);

                    CreateDataRequest createBP = new CreateDataRequest();
                    createBP.setWebServiceType("CreateBPartnerTest");

                    DataRow dataBP = new DataRow();
                    dataBP.addField("Name", txtCreateName.getText().toString());
                    dataBP.addField("Value", txtCreateValue.getText().toString());
                    dataBP.addField("TaxID", txtCreateTaxID.getText().toString());
                    dataBP.addField("Logo_ID", "@AD_Image.AD_Image_ID");
                    createBP.setDataRow(dataBP);

                    compositeOperation.addOperation(createImage);
                    compositeOperation.addOperation(createBP);

                    WebServiceConnection client = getClient();

                    CompositeResponse response = client.sendRequest(compositeOperation);

                    if (response.getStatus() == Enums.WebServiceResponseStatus.Error) {
                        printError(response.getErrorMessage());
                    } else {
                        printSuccess("Created");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    printError(e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case ACTIVITY_SELECT_IMG:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        yourSelectedImage = Bitmap.createScaledBitmap(yourSelectedImage, 400, 400, false);
                        yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, bos);
                        byteImgCreate = bos.toByteArray();
                        bos.close();
                        btnSelectLogo.setImageBitmap(yourSelectedImage);
                    } catch (Exception e) {
                        printError(e.getMessage());
                    }
                }
        }
    }

    private void printError(String msg) {
        Log.e("printError", msg);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MainActivity.this);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle("Error");
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void printSuccess(String msg) {
        Log.i("printSuccess", msg);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MainActivity.this);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle("Success");
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
