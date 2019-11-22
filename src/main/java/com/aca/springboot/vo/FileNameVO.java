package com.aca.springboot.vo;

import java.io.File;

/**
 * @ClassName FileNameVO
 * @Author HWG
 * @Time 2019/11/22 14:30
 */

public class FileNameVO extends File {
    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public FileNameVO(String pathname) {
        super(pathname);
    }
}
