/*
 * Copyright (c) 2015. All rights reserved for author and owner of email domain JLyc.Development@gmail.com.
 */

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;

/**
 * Created 2. 10. 2015.
 *
 * @author JLyc
 */
public class FileLoader implements Callable<InputStream> {

    private Path path;

    public FileLoader(String filePath) {
        path = FileSystems.getDefault().getPath(filePath);

    }

    @Override
    public InputStream call() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path.toString());


        return null;
    }
}
