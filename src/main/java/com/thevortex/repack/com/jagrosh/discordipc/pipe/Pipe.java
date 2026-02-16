package com.thevortex.repack.com.jagrosh.discordipc.pipe;

/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com). Licensed under the Apache License, Version 2.0.
 * Modifications for Unix socket support.
 */

import java.io.Closeable;
import java.io.IOException;

/**
 * Abstraction for IPC pipe communication that handles both Windows named pipes
 * and Unix domain sockets.
 */
public interface Pipe extends Closeable {

    /**
     * Writes data to the pipe.
     *
     * @param data The data to write.
     * @throws IOException If an I/O error occurs.
     */
    void write(byte[] data) throws IOException;

    /**
     * Reads a 4-byte integer from the pipe.
     *
     * @return The integer read.
     * @throws IOException If an I/O error occurs.
     */
    int readInt() throws IOException;

    /**
     * Reads exactly the specified number of bytes from the pipe.
     *
     * @param data The buffer to read into.
     * @throws IOException If an I/O error occurs or EOF is reached.
     */
    void readFully(byte[] data) throws IOException;

    /**
     * Checks if data is available to read.
     *
     * @return true if data is available.
     * @throws IOException If an I/O error occurs.
     */
    boolean hasData() throws IOException;

    /**
     * Opens a pipe connection to the specified IPC path.
     *
     * @param path The IPC path.
     * @return A Pipe instance.
     * @throws IOException If the connection cannot be established.
     */
    static Pipe openPipe(String path) throws IOException {
        if (System.getProperty("os.name").contains("Win")) {
            return new WindowsPipe(path);
        } else {
            return new UnixPipe(path);
        }
    }
}