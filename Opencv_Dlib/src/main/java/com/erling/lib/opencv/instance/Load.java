package com.erling.lib.opencv.instance;

import com.sun.jna.Library;
import com.sun.jna.Native;


public class Load {
    private final Class<? extends Library> targetInterface;

    public Load(Class<? extends Library> targetInterface) {
        this.targetInterface = targetInterface;
    }
    @SuppressWarnings("unchecked")
    public <T extends Library> T loading() {
        try{
            LibraryAnn annotation = targetInterface.getAnnotation(LibraryAnn.class);
            if (annotation != null && annotation.WindowsPath()!= null) {
                return (T) Native.load(
                        annotation.WindowsPath()+".dll",
                        targetInterface
                );
            }else{
                return null;
            }
        }catch(Throwable e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
