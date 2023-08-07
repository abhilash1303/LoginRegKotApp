package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
class ReferFriendViewActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var blur_reg1: RelativeLayout? = null
    var emptyElement: ImageView? = null
    var token: String? = null
    var manager: SessionManager? = null
    var name: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refer_friend_view)
        manager = SessionManager()
        token = manager?.getPreferences(this@ReferFriendViewActivity, Constants.USER_TOKEN_LRN)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        emptyElement = findViewById<ImageView>(R.id.emptyElement)

//        recyclerView.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this@ReferFriendViewActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

//        getlistview();
    } //    private void getlistview() {

    //
    //        blur_reg1.visibility = View.VISIBLE;
    //        String url = Configs.BASE_URL2 + "all-ref-fri/";
    //
    //        RetrofitClient.getClient().referfriendd(url, "application/json", "Bearer " + token)
    //                .enqueue(new GlobalCallback<Rfpojo>(recyclerView) {
    //                    @Override
    //                    public void onResponse(Call<Rfpojo> call, Response<Rfpojo> response) {
    //
    //                        blur_reg1.visibility = View.GONE;
    //
    //                        try {
    //                            assert response.body() != null;
    //                            List<GetReferredFriendsItem> getReferredFriends ? = response.body()?.getGetReferredFriends();
    //
    //
    //                            if (getReferredFriends.isEmpty()) {
    //                                emptyElement.visibility = View.VISIBLE;
    //
    //                            } else {
    //                                adapter = new ReferListAdapter((ArrayList<GetReferredFriendsItem>) getReferredFriends, ReferFriendViewActivity.this);
    //                                recyclerView.setAdapter(adapter);
    //
    //                            }
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
    //
    //
    //                    }
    //                });
    //
    //
    //    }
    companion object {
        private val adapter: RecyclerView.Adapter<*>? = null
        var myOnClickListener: View.OnClickListener? = null
    }
}