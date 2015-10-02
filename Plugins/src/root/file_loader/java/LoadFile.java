/*
 * Copyright (c) 2015. All rights reserved for author and owner of email domain JLyc.Development@gmail.com.
 */

import org.w3c.dom.*;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created 2. 10. 2015.
 *
 * @author JLyc
 */
public class LoadFile {

    private static ExecutorService defaultExecutor = Executors.newFixedThreadPool(5);

    private static FileSystem fileSystem = FileSystems.getDefault();
    private static final Map<String, Future<Document>> loadedFiles = new HashMap<>();

    public LoadFile() {

    }

    public LoadFile(ExecutorService defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
    }

    public LoadFile(ExecutorService defaultExecutor, FileSystem fileSystem) {
        this.defaultExecutor = defaultExecutor;
        this.fileSystem = fileSystem;
    }

    public void setDefaultExecutor(ExecutorService defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
    }



}
