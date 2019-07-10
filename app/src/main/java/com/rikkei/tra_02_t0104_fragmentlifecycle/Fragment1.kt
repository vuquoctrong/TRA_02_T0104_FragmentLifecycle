package com.rikkei.tra_02_t0104_fragmentlifecycle

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkei.tra_02_t0104_fragmentlifecycle.MainActivity.Companion.replaceFragment
import com.rikkei.tra_02_t0104_fragmentlifecycle.constrant.Define
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment1 : Fragment(),View.OnClickListener {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0

    private val TAG ="LOL"

    companion object {

        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): Fragment1 {
            val fragment = Fragment1()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment

        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG, "on Attach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"on Create")
        mediaPlayer = MediaPlayer.create(activity,R.raw.haychaochoanh)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_1,container,false)
        Log.d(TAG,"on CreateView")
    }

    override fun onStart() {
        super.onStart()
        btnStartFragment2.setOnClickListener(this)
        Log.d(TAG,"on Start")
        mediaPlayer?.start()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"on Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"on Pause")
        pauseMedia()


    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"on Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"on Destroy")
        mediaPlayer?.apply {
            stop()
            release()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "on SaveInstanceState")
        outState.putInt(Define.BUNDLE_POSITION,currentPosition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(Define.BUNDLE_POSITION)
            mediaPlayer?.seekTo(currentPosition)
        }

    }
    override fun onClick(v: View?) {
        activity?.let {
            replaceFragment(it,Fragment2.newInstance("",""))
        }
    }
    private  fun pauseMedia(){
        mediaPlayer?.apply {
            pause()
            this@Fragment1.currentPosition = currentPosition
        }
    }

}