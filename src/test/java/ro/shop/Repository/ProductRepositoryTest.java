package ro.shop.Repository;

import org.junit.jupiter.api.Test;
import ro.shop.model.Product;
import ro.shop.model.product.ElectronicProduct;
import ro.shop.model.product.WearableProduct;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    @Test
    public void testAdd(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        assertEquals(true, productRepository.getProduct(id).getName().equals("Test add true"));
        productRepository.delete(id);
    }

    @Test
    public void testAllProducts(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        List<Product> list = productRepository.allProducts();
        assertEquals(true, list.size()!=0);
        productRepository.delete(id);
    }
    @Test
    public void testUpdateName(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        assertEquals(true, productRepository.getProduct(id).getName().equals("Test add true"));
        productRepository.delete(id);
    }
    @Test
    public void testUpdatePrice(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        int price=9999;
        productRepository.updatePrice(id,price);
        assertEquals(true, productRepository.getProduct(id).getPrice()==price);
        productRepository.delete(id);
    }
    @Test
    public void testUpdateStock(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        int price=9999;
        productRepository.updateStock(id,price);
        assertEquals(true, productRepository.getProduct(id).getStock()==price);
        productRepository.delete(id);
    }
    @Test
    public void testUpdateIsDangerousFalse(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        boolean danger=false;
        productRepository.updateIsDangerous(id,danger);
        assertEquals(true, ((ElectronicProduct)productRepository.getProduct(id)).isDangerous==false);
        productRepository.delete(id);
    }
    @Test
    public void testUpdateIsDangerousTrue(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        boolean danger=true;
        productRepository.updateIsDangerous(id,danger);
        assertEquals(true, ((ElectronicProduct)productRepository.getProduct(id)).isDangerous==true);
        productRepository.delete(id);
    }
    @Test
    public void testUpdateWearableType(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new WearableProduct("Test add true",1,1,"XS", "Steel"));
        int id=productRepository.lastID();
        String newType = "Test wearable";
        productRepository.updateWearableType(id,newType);
        assertEquals(true, ((WearableProduct)productRepository.getProduct(id)).getWearableType().equals("Test wearable"));
        productRepository.delete(id);
    }

    @Test
    public void testUpdateWearableMat(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new WearableProduct("Test add true",1,1,"XS", "Steel"));
        int id=productRepository.lastID();
        String newType = "Test wearable";
        productRepository.updateMaterial(id,newType);
        assertEquals(true, ((WearableProduct)productRepository.getProduct(id)).getMaterial().equals("Test wearable"));
        productRepository.delete(id);
    }
    @Test
    public void testUpdateNameForWearable(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new WearableProduct("Test add true",1,1,"XS", "Steel"));
        int id=productRepository.lastID();
        String newName = "Test wearable";
        productRepository.updateName(id, newName);
        assertEquals(true, ((WearableProduct)productRepository.getProduct(id)).getName().equals("Test wearable"));
        productRepository.delete(id);
    }
    @Test
    public void testUpdateNameForElectronic(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(new ElectronicProduct("Test add true",1, 1,true));
        int id=productRepository.lastID();
        String newName = "Test wearable";
        productRepository.updateName(id, newName);
        assertEquals(true, ((ElectronicProduct)productRepository.getProduct(id)).getName().equals("Test wearable"));
        productRepository.delete(id);
    }

}