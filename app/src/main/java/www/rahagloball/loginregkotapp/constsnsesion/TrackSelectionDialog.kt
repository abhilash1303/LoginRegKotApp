package www.rahagloball.loginregkotapp.constsnsesion

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Tracks
import com.google.android.exoplayer2.source.TrackGroup
import com.google.android.exoplayer2.trackselection.TrackSelectionOverride
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters
import com.google.android.exoplayer2.ui.TrackSelectionView
import com.google.android.material.tabs.TabLayout
import com.google.common.collect.ImmutableList
import www.rahagloball.loginregkotapp.R

class TrackSelectionDialog : DialogFragment() {
    /** Called when tracks are selected.  */
    interface TrackSelectionListener {
        fun onTracksSelected(trackSelectionParameters: TrackSelectionParameters?)
    }

    private val tabFragments: SparseArray<TrackSelectionViewFragment> = SparseArray()
    private val tabTrackTypes: ArrayList<Int> = ArrayList()
    private var titleId = 0
    private var onClickListener: DialogInterface.OnClickListener? = null
    private var onDismissListener: DialogInterface.OnDismissListener? = null

    init {
        retainInstance = true
    }

    private fun init(
        tracks: List<Tracks.Group>,
        trackSelectionParameters: TrackSelectionParameters,
        titleId: Int,
        allowAdaptiveSelections: Boolean,
        allowMultipleOverrides: Boolean,
        onClickListener: DialogInterface.OnClickListener,
        onDismissListener: DialogInterface.OnDismissListener?
    ) {
        this.titleId = titleId
        this.onClickListener = onClickListener
        this.onDismissListener = onDismissListener

        for (i in SUPPORTED_TRACK_TYPES.indices) {
            val trackType: Int = SUPPORTED_TRACK_TYPES[i]
            val trackGroups = ArrayList<Tracks.Group>()
            for (trackGroup in tracks) {
                if (trackGroup.type == trackType) {
                    trackGroups.add(trackGroup)
                }
            }
            if (trackGroups.isNotEmpty()) {
                val tabFragment = TrackSelectionViewFragment()
                tabFragment.init(
                    trackGroups,
                    trackSelectionParameters.disabledTrackTypes.contains(trackType),
                    trackSelectionParameters.overrides,
                    allowAdaptiveSelections,
                    allowMultipleOverrides
                )
                tabFragments.put(trackType, tabFragment)
                tabTrackTypes.add(trackType)
            }
        }
    }

    fun getIsDisabled(trackType: Int): Boolean {
        val trackView: TrackSelectionViewFragment = tabFragments.get(trackType)
        return trackView != null && trackView.isDisabled
    }

    fun getOverrides(trackType: Int): Map<TrackGroup, TrackSelectionOverride> {
        val trackView: TrackSelectionViewFragment = tabFragments.get(trackType)
        return if (trackView == null) emptyMap() else trackView.overrides!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // We need to own the view to let tab layout work correctly on all API levels. We can't use
        // AlertDialog because it owns the view itself, so we use AppCompatDialog instead, themed using
        // the AlertDialog theme overlay with force-enabled title.
        val dialog = AppCompatDialog(requireActivity(), R.style.TrackSelectionDialogThemeOverlay)
        dialog.setTitle(titleId)
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss(dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val dialogView: View = inflater.inflate(R.layout.track_selection_dialog, container, false)
        val tabLayout: TabLayout = dialogView.findViewById(R.id.track_selection_dialog_tab_layout)
        val viewPager: ViewPager = dialogView.findViewById(R.id.track_selection_dialog_view_pager)
        val cancelButton = dialogView.findViewById<Button>(R.id.track_selection_dialog_cancel_button)
        val okButton = dialogView.findViewById<Button>(R.id.track_selection_dialog_ok_button)

        viewPager.adapter = FragmentAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.visibility = if (tabFragments.size() > 1) View.VISIBLE else View.GONE

        cancelButton.setOnClickListener { dismiss() }
        okButton.setOnClickListener {
            onClickListener?.onClick(dialog, DialogInterface.BUTTON_POSITIVE)
            dismiss()
        }

        return dialogView
    }

    private inner class FragmentAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(
        fragmentManager!!,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment {
            return tabFragments.get(tabTrackTypes[position])
        }

        override fun getCount(): Int {
            return tabTrackTypes.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return getTrackTypeString(resources, tabTrackTypes[position])
        }
    }

    class TrackSelectionViewFragment : Fragment(), TrackSelectionView.TrackSelectionListener {
        private var trackGroups: List<Tracks.Group>? = null
        private var allowAdaptiveSelections = false
        private var allowMultipleOverrides = false
        var isDisabled = false
        var overrides: Map<TrackGroup, TrackSelectionOverride>? = null

        init {
            retainInstance = true
        }

        fun init(
            trackGroups: List<Tracks.Group>?,
            isDisabled: Boolean,
            overrides: Map<TrackGroup, TrackSelectionOverride>?,
            allowAdaptiveSelections: Boolean,
            allowMultipleOverrides: Boolean
        ) {
            this.trackGroups = trackGroups
            this.isDisabled = isDisabled
            this.overrides = overrides
            this.allowAdaptiveSelections = allowAdaptiveSelections
            this.allowMultipleOverrides = allowMultipleOverrides
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val rootView: View = inflater.inflate(
                R.layout.exo_track_selection_dialog,
                container, false
            )
            val trackSelectionView: TrackSelectionView = rootView.findViewById(R.id.exo_track_selection_view)
            trackSelectionView.setShowDisableOption(true)
            trackSelectionView.setAllowMultipleOverrides(allowMultipleOverrides)
            trackSelectionView.setAllowAdaptiveSelections(allowAdaptiveSelections)
            trackGroups?.let { overrides?.let { it1 ->
                trackSelectionView.init(it, isDisabled,
                    it1, null, this)
            } }
            return rootView
        }

        override fun onTrackSelectionChanged(
            isDisabled: Boolean, overrides: Map<TrackGroup, TrackSelectionOverride>
        ) {
            this.isDisabled = isDisabled
            this.overrides = overrides
        }
    }


    companion object {
        val SUPPORTED_TRACK_TYPES: ImmutableList<Int> = ImmutableList.of(
            C.TRACK_TYPE_VIDEO, C.TRACK_TYPE_AUDIO, C.TRACK_TYPE_TEXT
        )
        fun willHaveContent(player: Player): Boolean {
            return willHaveContent(player.currentTracks)
        }
        fun willHaveContent(tracks: Tracks): Boolean {
            for (trackGroup in tracks.groups) {
                if (SUPPORTED_TRACK_TYPES.contains(trackGroup.type)) {
                    return true
                }
            }
            return false
        }

        //    companion object {
//        val SUPPORTED_TRACK_TYPES =
//            ImmutableList.of<Int>(C.TRACK_TYPE_VIDEO, C.TRACK_TYPE_AUDIO, C.TRACK_TYPE_TEXT)
//        fun willHaveContent(player: Player): Boolean {
//            return willHaveContent(player.getCurrentTracks())
//        }
//        fun willHaveContent(tracks: Tracks): Boolean {
//            for (trackGroup in tracks.groups) {
//                if (SUPPORTED_TRACK_TYPES.contains(trackGroup.type)) {
//                    return true
//                }
//            }
//            return false
//        }

        fun newInstance(
            trackGroups: List<Tracks.Group>,
            trackSelectionParameters: TrackSelectionParameters,
            titleId: Int,
            allowAdaptiveSelections: Boolean,
            allowMultipleOverrides: Boolean,
            onClickListener: DialogInterface.OnClickListener,
            onDismissListener: DialogInterface.OnDismissListener?
        ): TrackSelectionDialog {
            val dialog = TrackSelectionDialog()
            dialog.init(
                trackGroups,
                trackSelectionParameters,
                titleId,
                allowAdaptiveSelections,
                allowMultipleOverrides,
                onClickListener,
                onDismissListener
            )
            return dialog
        }

        private fun getTrackTypeString(resources: Resources, trackType: Int): String {
            return when (trackType) {
                C.TRACK_TYPE_VIDEO -> resources.getString(R.string.exo_track_selection_title_video)
                C.TRACK_TYPE_AUDIO -> resources.getString(R.string.exo_track_selection_title_audio)
                C.TRACK_TYPE_TEXT -> resources.getString(R.string.exo_track_selection_title_text)
                else -> throw IllegalArgumentException()
            }
        }

//        fun createForPlayer(exoPlayer: ExoPlayer, function: () -> Unit): TrackSelectionDialog {
//            TODO("Not yet implemented")
//        }
    }



}


//
///** Dialog to select tracks.  */
//class TrackSelectionDialog : DialogFragment() {
//    /** Called when tracks are selected.  */
//    interface TrackSelectionListener {
//        fun onTracksSelected(trackSelectionParameters: TrackSelectionParameters?)
//    }
//    private val tabFragments: SparseArray<TrackSelectionViewFragment>
//    private val tabTrackTypes: ArrayList<Int>
//    private var titleId = 0
//    private var onClickListener: DialogInterface.OnClickListener? = null
//    private var onDismissListener: DialogInterface.OnDismissListener? = null
//    init {
//        tabFragments = SparseArray<TrackSelectionViewFragment>()
//        tabTrackTypes = ArrayList()
//        retainInstance = true
//    }
//    private fun init(
//        tracks: Tracks,
//        trackSelectionParameters: TrackSelectionParameters,
//        titleId: Int,
//        allowAdaptiveSelections: Boolean,
//        allowMultipleOverrides: Boolean,
//        onClickListener: DialogInterface.OnClickListener,
//        onDismissListener: DialogInterface.OnDismissListener?
//    ) {
//        this.titleId = titleId
//        this.onClickListener = onClickListener
//        this.onDismissListener = onDismissListener
//        for (i in SUPPORTED_TRACK_TYPES.indices) {
//            val trackType: @C.TrackType Int = SUPPORTED_TRACK_TYPES[i]
//            val trackGroups = ArrayList<Tracks.Group>()
//            for (trackGroup in tracks.groups) {
//                if (trackGroup.type == trackType) {
//                    trackGroups.add(trackGroup)
//                }
//            }
//            if (trackGroups.isNotEmpty()) {
//                val tabFragment = TrackSelectionViewFragment()
//                tabFragment.init(
//                    trackGroups,
//                    trackSelectionParameters.disabledTrackTypes.contains(trackType),
//                    trackSelectionParameters.overrides,
//                    allowAdaptiveSelections,
//                    allowMultipleOverrides
//                )
//                tabFragments.put(trackType, tabFragment)
//                tabTrackTypes.add(trackType)
//            }
//        }
//    }
//    fun getIsDisabled(trackType: Int): Boolean {
//        val trackView: TrackSelectionViewFragment = tabFragments.get(trackType)
//        return trackView != null && trackView.isDisabled
//    }
//    fun getOverrides(trackType: Int): Map<TrackGroup, TrackSelectionOverride> {
//        val trackView: TrackSelectionViewFragment = tabFragments.get(trackType)
//        return if (trackView == null) emptyMap<TrackGroup, TrackSelectionOverride>() else trackView.overrides!!
//    }
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        // We need to own the view to let tab layout work correctly on all API levels. We can't use
//        // AlertDialog because it owns the view itself, so we use AppCompatDialog instead, themed using
//        // the AlertDialog theme overlay with force-enabled title.
//        val dialog = AppCompatDialog(requireActivity(), R.style.TrackSelectionDialogThemeOverlay)
//        dialog.setTitle(titleId)
//        return dialog
//    }
//    override fun onDismiss(dialog: DialogInterface) {
//        super.onDismiss(dialog)
//        onDismissListener?.onDismiss(dialog)
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        val dialogView: View = inflater.inflate(R.layout.track_selection_dialog, container, false)
//        val tabLayout: TabLayout =
//            dialogView.findViewById<TabLayout>(R.id.track_selection_dialog_tab_layout)
//        val viewPager: ViewPager =
//            dialogView.findViewById<ViewPager>(R.id.track_selection_dialog_view_pager)
//        val cancelButton =
//            dialogView.findViewById<Button>(R.id.track_selection_dialog_cancel_button)
//        val okButton = dialogView.findViewById<Button>(R.id.track_selection_dialog_ok_button)
//        viewPager.setAdapter(FragmentAdapter(childFragmentManager))
//        tabLayout.setupWithViewPager(viewPager)
//        tabLayout.setVisibility(if (tabFragments.size() > 1) View.VISIBLE else View.GONE)
//        cancelButton.setOnClickListener { view: View? -> dismiss() }
//        okButton.setOnClickListener { view: View? ->
//            onClickListener?.onClick(dialog, DialogInterface.BUTTON_POSITIVE)
//            dismiss()
//        }
//        return dialogView
//    }
//    private inner class FragmentAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(
//        fragmentManager!!,
//        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
//    ) {
//        override fun getItem(position: Int): Fragment {
//            return tabFragments.get(tabTrackTypes[position])
//        }
//
//        val count: Int
//            get() = tabTrackTypes.size
//
//        override fun getCount(): Int {
//            TODO("Not yet implemented")
//        }
//
//        override fun getPageTitle(position: Int): CharSequence? {
//            return getTrackTypeString(resources, tabTrackTypes[position])
//        }
//    }
//    class TrackSelectionViewFragment : Fragment(), TrackSelectionView.TrackSelectionListener {
//        private var trackGroups: List<Tracks.Group>? = null
//        private var allowAdaptiveSelections = false
//        private var allowMultipleOverrides = false
//        var isDisabled = false
//        var overrides: Map<TrackGroup, TrackSelectionOverride>? = null
//        init {
//            retainInstance = true
//        }
//        fun init(
//            trackGroups: List<Tracks.Group>?,
//            isDisabled: Boolean,
//            overrides: Map<TrackGroup?, TrackSelectionOverride?>?,
//            allowAdaptiveSelections: Boolean,
//            allowMultipleOverrides: Boolean
//        ) {
//            this.trackGroups = trackGroups
//            this.isDisabled = isDisabled
//            this.allowAdaptiveSelections = allowAdaptiveSelections
//            this.allowMultipleOverrides = allowMultipleOverrides
//            // TrackSelectionView does this filtering internally, but we need to do it here as well to
//            // handle the case where the TrackSelectionView is never created.
//            this.overrides = HashMap<TrackGroup, TrackSelectionOverride>(
//                TrackSelectionView.filterOverrides(overrides, trackGroups, allowMultipleOverrides)
//            )
//        }
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//            val rootView: View = inflater.inflate(
//                R.layout.exo_track_selection_dialog,
//                container,  /* attachToRoot= */
//                false)
//            @SuppressLint("MissingInflatedId", "LocalSuppress")
//            val trackSelectionView: TrackSelectionView =
//                rootView.findViewById<TrackSelectionView>(R.id.exo_track_selection_view)
//            trackSelectionView.setShowDisableOption(true)
//            trackSelectionView.setAllowMultipleOverrides(allowMultipleOverrides)
//            trackSelectionView.setAllowAdaptiveSelections(allowAdaptiveSelections)
//            trackSelectionView.init(
//                trackGroups!!,
//                isDisabled,
//                overrides!!,  /* trackFormatComparator= */
//                null,  /* listener= */
//                this
//            )
//            return rootView
//        }
//        override fun onTrackSelectionChanged(
//            isDisabled: Boolean, overrides: Map<TrackGroup, TrackSelectionOverride>
//        ) {
//            this.isDisabled = isDisabled
//            this.overrides = overrides
//        }
//    }
//
//    companion object {
//        val SUPPORTED_TRACK_TYPES =
//            ImmutableList.of<Int>(C.TRACK_TYPE_VIDEO, C.TRACK_TYPE_AUDIO, C.TRACK_TYPE_TEXT)
//        fun willHaveContent(player: Player): Boolean {
//            return willHaveContent(player.getCurrentTracks())
//        }
//        fun willHaveContent(tracks: Tracks): Boolean {
//            for (trackGroup in tracks.groups) {
//                if (SUPPORTED_TRACK_TYPES.contains(trackGroup.type)) {
//                    return true
//                }
//            }
//            return false
//        }
//        fun createForPlayer(
//            player: Player, onDismissListener: DialogInterface.OnDismissListener?
//        ): TrackSelectionDialog {
//            return createForTracksAndParameters(
//                R.string.track_selection_title,
//                player.currentTracks,
//                player.trackSelectionParameters,  /* allowAdaptiveSelections= */
//                true,  /* allowMultipleOverrides= */
//                false,
//                TrackSelectionListener { parameters: TrackSelectionParameters? ->
//                    if (parameters != null) {
//                        player.trackSelectionParameters = parameters
//                    }
//                },
//                onDismissListener
//            )
//        }
//        private fun createForTracksAndParameters(
//            titleId: Int, tracks: Tracks, trackSelectionParameters: TrackSelectionParameters,
//            allowAdaptiveSelections: Boolean, allowMultipleOverrides: Boolean, trackSelectionListener: TrackSelectionListener,
//            onDismissListener: DialogInterface.OnDismissListener?): TrackSelectionDialog {
//            val trackSelectionDialog = TrackSelectionDialog()
//            trackSelectionDialog.init(tracks, trackSelectionParameters, titleId, allowAdaptiveSelections, allowMultipleOverrides,  /* onClickListener= */
//                { dialog: DialogInterface?, which: Int ->
//                    val builder: TrackSelectionParameters.Builder =
//                        trackSelectionParameters.buildUpon()
//                    for (i in SUPPORTED_TRACK_TYPES.indices) {
//                        val trackType = SUPPORTED_TRACK_TYPES[i]
//                        builder.setTrackTypeDisabled(
//                            trackType,
//                            trackSelectionDialog.getIsDisabled(trackType)
//                        )
//                        builder.clearOverridesOfType(trackType)
//                        val overrides: Map<TrackGroup, TrackSelectionOverride> =
//                            trackSelectionDialog.getOverrides(trackType)
//                        for (override in overrides.values) {
//                            builder.addOverride(override)
//                        }
//                    }
//                    trackSelectionListener.onTracksSelected(builder.build())
//                },
//                onDismissListener
//            )
//            return trackSelectionDialog
//        }
//
//        private fun getTrackTypeString(resources: Resources, trackType: @C.TrackType Int): String {
//            return when (trackType) {
//                C.TRACK_TYPE_VIDEO -> resources.getString(R.string.exo_track_selection_title_video)
//                C.TRACK_TYPE_AUDIO -> resources.getString(R.string.exo_track_selection_title_audio)
//                C.TRACK_TYPE_TEXT -> resources.getString(R.string.exo_track_selection_title_text)
//                else -> throw IllegalArgumentException()
//            }
//        }
//    }
//}