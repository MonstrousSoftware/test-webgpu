package com.monstrous.test.teavm;

import com.github.xpenatan.gdx.backends.teavm.TeaApplicationConfiguration;
import com.monstrous.test.Main;
import com.monstrous.gdx.webgpu.backends.teavm.WgTeaApplication;



public class TeaVMTestLauncher {

    public static void main(String[] args) {
        TeaApplicationConfiguration config = new TeaApplicationConfiguration("canvas");
        config.width = 0;
        config.height = 0;
        config.showDownloadLogs = true;
        config.preloadAssets = true;
        config.useGL30 = true;

        new WgTeaApplication(new Main(), config);
    }
}
