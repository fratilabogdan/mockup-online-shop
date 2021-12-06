package ro.shop.controller;

import org.junit.jupiter.api.*;
import ro.shop.model.Product;
import ro.shop.model.product.ElectronicProduct;
import ro.shop.model.product.WearableProduct;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {
    public ProductController productController;
    public int idWearable;
    public int idElectronic;

    @BeforeEach
    public void initi(){
        productController = new ProductController();
        Product validWearable = new WearableProduct("Wearable Test",1,1,"XS","Test material");
        Product validElectronic = new ElectronicProduct("Electronic test",1,1,true);
        productController.add(validWearable);
        idWearable= productController.lastID();
        productController.add(validElectronic);
        idElectronic= productController.lastID();
    }
    @AfterEach
    public void clean(){
        productController.delete(idWearable);
        productController.delete(idElectronic);
    }
    @AfterAll
    public static void cleanAll(){
        ProductController productController = new ProductController();
        productController.removeDuplicates();
    }

    @Test
    public void testGetElectronic(){
        assertEquals(true, productController.getProduct(idElectronic).getId()==idElectronic);
    }
    public void testGetWearable(){
        assertEquals(true, productController.getProduct(idWearable).getId()==idWearable);
    }
    @Test
    public void testProductUpdateName(){
        productController.updateName(idElectronic, "New for test ELectronic name");
        assertEquals(true, productController.getProduct(idElectronic).getName().equals("New for test ELectronic name"));
    }

    //Problem. lastID for these or something
    @Test
    public void testProductUpdateNameFalse(){
        productController.updateName(idElectronic, productController.getProduct(idWearable).getName());
        System.out.println(productController.getProduct(idElectronic));
        assertEquals(false, productController.getProduct(idElectronic).getName().equals(productController.getProduct(idWearable).getName()));
    }

}