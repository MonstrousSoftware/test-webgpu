package com.monstrous.test.teavm;

import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle;
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration;
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder;
import com.github.xpenatan.gdx.backends.teavm.config.plugins.TeaReflectionSupplier;
import org.teavm.tooling.TeaVMTargetType;
import org.teavm.tooling.TeaVMTool;
import org.teavm.vm.TeaVMOptimizationLevel;

import java.io.File;
import java.io.IOException;

public class BuildTeaVM {

    public static void main(String[] args) throws IOException {
        String reflectionPackage = "com.badlogic.gdx.math";
        TeaReflectionSupplier.addReflectionClass(reflectionPackage);

        TeaBuildConfiguration teaBuildConfiguration = new TeaBuildConfiguration();
        teaBuildConfiguration.assetsPath.add(new AssetFileHandle("../assets"));
        teaBuildConfiguration.shouldGenerateAssetFile = true;
        teaBuildConfiguration.webappPath = new File("build/dist").getCanonicalPath();

        TeaVMTool tool = TeaBuilder.config(teaBuildConfiguration);
        tool.setObfuscated(false);
        tool.setTargetType(TeaVMTargetType.WEBASSEMBLY_GC);
        tool.setOptimizationLevel(TeaVMOptimizationLevel.ADVANCED);
        tool.setMainClass(TeaVMTestLauncher.class.getName());
        TeaBuilder.build(tool);
    }
}
