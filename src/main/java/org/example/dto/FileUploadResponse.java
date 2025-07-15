package org.example.dto;

public class FileUploadResponse {
    private boolean success;
    private String message;
    private String fileName;
    private String fileId;
    private long size;
    private String downloadUrl;

    public FileUploadResponse(boolean success, String message, String fileName, String fileId, long size, String downloadUrl) {
        this.success = success;
        this.message = message;
        this.fileName = fileName;
        this.fileId = fileId;
        this.size = size;
        this.downloadUrl = downloadUrl;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getFileName() { return fileName; }
    public String getFileId() { return fileId; }
    public long getSize() { return size; }
    public String getDownloadUrl() { return downloadUrl; }
}