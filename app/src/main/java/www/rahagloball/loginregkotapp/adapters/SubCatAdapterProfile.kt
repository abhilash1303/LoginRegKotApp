package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import androidx.fragment.app.FragmentActivity
import android.widget.Spinner
import android.widget.RadioGroup
import android.widget.RadioButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.EditText
import android.annotation.SuppressLint
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import android.os.Bundle
import android.content.Intent
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.widget.Toast
import android.os.Build
import android.widget.ArrayAdapter
import android.widget.Filter.FilterResults
import com.bumptech.glide.load.engine.DiskCacheStrategy
import www.rahagloball.loginregkotapp.adapters.ConnectedBizAdapter.OnClickActionn
import android.content.pm.PackageManager
import android.widget.RelativeLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import android.os.Looper
import com.google.android.exoplayer2.ui.PlayerView
import androidx.viewpager2.widget.ViewPager2
import android.widget.ToggleButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.media.MediaPlayer
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import www.rahagloball.loginregkotapp.adapters.CtsChVideoAdapter
import android.text.TextWatcher
import android.text.Editable
import www.rahagloball.loginregkotapp.adapters.YtCommentsAdapterCutCh
import android.widget.ProgressBar
import android.media.MediaPlayer.OnPreparedListener
import android.media.MediaPlayer.OnCompletionListener
import www.rahagloball.loginregkotapp.adapters.CuteVidAdptrHome
import www.rahagloball.loginregkotapp.adapters.YtCommentsAdapterCut
import www.rahagloball.loginregkotapp.adapters.CuteVidAdptrHomeAltered
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import androidx.core.view.ViewCompat
import androidx.core.content.ContextCompat
import org.json.JSONObject
import org.json.JSONException
import www.rahagloball.loginregkotapp.adapters.MyRecyclerViewAdapter.ItemClickListener
import com.google.android.material.chip.Chip
import android.widget.RatingBar
import www.rahagloball.loginregkotapp.adapters.SnglChCtsAdapter
import www.rahagloball.loginregkotapp.adapters.YtOneComentsAdptrCut
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ActivityCompat
import android.content.DialogInterface
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.annotation.RequiresApi

//package www.natlrnsnew.nationlearns.adapters;
//
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.Dialog;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RatingBar;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.google.android.material.dialog.MaterialAlertDialogBuilder;
//
//import java.util.List;
//
////import jp.shts.android.library.TriangleLabelView;
//import retrofit2.Call;
//import retrofit2.Response;
////import www.nationlearnsraha.com.AsstCntPrfileActivity;
////import www.nationlearnsraha.com.Configuration.Config;
////import www.nationlearnsraha.com.ConstantAndSession.Constants;
////import www.nationlearnsraha.com.ConstantAndSession.SessionManager;
////import www.nationlearnsraha.com.Model.GlobalCallback;
////import www.nationlearnsraha.com.Model.Pojo.conctcitybiz.RatingsItem;
////import www.nationlearnsraha.com.Model.Pojo.conectedbiz.ConctedListPojo;
////import www.nationlearnsraha.com.Model.Pojo.conectedbiz.ConnectlistItem;
////import www.nationlearnsraha.com.Model.RetrofitClient;
////import www.nationlearnsraha.com.R;
//import www.natlrnsnew.nationlearns.SessionManager;
//import www.natlrnsnew.nationlearns.activities.AsstCntPrfileActivity;
//import www.natlrnsnew.nationlearns.activities.SubCatListActivity;
//import www.natlrnsnew.nationlearns.constsnsesion.Constants;
//import www.natlrnsnew.nationlearns.constsnsesion.TriangleLabelView;
//import www.natlrnsnew.nationlearns.models.GlobalCallback;
//import www.natlrnsnew.nationlearns.models.RetrofitClient;
//import www.natlrnsnew.nationlearns.models.bizcnctprofile.RatingsItem;
//import www.natlrnsnew.nationlearns.models.conectedbiz.ConctedListPojo;
//import www.natlrnsnew.nationlearns.models.conectedbiz.ConnectlistItem;
//
//
//public class SubCatAdapterProfile extends RecyclerView.Adapter<SubCatAdapterProfile.ViewHolder> {
//
//    List<www.nationlearnsraha.com.Model.Pojo.conctcitybiz.BusinessItem> dataSet;
//    //    List<DataItemSc> dataSet1;
//    Activity mContext;
//    Button view_profile, request_services;
//    ImageView videoView1;
//    String ass_id, token, takeResCnct;
//    SessionManager manager;
//    RelativeLayout blur_reg1;
//    Button connectt;
//    EditText edit_query;
//
//    Button submit_dialog, cancel_dialog;
//    TextView name, asscote_name, cnt_assoc_name, cnt_assoc_num, cnt_assoc_email;
//
//    String edit_query_str, catggryList_biz_id, connect_sts, connect_sts1, bdget_spnr_str, srvc_spnr_str, edit_amnt_budget_str, agentSpinCnt_str;
//    MaterialAlertDialogBuilder materialAlertDialogBuilder;
//    View customAlertDialogView;
//    Spinner select_budgt_spinr, servc_spinr;
//    EditText edit_amnt_budget;
//
//
//    Dialog rankDialog;
//    String[] service_str = {"Select Service", "Immediately", "Within 1 week", "Within 2 week"};
//
//    ArrayAdapter<String> dataAdapter_bdgt, dataAdapter_srvc;
//
//    Button connectt_scc, connectted_scc;
//    LinearLayout ll_alrdy_cnctdd, ll_alrdy_cnctd;
//
//
//    public SubCatAdapterProfile(List<www.nationlearnsraha.com.Model.Pojo.conctcitybiz.BusinessItem> getAllAssociateItems, SubCatListActivity act) {
//        this.dataSet = getAllAssociateItems;
//        this.mContext = act;
//    }
//
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subcatgry_listdemo, parent, false);
//        ViewHolder myViewHolder = new ViewHolder(view);
//
//        manager = new SessionManager();
//        connectt_scc = view.findViewById(R.id.connectt_sc);
//        connectted_scc = view.findViewById(R.id.connectted);
//        ll_alrdy_cnctd = view.findViewById(R.id.ll_alrdy_cnctd);
//
//
//        return myViewHolder;
//    }
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder viewHolder,
//                                 @SuppressLint("RecyclerView") int i) {
//
//        final TextView assoname1 = viewHolder.assoname_sc;
//        final TextView city1 = viewHolder.city_sc;
//        final TextView asso_id1 = viewHolder.asso_id_sc;
//        final TextView pincodee = viewHolder.pincode_sc;
//        final ImageView img = viewHolder.circleImageview_sc;
//        final Button view_profile_scc = viewHolder.view_profile_sc;
//        final Button connectt_scc = viewHolder.connectt_sc;
//        final LinearLayout ll_alrdy_cnctdd = viewHolder.ll_alrdy_cnctd;
//        final TextView txtRatingValue1 = viewHolder.txtRatingValue_sc;
//        final RatingBar ratingBar1 = viewHolder.ratingBar_sc;
//        final Button connectted_scc = viewHolder.connectted;
//        final ImageView verified_tickk_red = viewHolder.verified_tickk_red;
//        final ImageView verified_tickk = viewHolder.verified_tickk;
//        final TriangleLabelView blog_icon1 = viewHolder.blog_icon1;
//
//        www.nationlearnsraha.com.Model.Pojo.conctcitybiz.BusinessItem dataItemSc = dataSet.get(i);
//
////        txtagent.setText(dataSet.get(i).getAgent_type());
//
//        assoname1.setText(dataSet.get(i).getName());
//        city1.setText(dataSet.get(i).getCity());
//        pincodee.setText(dataSet.get(i).getPincode());
//        ass_id = dataSet.get(i).getId();
////        ass_id = dataSet.get(i).getSubcategoryId();
//        String verify_str = dataSet.get(i).getVerify();
////        Log.e("verify_str", verify_str);
//
//
//        if (verify_str.contains("1")) {
//
//            verified_tickk.visibility = View.VISIBLE;
//            blog_icon1.visibility = View.VISIBLE;
//            verified_tickk_red.visibility = View.GONE;
//
//        } else {
//
//            verified_tickk.visibility = View.GONE;
//            verified_tickk_red.visibility = View.VISIBLE;
//            blog_icon1.visibility = View.GONE;
//
//        }
//
//
//        String status_nocitystr = dataSet.get(i).getStatus();
////        Log.e("statusoutside", status_nocitystr);
//
//        if (status_nocitystr.contains("1")) {
//
//            connectt_scc.setEnabled(true);
//
//        } else {
//            connectt_scc.setEnabled(false);
//
//        }
//
//
//        String url = Config.BASE_URL2 + "connect";
//        manager = new SessionManager();
//        token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN);
//
//        RetrofitClient.getClient().getcntbizz(url, "application/json",
//                "Bearer " + token)
//                .enqueue(new GlobalCallback<ConctedListPojo>(assoname1) {
//                    @Override
//                    public void onResponse(Call<ConctedListPojo> call, Response<ConctedListPojo> response) {
//
//
//                        try {
//
//                            List<ConnectlistItem> catggryList ? = response.body()?.getConnectlist();
//                            if (catggryList.size() > 0) {
//                                for (int i2 = 0; i2 < catggryList.size(); i2++) {
//                                    ConnectlistItem subBranchList = catggryList.get(i2);
//
//                                    catggryList_biz_id = subBranchList.getBusinessId();
//
//                                    ass_id = dataSet.get(i).getId();
//
//                                    if (catggryList_biz_id.equals(ass_id)) {
//
//                                        ll_alrdy_cnctdd.visibility = View.VISIBLE;
//                                        connectt_scc.visibility = View.GONE;
//                                        connectted_scc.visibility = View.VISIBLE;
//                                        connectt_scc.setEnabled(false);
//
//                                    } else {
//
//
//                                        ll_alrdy_cnctdd.visibility = View.GONE;
//                                        connectt_scc.visibility = View.VISIBLE;
//                                        connectted_scc.visibility = View.GONE;
//                                        connectt_scc.setEnabled(true);
//                                    }
//
//
//                                }
//                            }
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//
//        asso_id1.setText(ass_id);
//
//        if (dataSet.size() > 0) {
//            for (int i2 = 0; i2 < dataSet.size(); i2++) {
//                www.nationlearnsraha.com.Model.Pojo.conctcitybiz.BusinessItem subBranchList = dataSet.get(i);
//                List<RatingsItem> ratingss = subBranchList.getRatings();
//                for (int j = 0; j < ratingss.size(); j++) {
//                    ratingBar1.setRating(Float.parseFloat(ratingss.get(j).getReview()));
////
//                    String prorate = Float.parseFloat(ratingss.get(j).getReview()) + "/" + "5";
//                    txtRatingValue1.setText(prorate);
//                }
//            }
//        }
//
//
//        Glide.with(mContext)
//                .load(Config.BASE_URL21 + "images/business/banner/" + dataSet.get(i).getBannerImage())
//                .into(img);
////
//        view_profile_scc.setOnClickListener(view -> {
//
//            ass_id = dataSet.get(i).getId();
//            Intent id = new Intent(mContext, AsstCntPrfileActivity.class);
//            id.putExtra("ass_id", ass_id);
//            mContext.startActivity(id);
//
//        });
//
//
//        connectt_scc.setOnClickListener(v -> {
//
//
//            rankDialog = new Dialog(mContext, R.style.FullHeightDialog);
//            rankDialog.setContentView(R.layout.dialog_cat_connect);
//            rankDialog.setCancelable(true);
//
//            edit_query = rankDialog?.findViewById(R.id.edit_query);
//            asscote_name = rankDialog?.findViewById(R.id.asscote_name);
//            submit_dialog = rankDialog?.findViewById(R.id.submit_dialog);
//            cancel_dialog = rankDialog?.findViewById(R.id.cancel_dialog);
//            select_budgt_spinr = rankDialog?.findViewById(R.id.select_budgt_spinr);
//            servc_spinr = rankDialog?.findViewById(R.id.servc_spinr);
//            edit_amnt_budget = rankDialog?.findViewById(R.id.edit_amnt_budget);
//
//
//            cnt_assoc_name = rankDialog?.findViewById(R.id.cnt_assoc_name);
//            cnt_assoc_email = rankDialog?.findViewById(R.id.cnt_assoc_email);
//            cnt_assoc_num = rankDialog?.findViewById(R.id.cnt_assoc_num);
//
//            cnt_assoc_name.setText(dataSet.get(i).getName());
//            cnt_assoc_email.setText("xxxxxx@xxxxx.xxx");
//            cnt_assoc_num.setText("xxxxxxxxxx");
//
//
////            cnt_assoc_email.setText(dataSet.get(i).getEmail());
////            cnt_assoc_num.setText(dataSet.get(i).getMobile());
//
//
//            edit_query.setOnTouchListener((view, event) -> {
//                // TODO Auto-generated method stub
//                if (view.getId() == R.id.ch_desc) {
//                    view.getParent().requestDisallowInterceptTouchEvent(true);
//                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                        case MotionEvent.ACTION_UP:
//                            view.getParent().requestDisallowInterceptTouchEvent(false);
//                            break;
//                    }
//                }
//                return false;
//            });
//
//
//            dataAdapter_srvc = new ArrayAdapter<String>(mContext, R.layout.custom_spiner_layout, service_str);
//            dataAdapter_srvc.setDropDownViewResource(R.layout.custom_spiner_layout);
//            servc_spinr.setAdapter(dataAdapter_srvc);
//
//
//            submit_dialog.setOnClickListener(view -> {
//
//                edit_query_str = edit_query?.text.toString();
//                edit_amnt_budget_str = edit_amnt_budget?.text.toString();
//
//                srvc_spnr_str = servc_spinr?.selectedItem.toString();
//
//
//                if (edit_amnt_budget_str.equals("") || edit_amnt_budget_str.isEmpty()) {
//                    Toast.makeText(mContext, "Enter Budget", Toast.LENGTH_SHORT).show();
//                } else if (srvc_spnr_str.contains("Select Service")) {
//                    Toast.makeText(mContext, "Select any Service", Toast.LENGTH_SHORT).show();
//                } else if (edit_query_str.equals("") || edit_query_str.isEmpty()) {
//                    Toast.makeText(mContext, "Please Enter Something", Toast.LENGTH_SHORT).show();
//                }
//
//                if (edit_amnt_budget_str != null && edit_amnt_budget_str.length() > 0) {
//                    float percent = 5.0f;
//
//                    float answer = (Float.parseFloat(edit_amnt_budget_str) * (percent / 100.0f));
////                    Log.e("answer", String.valueOf(answer));
//
//                    ass_id = dataSet.get(i).getId();
//                    String subcatid_str = dataSet.get(i).getSubcategoryId();
//                    manager = new SessionManager();
//                    token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN);
//                    String mobilr_str = dataSet.get(i).getMobile();
//                    String name_str = dataSet.get(i).getName();
//
//                    conect_submit(ass_id, String.valueOf(answer), edit_amnt_budget_str,
//                            srvc_spnr_str, edit_query_str, subcatid_str, viewHolder, i, mobilr_str, name_str);
//
//                }
//
//            });
//
//            cancel_dialog.setOnClickListener(vv -> {
//                rankDialog.dismiss();
//
//            });
//
//            rankDialog.show();
//
//
//        });
//
//
//    }
//
//
//    private void conect_submit(String ass_id, String answer, String edit_amnt_budget_str,
//                               String srvc_spnr_str, String edit_query_str,
//                               String subcatid_str, ViewHolder viewHolder,
//                               int position, String mobilr_str, String name_str) {
//
//
////        SubConctPojo
//        manager = new SessionManager();
//        token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN);
//
//        RetrofitClient.getClient().connect_asociat(ass_id, String.valueOf(answer), edit_amnt_budget_str, srvc_spnr_str, edit_query_str,
//                subcatid_str, "application/json",
//                "Bearer " + token).
//                enqueue(new GlobalCallback<String>(asscote_name) {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
////                                blur_reg1.visibility = View.GONE;
//
//                        try {
//
//
//                            takeResCnct ? = response.body()?.toString();
////                            Log.e("takeResCnctcity",takeResCnct);
//
//                            if (takeResCnct.contains("1")) {
//
//                                String assdataename = dataSet.get(position).getName();
//
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//                                    NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID, Constants.NOTICHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//                                    channel.setDescription(Constants.CHANNEL_DESC);
//                                    NotificationManager manager = mContext.getSystemService(NotificationManager.class);
//                                    manager.createNotificationChannel(channel);
//
//                                }
//
//                                NotificationCompat.Builder mBuilder =
//                                        new NotificationCompat.Builder(mContext.applicationContext, Constants.CHANNEL_ID)
//                                                .setSmallIcon((R.drawable.nllogo))
//                                                .setAutoCancel(true)
//                                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                                                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
//                                                .setContentTitle("NationLearns")
//                                                .setContentText("You have successfully Connected to " + assdataename)
//                                                .setStyle(new NotificationCompat.BigTextStyle()
//                                                        .bigText("You have successfully Connected to " + assdataename))
//                                                .setContentIntent(PendingIntent.getActivity(mContext.applicationContext, 0, new Intent(), 0));
//
//                                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext.applicationContext);
//
////                                Intent intent1 = new Intent(mContext.applicationContext, SubsNewNlpActivity.class);
////                                PendingIntent pi = PendingIntent.getActivity(mContext.applicationContext, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//
////                                mBuilder.setContentIntent(pi);
////
//                                notificationManagerCompat.notify(1, mBuilder.build());
//
////                                Toast.makeText(mContext, "Connected Successfully. Kindly check the associate details on My Business List", Toast.LENGTH_SHORT).show();
//                                String success_nsgg = "Connected Successfully. Kindly check the associate details on My Business List";
//
//                                new AlertDialog.Builder(mContext)
//                                        .setMessage(success_nsgg)
//                                        .setCancelable(false)
//                                        .setPositiveButton("Yes", (dialog, id) -> {
//
////                                                String ass_idd_slc_str = dataSet.get(i).getUser_idd();
////                                                accpet_slc(ass_idd_slc_str);
//
//
//                                        })
//                                        .setNegativeButton("No", null)
//                                        .show();
//
//
////                                String msg_str = "You have got one inbound lead from " + name_str +  ". "+  "Please check on Inbound  click here  "+" https://play.google.com/store/apps/details?id=www.nationlearnspartnerlisting.com ";
////
////                                boolean installed = appInstalledOrNot("com.whatsapp");
////                                if (installed){
////                                    Intent intent = new Intent(Intent.ACTION_VIEW);
////                                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+ mobilr_str + "&text="+msg_str));
////                                    mContext.startActivity(intent);
////                                }else {
////                                    Toast.makeText(mContext.applicationContext, "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
////                                }
//
////
////                                Intent intent = new Intent(mContext, ConnectedBizList.class);
////                                mContext.startActivity(intent);
//                                rankDialog.dismiss();
//
//
////                                Toast.makeText(mContext, "Connected Successfully. Kindly check the associate details on My Business List", Toast.LENGTH_SHORT).show();
////
////                                Intent intent = new Intent(mContext, ConnectedBizList.class);
////                                mContext.startActivity(intent);
////                                rankDialog.dismiss();
//
//                            } else if (takeResCnct.contains("2")) {
//
//                                Toast.makeText(mContext, "Connection Failed", Toast.LENGTH_SHORT).show();
//
//                            }
//                            if (takeResCnct.contains("3")) {
//                                Toast.makeText(mContext, "Already Connected", Toast.LENGTH_SHORT).show();
//
////
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        super.onFailure(call, t);
//                    }
//                });
//
//    }
//
//    public boolean appInstalledOrNot(String url) {
//        PackageManager packageManager = mContext.getPackageManager();
//        boolean app_installed;
//        try {
//            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
//            app_installed = true;
//        } catch (PackageManager.NameNotFoundException e) {
//            app_installed = false;
//        }
//        return app_installed;
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return dataSet.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView assoname_sc, pincode_sc, city_sc, txtRatingValue_sc, asso_id_sc;
//        RatingBar ratingBar_sc;
//        Button view_profile_sc, connectt_sc, connectted;
//        ImageView circleImageview_sc, verified_tickk, verified_tickk_red;
//
//        LinearLayout ll_alrdy_cnctd;
//
//        TriangleLabelView blog_icon1;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            assoname_sc = itemView.findViewById(R.id.assoname_sc);
//            pincode_sc = itemView.findViewById(R.id.pincode_sc);
//            city_sc = itemView.findViewById(R.id.city_sc);
//            txtRatingValue_sc = itemView.findViewById(R.id.txtRatingValue_sc);
//            asso_id_sc = itemView.findViewById(R.id.asso_id_sc);
//            ratingBar_sc = itemView.findViewById(R.id.ratingBar_sc);
//            view_profile_sc = itemView.findViewById(R.id.view_profile_sc);
//            connectt_sc = itemView.findViewById(R.id.connectt_sc);
//            circleImageview_sc = itemView.findViewById(R.id.circleImageview_sc);
//
//            blur_reg1 = (RelativeLayout) itemView.findViewById(R.id.blur_reg1);
//            connectted = itemView.findViewById(R.id.connectted);
//            ll_alrdy_cnctd = itemView.findViewById(R.id.ll_alrdy_cnctd);
//            verified_tickk = itemView.findViewById(R.id.verified_tickk);
//            blog_icon1 = itemView.findViewById(R.id.blog_icon1);
//            verified_tickk_red = itemView.findViewById(R.id.verified_tickk_red);
//
//        }
//    }
//}
//
//
//
//
//
////    BroadcastReceiver myLocalBrdcastRcvr = new BroadcastReceiver() {
////                                    @Override
////                                    public void onReceive(Context context, Intent intent) {
////                                        String resultt = intent.getStringExtra("result");
////                                        Toast.makeText(context, resultt, Toast.LENGTH_SHORT).show();
////                                    }
////                                };
////
////                                IntentFilter intentFilter=new IntentFilter("my.own.broadcast");
////                                LocalBroadcastManager.getInstance(mContext).registerReceiver(myLocalBrdcastRcvr,intentFilter);
//
//
//
////                                MySmsService1.startactionwhatapp(mContext, "You have successfully Connected to " + assdataename, "9740361350");
////
//
////                                private void getinboundLead() {
//
//////                                    progress_inbound.visibility = View.VISIBLE;
////                                String url = Config.BASE_URL2 + "business-connect";
////                                token = manager?.getPreferences(mContext.applicationContext, Constants.USER_TOKEN_LRN);
//////                                Log.e("",);
////
////                                RetrofitClient.getClient().biz_connect_notif(url, "application/json", "Bearer " + token)
////                                        .enqueue(new GlobalCallback<NotifDataPojo>(videoView1) {
////                                            @SuppressLint("NotifyDataSetChanged")
////                                            @Override
////                                            public void onResponse(@NonNull Call<NotifDataPojo> call, @NonNull retrofit2.Response<NotifDataPojo> response) {
////
//////                                                    progress_inbound.visibility = View.GONE;
////
////                                                String res ? = response.body()?.toString();
////                                                Log.e("Inboundres", res);
////
////                                                if (res.contains("2")) {
////
////                                                    String assdataename = dataSet.get(position).getName();
////
////                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////                                                        NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID, Constants.NOTICHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
////                                                        channel.setDescription(Constants.CHANNEL_DESC);
////                                                        NotificationManager manager = mContext.getSystemService(NotificationManager.class);
////                                                        manager.createNotificationChannel(channel);
////
////                                                    }
////
////
////                                                    NotificationCompat.Builder mBuilder =
////                                                            new NotificationCompat.Builder(mContext.applicationContext, Constants.CHANNEL_ID)
////                                                                    .setSmallIcon((R.drawable.nllogo))
////                                                                    .setAutoCancel(true)
////                                                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
////                                                                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
////                                                                    .setContentTitle("NationLearns")
////                                                                    .setContentText("You have successfully Connected to " + assdataename)
////                                                                    .setStyle(new NotificationCompat.BigTextStyle()
////                                                                            .bigText("You have successfully Connected to " + assdataename))
////                                                                    .setContentIntent(PendingIntent.getActivity(mContext.applicationContext, 0, new Intent(), 0));
////
////                                                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext.applicationContext);
////
//////                                Intent intent1 = new Intent(mContext.applicationContext, SubsNewNlpActivity.class);
//////                                PendingIntent pi = PendingIntent.getActivity(mContext.applicationContext, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
////
//////                                mBuilder.setContentIntent(pi);
////
////                                                    notificationManagerCompat.notify(1, mBuilder.build());
////
////
////                                                }
////
////
////
////                                            }
////                                        });
//
//
//
////    sendwhatsppp(mobilr_str, msg_str);
////                                BroadcastReceiver myLocalBrdcastRcvr = new BroadcastReceiver() {
////                                    @Override
////                                    public void onReceive(Context context, Intent intent) {
////                                        String resultt = intent.getStringExtra("result");
////                                        Toast.makeText(context, resultt, Toast.LENGTH_SHORT).show();
////                                    }
////                                };
////
////                                IntentFilter intentFilter = new IntentFilter("my.own.broadcast");
////                                LocalBroadcastManager.getInstance(mContext).registerReceiver(myLocalBrdcastRcvr, intentFilter);
////
////                                MySmsService1.startactionwhatapp(mContext, "You have successfully Connected to " + assdataename, "9740361350");
//
//
//
//
////                                String msg_str = "you got one inbound lead from " + name_str + "click here  "+"https://play.google.com/store/apps/details?id=www.nationlearnspartnerlisting.com";
//////                                String assdataename = dataSet.get(position).getName();
////
////                                boolean installed = appInstalledOrNot("com.whatsapp");
////                                if (installed){
////                                    Intent intent = new Intent(Intent.ACTION_VIEW);
////                                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91" +mobilr_str + "&text="+msg_str));
////                                    mContext.startActivity(intent);
////                                }else {
////                                    Toast.makeText(mContext.applicationContext, "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
////                                }
//
//
////                                progress_inbound.visibility = View.VISIBLE;
//
////                                String url = Config.BASE_URL2 + "business-connect";
////                                token = manager?.getPreferences(mContext.applicationContext, Constants.USER_TOKEN_LRN);
//////                                Log.e("",);
////
////                                RetrofitClient.getClient().biz_connect_notif(url, "application/json", "Bearer " + token)
////                                        .enqueue(new GlobalCallback<NotifDataPojo>(videoView1) {
////                                            @SuppressLint("NotifyDataSetChanged")
////                                            @Override
////                                            public void onResponse(@NonNull Call<NotifDataPojo> call, @NonNull retrofit2.Response<NotifDataPojo> response) {
////
//////                                                    progress_inbound.visibility = View.GONE;
////
////                                                String res ? = response.body()?.getConnect();
////                                                Log.e("Inboundres", res);
////
////                                                if (res.contains("2")){
////
////                                                    String assdataename = dataSet.get(position).getName();
////
////                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////                                                        NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID, Constants.NOTICHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
////                                                        channel.setDescription(Constants.CHANNEL_DESC);
////                                                        NotificationManager manager = mContext.getSystemService(NotificationManager.class);
////                                                        manager.createNotificationChannel(channel);
////
////                                                    }
////
////
////                                                    NotificationCompat.Builder mBuilder =
////                                                            new NotificationCompat.Builder(mContext.applicationContext, Constants.CHANNEL_ID)
////                                                                    .setSmallIcon((R.drawable.nllogo))
////                                                                    .setAutoCancel(true)
////                                                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
////                                                                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
////                                                                    .setContentTitle("NationLearns")
////                                                                    .setContentText("You have successfully Connected to " + assdataename)
////                                                                    .setStyle(new NotificationCompat.BigTextStyle()
////                                                                            .bigText("You have successfully Connected to " + assdataename))
////                                                                    .setContentIntent(PendingIntent.getActivity(mContext.applicationContext, 0, new Intent(), 0));
////
////                                                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext.applicationContext);
////
//////                                Intent intent1 = new Intent(mContext.applicationContext, SubsNewNlpActivity.class);
//////                                PendingIntent pi = PendingIntent.getActivity(mContext.applicationContext, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
////
//////                                mBuilder.setContentIntent(pi);
////
////                                                    notificationManagerCompat.notify(1, mBuilder.build());
//
//
////                                                }
////
////
////
////                                            }
////                                        });
//
//
//
////
////    public void sendwhatsppp(String number, String messsagee) {
////
////
//////        String toNumber = "+91 97315 64723"; // contains spaces.
//////        number = number.replace("+", "").replace(" ", "");
////
//////        String message="this is wahtsapp";
////
////        Intent sendIntent = new Intent("android.intent.action.MAIN");
////        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
////        sendIntent.putExtra(Intent.EXTRA_TEXT, messsagee);
////        sendIntent.setAction(Intent.ACTION_SEND);
////        sendIntent.setPackage("com.whatsapp");
////        sendIntent.setType("text/plain");
////        try {
////            mContext.startActivity(sendIntent);
////        } catch (android.content.ActivityNotFoundException ex) {
////            Toast.makeText(mContext.applicationContext, "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
////        }
////
//////        For sharing images
//////
//////        String toNumber = "+91 98765 43210"; // contains spaces.
//////        toNumber = toNumber.replace("+", "").replace(" ", "");
//////
//////        Intent sendIntent = new Intent("android.intent.action.MAIN");
//////        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
//////        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
//////        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
//////        sendIntent.setAction(Intent.ACTION_SEND);
//////        sendIntent.setPackage("com.whatsapp");
//////        sendIntent.setType("image/png");
//////        context.startActivity(sendIntent);
////
////    }
