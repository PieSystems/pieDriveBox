/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.JWTEncryptionPreferences;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author richy
 */
public class BoxAuthentication {

    private static final String CLIENT_ID = "51k2ejwc1wo8gntvcqf5s1p1fnw7fgk5";
    private static final String CLIENT_SECRET = "o8OPR4N8Rdb8PIbeKAQ8MvaPg5PKT8pc";
    private static final String USER_ID = "256114953";
    private static final String PUBLIC_KEY_ID = "4jrket0u";
    private static final String PRIVATE_KEY_FILE = "ssh/box_private_key.pem";
    private static final String PRIVATE_KEY_PASSWORD = "1234";
    private static final int MAX_DEPTH = 1;

    public boolean Authenticate() {

            //BoxAPIConnection api = new BoxAPIConnection("51k2ejwc1wo8gntvcqf5s1p1fnw7fgk5", "o8OPR4N8Rdb8PIbeKAQ8MvaPg5PKT8pc");
        //String token = api.getAccessToken();
           //BoxAPIConnection api = new BoxAPIConnection("qDDtm9TIVST4rGxRhx0HoDjFQWGZir0u");
         //   string privateKey = String.format("%s/.ssh/", System.getProperty("user.home"));
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

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection(USER_ID, CLIENT_ID, CLIENT_SECRET, encryptionPref);

        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();

        BoxFolder folder = BoxFolder.getRootFolder(api);

        for (BoxItem.Info info : folder.getChildren()) {
            System.out.println(info.getName());
        }

        return true;
    }

}
