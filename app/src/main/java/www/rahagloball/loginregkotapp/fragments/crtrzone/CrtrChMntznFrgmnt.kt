package www.rahagloball.loginregkotapp.fragments.crtrzone

//import android.content.Context


//import okhttp3.MediaType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R

class CrtrChMntznFrgmnt : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ch_monzn_layout, container, false)
    }
}