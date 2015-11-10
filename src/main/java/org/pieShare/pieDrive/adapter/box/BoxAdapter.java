/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFile.Info;
import com.box.sdk.BoxFolder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.pieShare.pieDrive.adapter.api.Adaptor;
import org.pieShare.pieDrive.adapter.model.PieDriveFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author richy
 */
public class BoxAdapter implements Adaptor {

    @Autowired
    private BoxAuthentication boxAuthentication;
    private BoxAPIConnection api;

    @PostConstruct
    public void Init() {
        api = boxAuthentication.authenticate();
    }

    @Override
    public void delte(PieDriveFile file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void upload(PieDriveFile file) {
        BoxFile boxFile = new BoxFile(api, file.getUuid());
        BoxFile.Info info = boxFile.new Info();
        info.setName(file.getUuid());
        boxFile.updateInfo(info);

        InputStream stream;
        try {
            stream = file.getFileData();

            getRootFolder().uploadFile(stream, file.getUuid());
            stream.close();
        } catch (FileNotFoundException ex) {
            //ToDo: Handle Error.
        } catch (IOException ex) {
             //ToDo: Handle Error.
        }
    }

    @Override
    public void download(PieDriveFile file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Info getFileinfo(PieDriveFile file) {
        BoxFile boxFile = new BoxFile(api, file.getUuid());
        return boxFile.getInfo();
    }

    public BoxFolder getRootFolder() {
        return BoxFolder.getRootFolder(api);
    }

}
