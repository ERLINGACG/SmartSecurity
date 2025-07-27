package com.erling.lib.instance;


import lombok.Getter;

@Getter
public enum InstanceConfig {
    OPENCV_4120_RELEASE("lib\\x64\\debug\\SmartSecurityCoreLibTest.dll"),;

    private final String path;
    InstanceConfig(String path) {
        this.path = path;
    }
}
