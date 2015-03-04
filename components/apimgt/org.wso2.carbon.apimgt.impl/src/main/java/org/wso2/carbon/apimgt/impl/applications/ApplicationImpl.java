package org.wso2.carbon.apimgt.impl.applications;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.Application;
import org.wso2.carbon.apimgt.api.model.OAuthApplicationInfo;
import org.wso2.carbon.apimgt.api.model.Subscriber;
import org.wso2.carbon.apimgt.impl.dao.ApiMgtDAO;


public class ApplicationImpl extends Application {

    private static final Log log = LogFactory.getLog(ApplicationImpl.class);
    ApiMgtDAO dao = new ApiMgtDAO();

    public ApplicationImpl(String name, Subscriber subscriber) {
        super(name, subscriber);
    }

    public ApplicationImpl(int appId){
        super(appId);
    }

    /**
     * populate Application
     */
    public void populateApplication() throws APIManagementException {


        Application application = dao.getApplicationById(getId());
        if (application == null) {
            application = dao.getApplicationByName(this.getName(), this.getSubscriber().getName());
        }
        if (application == null) {
            handleException("Application " + this.getName() + " can not found");
        }
        this.setId(application.getId());
        this.setTier(application.getTier());
        if (application.getCallbackUrl() == "") {
            this.setCallbackUrl(null);
        } else {
            this.setCallbackUrl(application.getCallbackUrl());
        }

        this.setDescription(application.getDescription());
        this.setStatus(application.getStatus());
        this.getSubscriber().setId(application.getSubscriber().getId());

    }

    /**
     * update clientID of AM_APPLICATION_KEY_MAPPING that is being generated by OIDC
     * @param keyType
     */
    public void createApplicationMapping(String keyType){
        log.info("Creating ApplicationMapping");
        dao.updateApplicationKeyTypeMapping(this,keyType);
    }

    public void updateAssociateOAuthApp(String keyType, OAuthApplicationInfo oAuthApplication){
        this.addOAuthApp(keyType,oAuthApplication);
        createApplicationMapping(keyType);
    }



    /**
     * Common function for exception handling.
     * @param msg
     * @param e
     * @throws APIManagementException
     */
    protected void handleException(String msg, Exception e) throws APIManagementException {
        log.error(msg, e);
        throw new APIManagementException(msg, e);
    }

    /**
     * Common function for exception handling.
     * @param msg
     * @throws APIManagementException
     */
    protected void handleException(String msg) throws APIManagementException {
        log.error(msg);
        throw new APIManagementException(msg);
    }

}