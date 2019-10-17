

public class PurchaseModel {
    public int mPurchaseID, mCustomerID;
    public int mProductID, mQuantity;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mPurchaseID).append(",");
        sb.append("\"").append(mCustomerID).append("\"").append(",");
        sb.append(mProductID).append(",");
        sb.append("\"").append(mQuantity).append("\"").append(")");
        return sb.toString();
    }
}