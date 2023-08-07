package www.rahagloball.loginregkotapp.fragments.earnigs

//import okhttp3.MediaType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R

class SuprSprtEarnFrgmnt : Fragment() {
    var ll_profile_view: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        ll_profile_view=viewexp.findViewById(R.id.ll_profile_view);
//
//        ll_profile_view.setOnClickListener(v->{
//
//
//
//
//        });
        return inflater.inflate(R.layout.ch_crse_layout, container, false)
    }
}