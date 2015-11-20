/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.pieShare.pieDrive.adapter.box.configuration.BoxAdapterConfig;
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
public class BoxAuthenticationTest {

    @Autowired
    private BoxAdapter boxAdapter;

    public BoxAuthenticationTest() {
    }

    @Test
    public void testUpload() {

        UUID uid = UUID.randomUUID();
        File testFile = new File(uid.toString());
        
        if(testFile.exists()) testFile.delete();
        
        FileOutputStream ff;
        try {
            ff = new FileOutputStream(testFile);
            ff.write("This is a test content".getBytes());
            ff.close();
        } catch (FileNotFoundException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }

        PieDriveFile file = new PieDriveFile();

        file.setUuid(uid.toString());
        
        boxAdapter.upload(file);
        testFile.delete();
        try
        {
        boxAdapter.getFileinfo(file);
        
        boxAdapter.delte(file);
        
        
            boxAdapter.getFileinfo(file);
            Assert.fail("Should be deleted");
        }
        catch(Exception ex)
        {
            String a  = "";
        }
    }

}
