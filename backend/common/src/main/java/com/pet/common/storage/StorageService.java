package com.pet.common.storage;

import java.io.InputStream;

@SuppressWarnings("unused")
public interface StorageService {

    void upload(String path, String filename, InputStream inputStream);

    InputStream download(String path, String filename);

    void delete(String path, String filename);

    String getFileUrl(String path, String filename);
}
