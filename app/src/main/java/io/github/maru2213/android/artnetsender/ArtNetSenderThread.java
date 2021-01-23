/*
 * ArtNetSender - An Android app sending artnet signals to local network
 *  Copyright (C) 2021  maru2213
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
