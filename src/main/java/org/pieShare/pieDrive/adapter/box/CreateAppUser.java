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

/**
 *
 * @author richy
 */
public class CreateAppUser {

    private static final String CLIENT_ID = "51k2ejwc1wo8gntvcqf5s1p1fnw7fgk5";
    private static final String CLIENT_SECRET = "o8OPR4N8Rdb8PIbeKAQ8MvaPg5PKT8pc";
    private static final String PUBLIC_KEY_ID = "4jrket0u";
    private static final String PRIVATE_KEY_PASSWORD = "1234";
    private static final String ENTERPRISE_ID = "769989";
    private static final String PRIVATE_KEY_FILE = "ssh/box_private_key.pem";
    private static final String APP_USER_NAME = "AIC_User";

    public CreateAppUser() {
    }

    public boolean create() {
        String privateKey;
        try {
            privateKey = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)));
        } catch (IOException ex) {
            return false;
        }

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
                ENTERPRISE_ID, CLIENT_ID, CLIENT_SECRET, encryptionPref);

        BoxUser.Info user = BoxUser.createAppUser(api, APP_USER_NAME);
        System.out.format("User created with name %s and id %s\n\n", APP_USER_NAME, user.getID());
        return true;
    }
}
