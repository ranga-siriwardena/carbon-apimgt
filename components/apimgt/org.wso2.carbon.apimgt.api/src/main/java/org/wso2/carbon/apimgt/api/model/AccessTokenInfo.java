/*
 *
 *   Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 * /
 */

package org.wso2.carbon.apimgt.api.model;

import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Details about an Access Token.
 */
public class AccessTokenInfo {

    private String consumerKey;

    private String[] scope;

    private String tokenState;

    private String accessToken;

    private long issuedTime;

    private long validityPeriod;

    public String[] getScope() {
        return scope;
    }

    public void setScope(String[] scope) {
        this.scope = scope;
    }

    public String getTokenState() {
        return tokenState;
    }

    public void setTokenState(String tokenState) {
        this.tokenState = tokenState;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getIssuedTime() {
        return issuedTime;
    }

    public long getValidityPeriod() {
        return validityPeriod;
    }

    private HashMap<String,Object> parameters = new HashMap<String, Object>();

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setIssuedTime(long issuedTime) {
        this.issuedTime = issuedTime;
    }

    public void setValidityPeriod(long validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public void addParameter(String paramName, Object paramValue){
        parameters.put(paramName,paramName);
    }

    public Object getParameter(String paramName){
        return parameters.get(paramName);
    }

    /**
     * Sending additional properties as a JSON String.

     */
    public String getJSONString(){

        if(!parameters.containsKey("scopes")){
            parameters.put("scopes",scope);
        }

        if(!parameters.containsKey("tokenState")){
            parameters.put("tokenState",tokenState);
        }

        return JSONObject.toJSONString(parameters);
    }

}