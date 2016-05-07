package com.thirds.adl.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

public class AdlFiles {

    private static String projectName = "";

    public static boolean isProjectActive() {

        FileHandle projectFile = getFileAtRoot("project.txt");
        if (projectFile.exists() && getFileAtRoot(projectFile.readString() + "/").exists()) {
            projectName = projectFile.readString();
            return true;
        }
        else
            return false;
    }

    public static void setProjectName(String projectName) {

        AdlFiles.projectName = projectName;
        createFileAtRoot("project.txt", projectName);
    }

    private static void createFileAtRoot(String path, String input) {

        Gdx.app.log("Create File", path);
        FileHandle fileHandle = Gdx.files.external("Documents/ADL/" + path);
        try {
            if (!fileHandle.exists()) {
                boolean success = fileHandle.parent().file().mkdirs();
                success |= fileHandle.file().createNewFile();
                if (!success) {
                    Gdx.app.log("Error while trying to create new file", "Documents/ADL" + projectName + "/" + path);
                    Gdx.app.exit();
                }
            }
            fileHandle.writeString(input, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static FileHandle getFileAtRoot(String path) {

        return Gdx.files.external("Documents/ADL/" + path);
    }

    private static FileHandle getFile(String path) {

        return getFileAtRoot(projectName + "/" + path);
    }

    public static FileHandle getAdlFile(String path) {

        return getFile(path + ".adl");
    }


    private static void createFile(String path, String input) {

        createFileAtRoot(projectName + "/" + path, input);
    }

    private static void createAdlFile(String path, String input) {

        createFile(path + ".adl", input);
    }

    public static void createFileFromTemplate(String path, String template) {

        createFile(path, Gdx.files.internal("adlTemplates/" + template).readString());
    }

    public static void createAdlFileFromTemplate(String path, String template) {

        createAdlFile(path, Gdx.files.internal("adlTemplates/" + template + ".template.adl").readString());
    }
}
