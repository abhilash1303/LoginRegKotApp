package www.rahagloball.loginregkotapp.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants

class ReferFrnd : AppCompatActivity(), View.OnClickListener {
    var progressBar: ProgressBar? = null
    var rName: EditText? = null
    var rNumber: EditText? = null
    var comment: EditText? = null
    var refer: Button? = null
    var Referedview: Button? = null
    var manager: SessionManager? = null
    var token: String? = null
    var contact_List: ImageView? = null
    var permissionSendMessage = 0
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refer_frnd)
        manager = SessionManager()
        token = manager?.getPreferences(this@ReferFrnd, Constants.USER_TOKEN_LRN)
        rName = findViewById<View>(R.id.editText) as EditText?
        rNumber = findViewById<View>(R.id.editText3) as EditText?
        refer = findViewById<View>(R.id.refer) as Button?
        Referedview = findViewById<View>(R.id.Referedview) as Button?
        comment = findViewById<EditText>(R.id.comment)
        //        contact_List = findViewById(R.id.contact_list);
        refer!!.setOnClickListener(this)
        progressBar = this.findViewById<View>(R.id.progressBar) as ProgressBar?

//        Referedview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(ReferFrnd.this, ReferFriendViewActivity.class);
//                startActivity(i);
//
//            }
//        });
//        contact_List.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                askForContactPermission();
//
////                checkAndRequestPermissions();
//
//
//            }
//        });
    }

    //    @Override
    //    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //        super.onActivityResult(requestCode, resultCode, data);
    //        if (resultCode == RESULT_OK) {
    //            switch (requestCode) {
    //                case REQUEST_CODE_PICK_CONTACTS:
    //                    contactPicked(data);
    //                    break;
    //            }
    //
    //        } else {
    //
    //        }
    //    }
    //
    //
    //    private void contactPicked(Intent data) {
    //        Cursor cursor = null;
    //        try {
    //            String phoneNo = null ;
    //            String name = null;
    //            Uri uri = data.getData();
    //            cursor = getContentResolver().query(uri, null, null, null, null);
    //            cursor.moveToFirst();
    //
    //            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    //            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
    //
    //            phoneNo = cursor.getString(phoneIndex);
    //            name = cursor.getString(nameIndex);
    //
    //            String input = phoneNo.replace("+91", "").replace("-", "").replace(" ", "").replace("(","").replace(")","");
    //
    //
    //            if(!input.matches("[0-9]{10}")){
    //                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
    //                rNumber.setText("");
    //            }
    //            else
    //            {
    //                rNumber.setText(input);
    //                rName.setText(name);
    //            }
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    override fun onClick(v: View) {
        if (validate()) {
//            referfriend();
        }
    }

    fun validate(): Boolean {
        var valid = true
        val aName: String = rName?.text.toString()
        val aNumber: String = rNumber?.text.toString()
        if (aName.isEmpty()) {
            rName?.error = "Enter Name"
            valid = false
        } else {
            rName?.setError(null)
        }
        if (aNumber.isEmpty()) {
            rNumber?.error = "Enter Contact Number"
            valid = false
        } else if (!aNumber.matches("\\d{10}".toRegex())) {
            rNumber?.error = "Enter valid Contact Number"
            valid = false
        } else {
            rNumber?.error = null
        }
        return valid
    } //    public void referfriend() {

    //
    //        progressBar.visibility = View.VISIBLE;
    //
    //        RetrofitClient.getClient().refrfriend(rName?.text.toString(), rNumber?.text.toString(), comment?.text.toString(),
    //                "application/json", "Bearer " + token).enqueue(new GlobalCallback<String>(rName) {
    //            @Override
    //            public void onResponse(Call<String> call, Response<String> response) {
    //                progressBar.visibility = View.GONE;
    //
    //                try {
    //
    //                    assert response.body() != null;
    //                    String takeRes ? = response.body()?.toString();
    //
    //                    if (takeRes.contains("1")) { //
    //                        Toast.makeText(ReferFrnd.this, "Already Refered!!", Toast.LENGTH_SHORT).show();
    //
    //                    } else if (takeRes.contains("2")) {
    //                        Toast.makeText(ReferFrnd.this, "Sucessfully Submitted!!", Toast.LENGTH_SHORT).show();
    //
    //                        Intent i = new Intent(ReferFrnd.this, ReferFriendViewActivity.class);
    //                        startActivity(i);
    //                        rName.setText("");
    //                        rNumber.setText("");
    //                        comment.setText("");
    //
    //                    } else if (takeRes.contains("3")) {
    //                        Toast.makeText(ReferFrnd.this, "Try Again!!", Toast.LENGTH_SHORT).show();
    //
    //
    //                    }
    //
    //
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<String> call, Throwable t) {
    //                super.onFailure(call, t);
    //
    //            }
    //        });
    //
    //
    //    }
    //    private boolean checkAndRequestPermissions() {
    //
    //        int permissionSendMessage = ContextCompat.checkSelfPermission(ReferFrnd.this, Manifest.permission.READ_CONTACTS);
    //
    //
    //        List<String> listPermissionsNeeded = new ArrayList<>();
    //
    //
    //        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
    //            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
    //        }
    //
    //
    //        if (!listPermissionsNeeded.isEmpty()) {
    //            ActivityCompat.requestPermissions(ReferFrnd.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
    //            return false;
    //        }
    //        return true;
    //    }
    //
    //    public void EnableRuntimePermission() {
    //        permissionSendMessage = ContextCompat.checkSelfPermission(ReferFrnd.this, Manifest.permission.READ_CONTACTS);
    //        List<String> listPermissionsNeeded = new ArrayList<>();
    //        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
    //            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
    //        }
    //        if (!listPermissionsNeeded.isEmpty()) {
    //            ActivityCompat.requestPermissions(ReferFrnd.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
    //
    //        }
    //
    ////
    ////        if (ActivityCompat.shouldShowRequestPermissionRationale(ReferFrnd.this,
    ////                Manifest.permission.READ_CONTACTS))
    ////        {
    ////            Toast.makeText(ReferFrnd.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
    ////
    ////
    ////        }
    //        else {
    //
    //            ActivityCompat.requestPermissions(ReferFrnd.this, new String[]{
    //                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
    //
    //
    //        }
    //    }
    //    @Override
    //    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
    //
    //        switch (RC) {
    //
    //            case RequestPermissionCode:
    //
    //                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
    //
    //                    Toast.makeText(ReferFrnd.this, "Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();
    //
    //                } else {
    //
    //
    //                    Toast.makeText(ReferFrnd.this, "Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();
    //
    //                }
    //                break;
    //        }
    //    }
    //    @Override
    //    public void onActivityResult(int RequestCode, int ResultCode, Intent ResultIntent) {
    //
    //        super.onActivityResult(RequestCode, ResultCode, ResultIntent);
    //
    //        switch (RequestCode) {
    //
    //            case (7):
    //                if (ResultCode == Activity.RESULT_OK) {
    //
    //                    Uri uri;
    //                    Cursor cursor1, cursor2;
    //                    String TempNameHolder, TempNumberHolder, TempContactID, IDresult = "";
    //                    int IDresultHolder;
    //
    //                    uri = ResultIntent.getData();
    //
    //                    cursor1 = getContentResolver().query(uri, null, null, null, null);
    //
    //                    if (cursor1.moveToFirst()) {
    //
    //                        TempNameHolder = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    //
    //                        TempContactID = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
    //
    //                        IDresult = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
    //
    //                        IDresultHolder = Integer.valueOf(IDresult);
    //
    //                        if (IDresultHolder == 1) {
    //
    //                            cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + TempContactID, null, null);
    //
    //                            while (cursor2.moveToNext()) {
    //
    //                                TempNumberHolder = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
    //
    //                                rName.setText(TempNameHolder);
    //
    //                                rNumber.setText(TempNumberHolder);
    //
    //                            }
    //                        }
    //
    //                    }
    //                }
    //                break;
    //        }
    //    }
    //    public void askForContactPermission() {
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    //            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
    //
    //                // Should we show an explanation?
    //                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
    //                        Manifest.permission.READ_CONTACTS)) {
    //                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //                    builder.setTitle("Contacts access needed");
    //                    builder.setPositiveButton(android.R.string.ok, null);
    //                    builder.setMessage("please confirm Contacts access");//TODO put real question
    //                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
    //                        @TargetApi(Build.VERSION_CODES.M)
    //                        @Override
    //                        public void onDismiss(DialogInterface dialog) {
    //                            requestPermissions(
    //                                    new String[]
    //                                            {Manifest.permission.READ_CONTACTS}
    //                                    , permissionSendMessage);
    //                        }
    //                    });
    //                    builder.show();
    //                    // Show an expanation to the user *asynchronously* -- don't block
    //                    // this thread waiting for the user's response! After the user
    //                    // sees the explanation, try again to request the permission.
    //
    //                } else {
    //
    //                    // No explanation needed, we can request the permission.
    //
    //                    ActivityCompat.requestPermissions(this,
    //                            new String[]{Manifest.permission.READ_CONTACTS},
    //                            RequestPermissionCode);
    //
    //                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
    //                    // app-defined int constant. The callback method gets the
    //                    // result of the request.
    //                }
    //            } else {
    //                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    //                startActivityForResult(intent, 7);            }
    //        } else {
    //            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    //            startActivityForResult(intent, 7);        }
    //    }
    companion object {
        private const val REQUEST_CODE_PICK_CONTACTS = 99
        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        const val RequestPermissionCode = 1
    }
}