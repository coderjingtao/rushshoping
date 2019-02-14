package com.rushshopping.common;

/**
 * Description: The common return type of server response for json
 * Created by Jingtao Liu on 11/02/2019.
 */
public class ServerResponse {

    //success, fail
    private String status;
    //if status = success, the data is the json data of view object
    //if status = fail, the data is the common failure format
    private Object data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
    private ServerResponse(){}

    public static ServerResponse create(String status,Object data){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(status);
        serverResponse.setData(data);
        return serverResponse;
    }

    public static ServerResponse create(Object data){
        return ServerResponse.create("success",data);
    }
}
