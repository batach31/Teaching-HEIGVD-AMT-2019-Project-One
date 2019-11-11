package ch.heigvd.amt.projectone.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CustomerManagerTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CustomerManager.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void getCustomerById() {
    }

    @Test
    public void createCustomer() {
    }

    @Test
    public void createCustomer1() {
    }

    @Test
    public void deleteCustomer() {
    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void getCustomerByPseudo() {
    }

    @Test
    public void verifyPassword() {
    }
}
