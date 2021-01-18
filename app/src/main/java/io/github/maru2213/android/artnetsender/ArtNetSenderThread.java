/*
 * Copyright 2021 maru2213
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
