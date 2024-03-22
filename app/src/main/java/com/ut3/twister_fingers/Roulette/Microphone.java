package com.ut3.twister_fingers.Roulette;

import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class Microphone {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private final Context context;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private AudioRecord recorder;
    private Thread recordingThread;
    private boolean isRecording = false;


    public interface MicrophoneListener {
        void onBlowDetected();
    }

    private MicrophoneListener listener;

    public void setMicrophoneListener(MicrophoneListener listener) {
        this.listener = listener;
    }


    public Microphone(Context context) {
        this.context = context;
        requestRecordAudioPermission();
    }

    private void requestRecordAudioPermission() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }


    public void startRecording() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        int bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
        recorder.startRecording();
        isRecording = true;

        recordingThread = new Thread(new Runnable() {
            public void run() {
                while (isRecording) {
                    short[] buffer = new short[bufferSize];
                    int bufferReadResult = recorder.read(buffer, 0, bufferSize);
                    if (bufferReadResult > 0) {
                        processAudioData(buffer, bufferReadResult);
                    }
                }
            }
        });

        recordingThread.start();
    }

    public void stopRecording() {
        isRecording = false;
        if (recorder != null) {
            try {
                recorder.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            if (recordingThread != null) {
                recordingThread = null;
            }
        }
    }

    private void processAudioData(short[] buffer, int bufferSize) {
        double amplitude = 0;
        for (short value : buffer) {
            amplitude += Math.abs(value);
        }
        amplitude /= bufferSize;

        double threshold = 3000.0;

        if (amplitude > threshold) {
            Log.d("DEBUG MICRO", "AAAAAAAAAAAAAAAAHHHHHH");
            listener.onBlowDetected();
        }
    }


}
