package ro.shop.model.product;

import ro.shop.model.Product;

public class WearableProduct extends Product {
    private String wearableType;
    private String material;

    public WearableProduct(int id, String name, int price, int stock, String wearableType, String material) {
        super(id, name, price, "Wearable", stock);
        this.wearableType = wearableType;
        this.material = material;
    }

    public WearableProduct(String name, int price, int stock, String wearableType, String material) {
        super(name, price, "Wearable", stock);
        this.wearableType = wearableType;
        this.material = material;
    }

    public String getWearableType() {
        return wearableType;
    }

    public void setWearableType(String wearableType) {
        this.wearableType = wearableType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int compareTo(Product wearableProduct){
        if(wearableProduct.getId()==this.getId()){
            return 0;
        }else if(wearableProduct.getId()<this.getId()){
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj){
        WearableProduct wearableProduct = (WearableProduct) obj;
        return wearableProduct.getId()==this.getId();
    }
    @Override
    public String toString(){
        String prod = super.toString();
        return prod+","+wearableType+","+material;
    }

}
