/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pieShare.pieDrive.adapter.box.configuration;

import org.pieShare.pieDrive.adapter.box.BoxAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author richy
 */
@Configuration
public class BoxAdapterConfig {
    
    @Bean
    public BoxAuthentication boxAuthentication()
    {
        return new BoxAuthentication();
    }
}
