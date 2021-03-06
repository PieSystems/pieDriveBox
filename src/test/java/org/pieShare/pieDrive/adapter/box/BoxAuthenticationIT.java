/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import com.box.sdk.BoxFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.UUID;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pieShare.pieDrive.adapter.box.configuration.BoxAdapterConfig;
import org.pieShare.pieDrive.adapter.exceptions.AdaptorException;
import org.pieShare.pieDrive.adapter.model.PieDriveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author richy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BoxAdapterConfig.class)
public class BoxAuthenticationIT {

    @Autowired
    private BoxAdapter boxAdapter;
    @Autowired
    private BoxAuthentication boxAuthentication;

    @Before
    public void init()
    {
        boxAuthentication.authenticateWithDeveloperToken("0pcQlMYpcUQmsc2Hw017Ski6Sct2VP0O");
    }
    
    public BoxAuthenticationIT() {
    }

    @Test
    public void testUploadDownloadDelete() throws AdaptorException {

        UUID uid = UUID.randomUUID();
        File testFile = new File(uid.toString());

        if (testFile.exists()) {
            testFile.delete();
        }

        byte[] content = "This is a test content".getBytes();

        FileOutputStream ff;
        try {
            ff = new FileOutputStream(testFile);
            ff.write(content);
            ff.close();
        } catch (FileNotFoundException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }

        PieDriveFile file = new PieDriveFile();

        file.setUuid(uid.toString());

        FileInputStream st = null;
        try {
            st = new FileInputStream(testFile);
        } catch (FileNotFoundException ex) {
            Assert.fail();
        }

        try {
            boxAdapter.upload(file, st);
        } catch (AdaptorException ex) {
            Assert.fail();
        }

        File donwloadedFile = new File("downloaded" + file.getUuid());

        try {
            try {
                boxAdapter.download(file, new FileOutputStream(donwloadedFile));
            } catch (AdaptorException ex) {
                Assert.fail();
            }
        } catch (FileNotFoundException ex) {
            Assert.fail();
        }

        byte[] data1 = null;
        try {
            data1 = Files.readAllBytes(donwloadedFile.toPath());
        } catch (IOException ex) {
            Assert.fail();
        }

        Arrays.equals(content, data1);

        donwloadedFile.delete();
        testFile.delete();

        BoxFile boxFile1 = boxAdapter.findFileByName(file.getUuid());
        Assert.assertNotNull(boxFile1);

        try {
            boxAdapter.delete(file);
        } catch (AdaptorException ex) {
            Assert.fail();
        }

        BoxFile boxFile2 = boxAdapter.findFileByName(file.getUuid());
        Assert.assertNull(boxFile2);

    }

}
