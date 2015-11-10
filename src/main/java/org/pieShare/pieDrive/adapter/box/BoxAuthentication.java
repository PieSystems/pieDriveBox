/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import com.box.sdk.BoxAPIConnection;

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

    public BoxAPIConnection authenticate() {
        BoxAPIConnection api = new BoxAPIConnection("b69031JnSd1PRaLs4JH9nR66k4uuqFzl");
        return api;
        /*
        
        
         //BoxAPIConnection api = new BoxAPIConnection("51k2ejwc1wo8gntvcqf5s1p1fnw7fgk5", "o8OPR4N8Rdb8PIbeKAQ8MvaPg5PKT8pc");
         //String token = api.getAccessToken();
         //BoxAPIConnection api = new BoxAPIConnection("qDDtm9TIVST4rGxRhx0HoDjFQWGZir0u");
         //   string privateKey = String.format("%s/.ssh/", System.getProperty("user.home"));
         String privateKey;
         try {
         privateKey = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)));
            
         } catch (IOException ex) {
         return null;
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

         return api;*/
    }

}
