package www.rahagloball.loginregkotapp.fragments.mychdtlsf

//import okhttp3.MediaType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R

class MyChAboutFrgmnt : Fragment() {
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
        return inflater.inflate(R.layout.mych_abt_layout, container, false)
    }
}