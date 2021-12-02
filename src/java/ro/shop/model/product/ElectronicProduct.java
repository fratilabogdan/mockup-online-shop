package ro.shop.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import ro.shop.model.Product;



public class ElectronicProduct extends Product {
    public boolean isDangerous;

    public ElectronicProduct(int id, String name, int price, int stock, boolean isDangerous) {
        super(id, name, price, "Electronics", stock);
        this.isDangerous = isDangerous;
    }

    public ElectronicProduct(String name, int price, int stock, boolean isDangerous) {
        super(name, price, "Electronics", stock);
        this.isDangerous=isDangerous;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    @Override
    public int compareTo(Product electronicProduct){
        if(electronicProduct.getId()==this.getId()){
            return 0;
        }else if(electronicProduct.getId()<this.getId()){
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj){
        ElectronicProduct electronicProduct = (ElectronicProduct) obj;
        return electronicProduct.getId()==this.getId();
    }

    @Override
    public String toString(){
        String prod = super.toString();
        return prod+","+this.isDangerous;
    }


}
