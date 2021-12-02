package ro.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product implements Comparable<Product>{
    private int id;
    private String name;
    private int price;
    private String category;
    private  int stock;

    public Product(String name, int price, String category, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    @Override
    public int compareTo(Product product){
        if(product.id==this.id){
            return 0;
        }else if(product.id<this.id){
            return 1;
        }
        return -1;
    }
    @Override
    public boolean equals(Object obj){
        Product product = (Product) obj;
        return product.getId()==this.getId();
    }
    @Override
    public String toString(){
        return name+","+price+","+category+","+stock;
    }
}
