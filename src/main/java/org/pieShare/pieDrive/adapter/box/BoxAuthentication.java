/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.JWTEncryptionPreferences;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pieShare.pieTools.pieUtilities.service.pieLogger.PieLogger;

/**
 *
 * @author richy
 */
public class BoxAuthentication {

    private static final String USER_ID = "259944917";
    private static final int MAX_DEPTH = 1;

    private static final String CLIENT_ID = "bryxc5clldhd2q8d7gns5vo0iha4prx5";
    private static final String CLIENT_SECRET = "BrwCVQU7OwRZ5JK8HuTuUc2WEYLLjhLd";
    private static final String PUBLIC_KEY_ID = "l6rj58un";//"a3gs5sfd";
    private static final String PRIVATE_KEY_FILE = "AIC_BOX_Privkey.pem";
    private static final String PRIVATE_KEY_PASSWORD = "foobar";//1234";
    private static BoxAPIConnection api = null;

    public BoxAPIConnection getConnection() {
        return api;
    }

    public BoxAPIConnection authenticate(String token) {

        //BoxAPIConnection api = new BoxAPIConnection("TzEyYLq9eiERs8KfPIXouWWx1JO2oQWy");
        String privString = String.format("%s/.ssh/%s", System.getProperty("user.home"), PRIVATE_KEY_FILE);

        String privateKey = null;
        try {
            privateKey = new String(Files.readAllBytes(Paths.get(privString)));
        } catch (IOException ex) {
            PieLogger.error(this.getClass(), "Error", ex);
        }

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        //BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection(USER_ID, CLIENT_ID,
        //         CLIENT_SECRET, encryptionPref);
        api = new BoxAPIConnection(CLIENT_ID, CLIENT_SECRET, token);
        return api;
        //https://app.box.com/api/oauth2/authorize?response_type=code&client_id=bryxc5clldhd2q8d7gns5vo0iha4prx5&redirect_uri=https://127.0.0.1&state=security_token%3DKnhMJatFipTAnM0nHlZA
    }
    
    public void authenticateWithDeveloperToken(String developerToken)
    {
         api = new BoxAPIConnection(developerToken);
    }
    
    public String getURI()
    {
        return "https://app.box.com/api/oauth2/authorize?response_type=code&client_id=bryxc5clldhd2q8d7gns5vo0iha4prx5&redirect_uri=https://127.0.0.1:8080/token&state=security_token%3DKnhMJatFipTAnM0nHlZA";
    }

}