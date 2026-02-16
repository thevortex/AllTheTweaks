package com.thevortex.repack.com.jagrosh.discordipc.pipe;


/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com). Licensed under the Apache License, Version 2.0.
 */
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Windows named pipe implementation using RandomAccessFile.
 */
public class WindowsPipe implements Pipe {

    private final RandomAccessFile file;

    public WindowsPipe(String path) throws IOException {
        this.file = new RandomAccessFile(path, "rw");
    }

    @Override
    public void write(byte[] data) throws IOException {
        file.write(data);
    }

    @Override
    public int readInt() throws IOException {
        return file.readInt();
    }

    @Override
    public void readFully(byte[] data) throws IOException {
        file.readFully(data);
    }

    @Override
    public boolean hasData() throws IOException {
        return file.length() > 0;
    }

    @Override
    public void close() throws IOException {
        file.close();
    }
}
