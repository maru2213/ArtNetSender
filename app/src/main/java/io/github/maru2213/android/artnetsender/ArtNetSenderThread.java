package io.github.maru2213.android.artnetsender;

import ch.bildspur.artnet.ArtNetClient;

public class ArtNetSenderThread extends Thread {

    private MainActivity mainActivity;
    private ArtNetClient artnet = new ArtNetClient();

    private boolean isActive = true;

    public ArtNetSenderThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        artnet.start();
    }

    public void stopThread() {
        isActive = false;
    }

    @Override
    public void run() {
        try {
            while (isActive) {
                for (String ip : mainActivity.getIPList()) {
                    artnet.unicastDmx(ip, 0, 0, mainActivity.getData());
                }
                sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        artnet.stop();
    }
}
