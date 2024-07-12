package utils;

import model.Product;

public class TrendProduct {
    private Product product;
    private int quantityFirstMonth;
    private int quantitySecondMonth;
    private int quantityThirdMonth;
    private String trend;

    public TrendProduct(Product product, int quantityFirstMonth, int quantitySecondMonth, int quantityThirdMonth) {
        this.product = product;
        this.quantityFirstMonth = quantityFirstMonth;
        this.quantitySecondMonth = quantitySecondMonth;
        this.quantityThirdMonth = quantityThirdMonth;
        if(quantityFirstMonth >= quantitySecondMonth && quantitySecondMonth >= quantityThirdMonth){
            this.trend = "Đi Xuống";
        } else if (quantityThirdMonth >= quantitySecondMonth && quantitySecondMonth >= quantityFirstMonth) {
            this.trend = "Đi Lên";
        }else{
            this.trend = "Đi Ngang";
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityFirstMonth() {
        return quantityFirstMonth;
    }

    public void setQuantityFirstMonth(int quantityFirstMonth) {
        this.quantityFirstMonth = quantityFirstMonth;
    }

    public int getQuantitySecondMonth() {
        return quantitySecondMonth;
    }

    public void setQuantitySecondMonth(int quantitySecondMonth) {
        this.quantitySecondMonth = quantitySecondMonth;
    }

    public int getQuantityThirdMonth() {
        return quantityThirdMonth;
    }

    public void setQuantityThirdMonth(int quantityThirdMonth) {
        this.quantityThirdMonth = quantityThirdMonth;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}
