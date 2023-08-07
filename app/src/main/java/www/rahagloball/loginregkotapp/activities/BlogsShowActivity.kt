package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getsingleblg.GetSingleBlog
import www.rahagloball.loginregkotapp.models.getsingleblg.GetSingleBlogPojo

class BlogsShowActivity : AppCompatActivity() {
    var bundle: Bundle? = null
    var Qid: String? = null
    var token: String? = null
    var manager: SessionManager? = null
    var thum_nailimg: ImageView? = null
    var blogsTitle: TextView? = null
    var blogsdescc: TextView? = null
    var mContext: Context? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blogs_show)
        bundle = intent.extras
        Qid = bundle?.getString("Qid")
        mContext = this@BlogsShowActivity
        manager = SessionManager()
        token = manager?.getPreferences(this@BlogsShowActivity, Constants.USER_TOKEN_LRN)
        thum_nailimg = findViewById<ImageView>(R.id.thum_nailimg)
        blogsTitle = findViewById<TextView>(R.id.blogsTitle)
        blogsdescc = findViewById<TextView>(R.id.blogsdescc)
        lEarnImgs
    }

    private val lEarnImgs: Unit
        get() {
//        shimmerFrameLayout?.startShimmer();
            val url: String = Configs.BASE_URL2 + "blogs/" + Qid
            RetrofitClient.getClient().learnsingleBlgs(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<GetSingleBlogPojo?>(blogsTitle) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<GetSingleBlogPojo?>,
                        response: Response<GetSingleBlogPojo?>
                    ) {
                        try {
                            val milestnres: String? = response.body()?.status
                            if (milestnres == "200") {
                                val getSingleBlog: GetSingleBlog? = response.body()?.data
                                val blog_title: String? = getSingleBlog?.title
                                val blog_desc: String? = getSingleBlog?.content
                                blogsTitle?.text = blog_title
                                blogsdescc?.text = Html.fromHtml(blog_desc, HtmlCompat.FROM_HTML_MODE_LEGACY)
                                val course_img_str: String =
                                    Configs.BASE_URL21 + "images/blogscontent/" + getSingleBlog?.image

//                                Glide.with(mContext).load(course_img_str).into(thum_nailimg)

                                thum_nailimg?.let {
                                    Glide.with(this@BlogsShowActivity)
                                        .load(course_img_str)
                                        .into(it)
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
}