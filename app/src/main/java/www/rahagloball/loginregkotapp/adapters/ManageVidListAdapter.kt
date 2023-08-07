package www.rahagloball.loginregkotapp.adapters


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.TUploadVidNewActivityEdit
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mymngbid.MvCategory
import www.rahagloball.loginregkotapp.models.mymngbid.MvSubCategory
import www.rahagloball.loginregkotapp.models.mymngbid.MyManageVid
import www.rahagloball.loginregkotapp.models.mymngbid.VideoCategory
import www.rahagloball.loginregkotapp.models.mymngvidsingle.MngVidSingle
import www.rahagloball.loginregkotapp.models.mymngvidsingle.MngVidSingleCat
import www.rahagloball.loginregkotapp.models.mymngvidsingle.MngVidSinglePojo

class ManageVidListAdapter(dataSet: List<MyManageVid>, mContext: Activity) :
    RecyclerView.Adapter<ManageVidListAdapter.ViewHolder>() {
    var dataSet: List<MyManageVid>
    var mContext: Activity
    var rankDialog: Dialog? = null
    var token: String? = null
    var mng_viddd_title: TextView? = null
    var mng_viddd_desc: TextView? = null
    var mng_viddd_cat: TextView? = null
    var mng_viddd_kywrds: TextView? = null
    var cancel_dialog: TextView? = null
    var manager: SessionManager? = null
    var cv_kyds: CardView? = null
    var cv_desc: CardView? = null
    var cv_title: CardView? = null
    var cv_cat: CardView? = null

    init {
        this.dataSet = dataSet
        this.mContext = mContext
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.sprt_list_adptr1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        try {
            val sprt_name: TextView = viewHolder.sprt_name
            val user_ststuss: TextView = viewHolder.user_ststuss
            val user_keywords: TextView = viewHolder.user_keywords
            val user_vidtype: TextView = viewHolder.user_vidtype
            val user_visiblefor: TextView = viewHolder.user_visiblefor
            val sel_vid_ctagry: TextView = viewHolder.sel_vid_ctagry
            val user_ctgry: TextView = viewHolder.user_ctgry
            val user_subctgry: TextView = viewHolder.user_subctgry
            val user_descrptn: TextView = viewHolder.user_descrptn
            val user_vieww: TextView = viewHolder.user_vieww
            val my_finance = viewHolder.my_finance
            val sprt_img: CircleImageView = viewHolder.sprt_img
            val sts: String? = dataSet[i].status
            val sprt_name_str: String? = dataSet[i].title
            val user_keywords_str: String? = dataSet[i].keywords
            val user_vidtype_str: String? = dataSet[i].type
            val user_visiblefor_str: String? = dataSet[i].visible
            val mvCategory: MvCategory? = dataSet[i].category
            val mvSubCategory: MvSubCategory? = dataSet[i].subCategory
                val user_subctgry_str: String? = mvSubCategory?.title
                user_subctgry.setText(user_subctgry_str)
            val videoCategory: VideoCategory? = dataSet[i].videoCategory
            val user_ctgry_str: String? = mvCategory?.title
            val user_descrptn_str: String? = dataSet[i].description
            val sel_vid_ctagry_str: String? = videoCategory?.catName
            sprt_name.setText(sprt_name_str)
            user_keywords.setText(user_keywords_str)
            user_vidtype.setText(user_vidtype_str)
            user_visiblefor.setText(user_visiblefor_str)
            user_ctgry.setText(user_ctgry_str)
            user_descrptn.setText(user_descrptn_str)
            sel_vid_ctagry.setText(sel_vid_ctagry_str)
            val chnl_Imgstr: String =
                Configs.BASE_URL21 + "images/pool/preview/" + dataSet[i].previewImage
            Glide.with(mContext).load(chnl_Imgstr).into(sprt_img)
            if (sts == "0") {
                user_ststuss.setText("in review")
                val radius: Float = mContext.getResources().getDimension(R.dimen.corner_radius)
                val shapeAppearanceModel: ShapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, radius)
                    .build()
                val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
                ViewCompat.setBackground(user_ststuss, shapeDrawable)
                shapeDrawable.fillColor = ContextCompat.getColorStateList(
                    mContext,
                    R.color.tan_orang
                )
                shapeDrawable.setStroke(2.0f, ContextCompat.getColor(mContext, R.color.white))
                //            user_ststuss.setBackgroundColor(Color.parseColor("#fd5523"));
            } else if (sts == "1") {
                user_ststuss.setText("approved")
                val radius: Float = mContext.getResources().getDimension(R.dimen.corner_radius)
                val shapeAppearanceModel: ShapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, radius)
                    .build()
                val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
                ViewCompat.setBackground(user_ststuss, shapeDrawable)
                shapeDrawable.setFillColor(
                    ContextCompat.getColorStateList(
                        mContext,
                        R.color.AppBlue
                    )
                )
            } else {
                user_ststuss.setText("Rejected")
                //            user_ststuss.setBackgroundColor(Color.parseColor("#fd5523"));
                val radius: Float = mContext.getResources().getDimension(R.dimen.corner_radius)
                val shapeAppearanceModel: ShapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, radius)
                    .build()
                val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
                ViewCompat.setBackground(user_ststuss, shapeDrawable)
                shapeDrawable.setFillColor(ContextCompat.getColorStateList(mContext, R.color.reddd))
            }
            val vid_iddddd: String? = dataSet[i].id
            my_finance.setOnClickListener { v: View? ->
                val intent11 = Intent(mContext, TUploadVidNewActivityEdit::class.java)
                intent11.putExtra("vid_idddd", vid_iddddd)
                mContext.startActivity(intent11)
            }
            user_vieww.setOnClickListener(View.OnClickListener { v: View? ->
                val vid_idddddff: String? = dataSet[i].id
                rankDialog = Dialog(mContext, R.style.FullHeightDialog)
                rankDialog?.setContentView(R.layout.dialog_mngvidshow)
                rankDialog!!.setCancelable(true)
                val window = rankDialog!!.window
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                //                TextView mng_viddd_title,mng_viddd_desc,mng_viddd_cat;
                mng_viddd_title = rankDialog?.findViewById<TextView>(R.id.mng_viddd_title)
                cancel_dialog = rankDialog?.findViewById<TextView>(R.id.cancel_dialog)
                mng_viddd_desc = rankDialog?.findViewById<TextView>(R.id.mng_viddd_desc)
                mng_viddd_cat = rankDialog?.findViewById<TextView>(R.id.mng_viddd_cat)
                mng_viddd_kywrds = rankDialog?.findViewById<TextView>(R.id.mng_viddd_kywrds)
                cv_kyds = rankDialog!!.findViewById<CardView>(R.id.cv_kyds)
                cv_desc = rankDialog!!.findViewById<CardView>(R.id.cv_desc)
                cv_title = rankDialog!!.findViewById<CardView>(R.id.cv_title)
                cv_cat = rankDialog!!.findViewById<CardView>(R.id.cv_cat)
                cv_kyds?.setBackgroundResource(R.drawable.backgd_app_odd)
                cv_desc?.setBackgroundResource(R.drawable.backgd_app_odd)
                cv_title?.setBackgroundResource(R.drawable.backgd_app_odd)
                cv_cat?.setBackgroundResource(R.drawable.backgd_app_odd)
                manager = SessionManager()
                token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
                val url: String = Configs.BASE_URL2 + "my-managevideos/" + vid_idddddff
                RetrofitClient.getClient().getMngVidLsit1(url, "application/json", "Bearer $token")
                    ?.enqueue(object : GlobalCallback<MngVidSinglePojo?>(mng_viddd_cat) {
                        @SuppressLint("SetTextI18n")
                     override   fun onResponse(
                            call: Call<MngVidSinglePojo?>,
                            response: Response<MngVidSinglePojo?>
                        ) {
                            try {
                                if (response.body() != null) {
                                    val res_mv: String ? = response.body()?.status
                                    if (res_mv == "200") {
                                        val myManageVid: MngVidSingle?=response.body()?.data
                                        val vid_title_str: String? = myManageVid?.title
                                        val vid_desc_str: String? = myManageVid?.description
                                        val vid_kywrds_str: String? = myManageVid?.keywords
                                        mng_viddd_title?.text = vid_title_str
                                        mng_viddd_desc?.text = vid_desc_str
                                        mng_viddd_kywrds?.text = vid_kywrds_str
                                        val mngVidSingleCat: MngVidSingleCat? =
                                            myManageVid?.category
                                        val catgry_str: String? = mngVidSingleCat?.title
                                        mng_viddd_cat?.setText(catgry_str)
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    })
                cancel_dialog?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                rankDialog!!.show()
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sprt_img: CircleImageView
        var my_finance: CardView
        var sprt_name: TextView
        var user_ststuss: TextView
        var user_keywords: TextView
        var user_vidtype: TextView
        var user_visiblefor: TextView
        var sel_vid_ctagry: TextView
        var user_ctgry: TextView
        var user_subctgry: TextView
        var user_descrptn: TextView
        var user_vieww: TextView

        init {
            user_ststuss = itemView.findViewById<TextView>(R.id.user_ststuss)
            user_keywords = itemView.findViewById<TextView>(R.id.user_keywords)
            user_vidtype = itemView.findViewById<TextView>(R.id.user_vidtype)
            user_visiblefor = itemView.findViewById<TextView>(R.id.user_visiblefor)
            sel_vid_ctagry = itemView.findViewById<TextView>(R.id.sel_vid_ctagry)
            user_ctgry = itemView.findViewById<TextView>(R.id.user_ctgry)
            user_subctgry = itemView.findViewById<TextView>(R.id.user_subctgry)
            user_descrptn = itemView.findViewById<TextView>(R.id.user_descrptn)
            sprt_name = itemView.findViewById<TextView>(R.id.sprt_name)
            sprt_img = itemView.findViewById<CircleImageView>(R.id.sprt_img)
            my_finance = itemView.findViewById<CardView>(R.id.my_finance)
            user_vieww = itemView.findViewById<TextView>(R.id.user_vieww)
        }
    }
}