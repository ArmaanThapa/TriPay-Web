package com.tripayweb.model.web;

public class UserListDTO {

    private boolean success;
    private long numberOfElements;
    private boolean firstPage;
    private boolean lastPage;
    private long size;
    private long totalPages;
    private Object jsonArray;

    public Object getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(Object jsonArray) {
        this.jsonArray = jsonArray;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

}
