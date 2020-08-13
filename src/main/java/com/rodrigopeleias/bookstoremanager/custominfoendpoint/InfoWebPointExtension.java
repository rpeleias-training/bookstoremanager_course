package com.rodrigopeleias.bookstoremanager.custominfoendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class InfoWebPointExtension {

    @Autowired
    private InfoEndpoint delegate;

    @ReadOperation
    public WebEndpointResponse<Map> info() {
        Map<String, Object> info = this.delegate.info();
        Integer status = getStatus(info);
        Map<String, Object> customInfo = new HashMap<>(info);
        customInfo.put("customInfo", "handOn");
        return new WebEndpointResponse<>(customInfo, status);
    }

    private Integer getStatus(Map<String, Object> info) {
        return 200;
    }
}
