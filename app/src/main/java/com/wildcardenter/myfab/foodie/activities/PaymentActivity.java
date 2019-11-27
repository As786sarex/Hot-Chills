package com.wildcardenter.myfab.foodie.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.helpers.JsonParse;
import com.wildcardenter.myfab.foodie.models.Order;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PaymentActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {
    private static final String TAG = "PaymentActivity";
    private Order order;
    private ImageView status_img;

    //
    static String mid = "CrOOQA42107174502294";

    private RequestQueue requestQueue;
    private static String URL = "http://foodie.educationhost.cloud/checksum/generateChecksum.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        status_img = findViewById(R.id.status_msg);
        getWindow().setStatusBarColor(getResources().getColor(R.color.card_dark_back));
        requestQueue = Volley.newRequestQueue(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            order = (Order) bundle.getSerializable("order_details");
        }
        /*try {
            Log.e(TAG, "onCreate: "+order.getUid() );

            StringRequest jsonRequest = new StringRequest(Request.Method.POST, URL,
                    response -> {
                        try {
                            Log.e(TAG, "onCreate: " + response);
                            JSONObject jsonObject=new JSONObject(response);

                            String s = jsonObject.getString("CHECKSUMHASH");
                            Log.e(TAG, "onCreate: "+s );
                            PaytmPGService service = PaytmPGService.getStagingService();
                            HashMap<String, String> paramMap = new HashMap<>();
                            paramMap.put("MID", "CrOOQA42107174502294");
// Key in your staging and production MID available in your dashboard
                            paramMap.put("ORDER_ID", order.getOrderName());
                            paramMap.put("CUST_ID", order.getUid());
                            paramMap.put("MOBILE_NO", "9932776545");
                            paramMap.put("EMAIL", "abc@gmail.com");
                            paramMap.put("CHANNEL_ID", "WAP");
                            paramMap.put("TXN_AMOUNT", String.valueOf((double) order.getAmount()));
                            paramMap.put("WEBSITE", "WEBSTAGING");
                            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
                            paramMap.put("CALLBACK_URL", "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp");
                            paramMap.put("CHECKSUMHASH", s);
                            PaytmOrder poder = new PaytmOrder(paramMap);
                            service.initialize(poder, null);
                            service.startPaymentTransaction(this, true,
                                    false, this);
                        } catch (Exception e) {
                            Toasty.error(this, "error", Toasty.LENGTH_SHORT).show();
                        }
                    }, error -> {
                Log.e(TAG, "onCreate: " + error + " resp: ");
                Toasty.error(this, "error", Toasty.LENGTH_SHORT).show();
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> object = new HashMap<>();
                    object.put("ORDER_ID", order.getOrderName());
                    object.put("CUST_ID", order.getUid());
                    object.put("INDUSTRY_TYPE_ID", "Retail");
                    object.put("CHANNEL_ID", "WAP");
                    object.put("TXN_AMOUNT", String.valueOf((double) order.getAmount()));
                    object.put("WEBSITE", "WEBSTAGING");
                    return object;
                }
            };
            requestQueue.add(jsonRequest);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.getLocalizedMessage());
        }*/
        new GetCheckSum(this, order).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public static class GetCheckSum extends AsyncTask<Void, Void, String> {

        WeakReference<PaymentActivity> activity;

        //TODO your server's url here (www.xyz/checksumGenerate.php)
        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
        String CHECKSUMHASH = "";
        Order order;

        GetCheckSum(PaymentActivity activity, Order order) {
            this.activity = new WeakReference<>(activity);
            this.order = order;
        }

        @Override
        protected void onPreExecute() {
        }

        protected final String doInBackground(Void... alldata) {
            JsonParse jsonParser = new JsonParse(activity.get());
            String param =
                    "MID=" + mid +
                            "&ORDER_ID=" + order.getOrderName() +
                            "&CUST_ID=" + order.getUid() +
                            "&CHANNEL_ID=WAP&TXN_AMOUNT=" + order.getAmount() + "&WEBSITE=DEFAULT" +
                            "&CALLBACK_URL=" + varifyurl + "&INDUSTRY_TYPE_ID=Retail";

            Log.e("PostData", param);

            JSONObject jsonObject = jsonParser.makeHttpRequest(URL, "POST", param);
            Log.e("CheckSum result >>", jsonObject.toString());
            if (jsonObject != null) {
                Log.e("CheckSum result >>", jsonObject.toString());
                try {
                    CHECKSUMHASH = jsonObject.has("CHECKSUMHASH") ? jsonObject.getString("CHECKSUMHASH") : "";
                    Log.e("CheckSum result >>", CHECKSUMHASH);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ", "  signup result  " + result);

            //  PaytmPGService Service = PaytmPGService.getStagingService();
            PaytmPGService Service = PaytmPGService.getStagingService();
            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("MID", mid);
            paramMap.put("ORDER_ID", order.getOrderName());
            paramMap.put("CUST_ID", order.getUid());
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", String.valueOf(order.getAmount()));
            paramMap.put("WEBSITE", "DEFAULT");
            paramMap.put("CALLBACK_URL", varifyurl);
            paramMap.put("CHECKSUMHASH", CHECKSUMHASH);
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param " + paramMap.toString());
            Service.initialize(Order, null);
            Service.startPaymentTransaction(activity.get(), true,
                    true,
                    activity.get());
        }
    }

    @Override
    public void onTransactionResponse(Bundle inResponse) {
        Log.e(TAG, "onTransactionResponse: " + inResponse);
        String response = inResponse.getString("RESPMSG");
        if (response != null && response.equals("Txn Success")) {
            Toasty.success(getApplicationContext(), "Order Completed Successfully"
                    , Toasty.LENGTH_SHORT).show();
            status_img.setImageResource(R.drawable.success);
            FirebaseFirestore.getInstance().collection("Orders")
                    .document(order.getOrderName())
                    .set(order, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> closeAfterAMin())
                    .addOnFailureListener(e -> {
                        Toasty.error(getApplicationContext(), "Order is not updated"
                                , Toasty.LENGTH_SHORT).show();
                        closeAfterAMin();
                    });
        } else {
            status_img.setImageResource(R.drawable.cancel);
            Log.e(TAG, "onTransactionResponse: " + inResponse);
            closeAfterAMin();
        }
    }

    @Override
    public void networkNotAvailable() {
        status_img.setImageResource(R.drawable.cancel);
        Toasty.error(this, "error", Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {
        status_img.setImageResource(R.drawable.cancel);
        Toasty.error(this, "error " + inErrorMessage, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {
        status_img.setImageResource(R.drawable.cancel);
        Toasty.error(this, inErrorMessage, Toasty.LENGTH_SHORT).show();
        closeAfterAMin();
    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
        status_img.setImageResource(R.drawable.cancel);
        Toasty.error(this, inErrorMessage, Toasty.LENGTH_SHORT).show();
        closeAfterAMin();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        status_img.setImageResource(R.drawable.cancel);
        Toasty.error(this, "Back pressed", Toasty.LENGTH_SHORT).show();
        closeAfterAMin();
    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
        status_img.setImageResource(R.drawable.cancel);
        Toasty.error(this, inErrorMessage, Toasty.LENGTH_SHORT).show();
        closeAfterAMin();
    }

    void closeAfterAMin() {
        new Handler().postDelayed(this::finish, 4000);
    }
}
