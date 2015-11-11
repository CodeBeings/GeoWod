package com.urocks.fragment;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by sony on 13-10-2015.
 */
public class Camera_Fragment extends Fragment implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera.PictureCallback mPictureCallback;
    private Camera.ShutterCallback mShutterCallback;
    private Camera.PictureCallback mJpegcallback;
    private View mView;
    private TextView mCapture,mRecapture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.camera_fragment,container,false);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mSurfaceView = (SurfaceView) mView.findViewById(R.id.surfaceView);
        mCapture = (TextView) mView.findViewById(R.id.capture);
        mCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    captureImage(v);
                }catch (Exception e){

                }

            }
        });
        mRecapture = (TextView) mView.findViewById(R.id.recapture);
        mRecapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     refreshCamera();
                }catch (Exception e){

                }

            }
        });
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mJpegcallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
               /* FileOutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
                    outStream.write(data);
                    outStream.close();
                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }*/
               // Toast.makeText(getActivity(), "Picture Saved", Toast.LENGTH_SHORT).show();
               // refreshCamera();
            }
        };
    }

    private void captureImage(View v) throws IOException{
        mCamera.takePicture(null,null,mJpegcallback);
    }

    private void refreshCamera()
    {
        if (mSurfaceHolder.getSurface()==null)
            return;
        try {
            mCamera.stopPreview();
        }catch (Exception e){

        }
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
        }catch (Exception e){

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
        }catch (RuntimeException e){

        }
       try {
           mCamera.setDisplayOrientation(90);
           mCamera.setPreviewDisplay(mSurfaceHolder);
           mCamera.startPreview();
       }catch (Exception e)
       {

       }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    mCamera.stopPreview();
        mCamera.release();
        mCamera =null;
    }
}
