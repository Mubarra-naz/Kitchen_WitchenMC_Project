package pk.edu.pucit.kitchen_witchenmc_project.model;

public class order {
    int orderId;
    int orderPrice;
    String orderStatus;

    public order(int orderId, int orderPrice, String orderStatus) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
