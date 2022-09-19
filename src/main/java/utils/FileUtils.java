package utils;

import java.io.IOException;

public class FileUtils {

    public static void runFile(String filePath) {
        //Run from Windows
//      Runtime runtime = Runtime.getRuntime();
//      runtime.exec("cmd /c start dockerUp.txt"); // run from Windows

        // Run from MAC
        String[] args = new String[]{"/bin/bash", "-c", "./".concat(filePath)};
        System.out.println("Run file: ".concat(filePath));
        try {
            new ProcessBuilder(args).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
