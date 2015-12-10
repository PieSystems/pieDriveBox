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

    private static final String USER_ID = "259944917";
    private static final int MAX_DEPTH = 1;

    private static final String CLIENT_ID = "bryxc5clldhd2q8d7gns5vo0iha4prx5";
    private static final String CLIENT_SECRET = "BrwCVQU7OwRZ5JK8HuTuUc2WEYLLjhLd";
    private static final String PUBLIC_KEY_ID = "a3gs5sfd";
    private static final String PRIVATE_KEY_FILE = "box_private_key.pem";
    private static final String PRIVATE_KEY_PASSWORD = "1234";

    public BoxAPIConnection authenticate() {
        
		BoxAPIConnection api = new BoxAPIConnection("FstPu5QMhUflifJDgkGnpzdCmSvdGyg3");
/*
        String privString = String.format("%s/.ssh/%s", System.getProperty("user.home"), PRIVATE_KEY_FILE);
        
        String privateKey = null;
        try {
            privateKey = new String(Files.readAllBytes(Paths.get(privString)));
        } catch (IOException ex) {
            Logger.getLogger(BoxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection(USER_ID, CLIENT_ID,
                CLIENT_SECRET, encryptionPref);
*/
        return api;
    }

}
