/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.pieShare.pieDrive.adapter.box.configuration.BoxAdapterConfig;
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
    private BoxAuthentication boxAuthentication;

    @Autowired
    private CreateAppUser createAppUser;

    public BoxAuthenticationTest() {
    }

    @Test
    public void create() {
        createAppUser.create();
    }

    @Test
    public void testAuthenticate() {

        boxAuthentication.Authenticate();

    }

}
