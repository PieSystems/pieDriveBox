/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxUser;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.JWTEncryptionPreferences;

public final class CreateAppUser {

    private static final String CLIENT_ID = "bryxc5clldhd2q8d7gns5vo0iha4prx5";
    private static final String CLIENT_SECRET = "BrwCVQU7OwRZ5JK8HuTuUc2WEYLLjhLd";
    private static final String ENTERPRISE_ID = "776293";
    private static final String PUBLIC_KEY_ID = "a3gs5sfd";
    private static final String PRIVATE_KEY_FILE = "box_private_key.pem";
    private static final String PRIVATE_KEY_PASSWORD = "1234";
    private static final String APP_USER_NAME = "AIC_User1";

    public CreateAppUser() {
    }

    public void create() throws IOException {
        // Turn off logging to prevent polluting the output.
        Logger.getLogger("com.box.sdk").setLevel(Level.OFF);

        String privString = String.format("%s/.ssh/%s", System.getProperty("user.home"), PRIVATE_KEY_FILE);

        String privateKey = new String(Files.readAllBytes(Paths.get(privString)));

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
                ENTERPRISE_ID, CLIENT_ID, CLIENT_SECRET, encryptionPref);

        BoxUser.Info user = BoxUser.createAppUser(api, APP_USER_NAME);
        System.out.format("User created with name %s and id %s\n\n", APP_USER_NAME, user.getID());
    }
}
