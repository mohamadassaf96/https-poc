package com.example.boilerplate.config;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import org.springframework.lang.NonNull;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class LdapExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(@NonNull TestContext testContext) {
        try {
            InMemoryDirectoryServer ldapServer = testContext.getApplicationContext().getBean(InMemoryDirectoryServer.class);
            ldapServer.shutDown(true);
        } catch (Exception ignored) {
            ignored.printStackTrace();
            System.out.println(ignored.getMessage());
        }
    }
}