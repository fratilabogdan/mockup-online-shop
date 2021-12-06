package ro.shop.controller;

import ro.shop.Repository.ProductRepository;
import ro.shop.model.Product;
import ro.shop.model.User;
import ro.shop.model.product.ElectronicProduct;
import ro.shop.model.product.WearableProduct;

import java.util.Iterator;
import java.util.List;

public class ProductController {
    ProductRepository productRepository;
    List<Product> productList;

    public ProductController(){
        productRepository = new ProductRepository();
        this.productRepository = productRepository;
        this.productList=productRepository.allProducts();
    }

    public boolean add(Product product){
        if(!duplicate(product) && (product.getClass().equals("Electronics") || product.getClass().equals("Wearable"))){
            return productRepository.add(product);
        }
        return false;
    }
    public boolean delete(int id){
        if(containsID(id)){
            return productRepository.delete(id);
        }
        return false;
    }
    public boolean updateName(int id, String newName){
        if(containsID(id)){
            Product product = getProduct(id);
            product.setName(newName);
            if(nbOfDuplicates(product)>2){
                return false;
            } else return productRepository.updateName(id,newName);
        }
        return false;
    }


    public boolean containsID(int id){
        Iterator<Product> it = productRepository.allProducts().iterator();
        while (it.hasNext()){
            Product u=it.next();
            if(u.getId()==id){
                return true;
            }
        }
        return false;
    }
    public int lastID(){
        return productRepository.lastID();
    }
    public Product getProduct(int id){
        if(containsID(id)){
            return productRepository.getProduct(id);
        }
        return null;
    }
    public ElectronicProduct getElectronicProduct(int id){
        ElectronicProduct electronicProduct = (ElectronicProduct) getProduct(id);

        {
            if(electronicProduct.getCategory().equals("Electronics")){
                return electronicProduct;
            }
        }
        return null;
    }
    public WearableProduct getWearableProduct(int id){
        WearableProduct wearableProduct = (WearableProduct) getProduct(id);
        {
            if(wearableProduct.getCategory().equals("Wearable")){
                return wearableProduct;
            }
        }
        return null;
    }
    public boolean duplicate(Product product){
        int count=1;
        Iterator<Product> it = productRepository.allProducts().iterator();
        while (it.hasNext()){
            Product u=it.next();
            if(product.getId()==u.getId() || product.getName().equals(u.getName())){
                count++;
            }
        }
        if(count<2){
            return false;
        }
        return true;
    }
    public int nbOfDuplicates(Product product){
        int count=1;
        Iterator<Product> it = productRepository.allProducts().iterator();
        while (it.hasNext()){
            Product u=it.next();
            if(product.getId()==u.getId() || product.getName().equals(u.getName())){
                count++;
            }
        }
        return count;
    }
    public boolean removeDuplicates(){
        int count=0;
        Iterator<Product> it = productRepository.allProducts().iterator();
        while (it.hasNext()){
            Product b = it.next();
            if(nbOfDuplicates(b)>=3){
                count++;
                productRepository.delete(b.getId());
            }
        }
        if(count>=1){
            System.out.println("Total duplicates removed: "+count);
            return true;
        }
        return false;
    }


}
