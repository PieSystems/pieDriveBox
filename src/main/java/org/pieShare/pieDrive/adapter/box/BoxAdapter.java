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
import com.box.sdk.BoxItem;
import com.box.sdk.ProgressListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.PostConstruct;
import org.pieShare.pieDrive.adapter.api.Adaptor;
import org.pieShare.pieDrive.adapter.exceptions.AdaptorException;
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
    private boolean showProgress;

    @PostConstruct
    public void Init() {
        showProgress = false;
        api = boxAuthentication.authenticate();
    }

    @Override
    public synchronized void delete(PieDriveFile file) throws AdaptorException {
        BoxFile boxFile = findFileByName(file.getUuid());
        boxFile.delete();
    }

    @Override
    public synchronized void upload(PieDriveFile file, InputStream stream) throws AdaptorException{
        try {
            Info info = getRootFolder().uploadFile(stream, file.getUuid());
            stream.close();
        } catch (FileNotFoundException ex) {
            throw new AdaptorException(ex);
        } catch (IOException ex) {
            throw new AdaptorException(ex);
        }
    }

    @Override
    public synchronized void download(PieDriveFile file, OutputStream stream) throws AdaptorException {
        BoxFile boxFile = findFileByName(file.getUuid());

        ProgressListener p = (long numBytes, long totalBytes) -> {
            double percentComplete = numBytes / totalBytes;
        };

        try {
            if (showProgress) {
                boxFile.download(stream, p);
            } else {
                boxFile.download(stream);
            }
            stream.close();
        } catch (FileNotFoundException ex) {
            throw new AdaptorException(ex);
        } catch (IOException ex) {
             throw new AdaptorException(ex);
        }
    }

    public Info getFileInfo(PieDriveFile file) {
        BoxFile boxFile = new BoxFile(api, file.getUuid());
        return boxFile.getInfo();
    }

    public BoxFolder getRootFolder() {
        return BoxFolder.getRootFolder(api);
    }
    
    public BoxFile findFileByName(String name)
    {
        for(BoxItem.Info file : getRootFolder().getChildren())
        {
            if(file.getName().equals(name))
                return new BoxFile(api, file.getID());
        }
        //ToDo: Think about ... maybe an exception
        return null;
    }

}
