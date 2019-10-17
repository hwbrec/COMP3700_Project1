

public class CustomerModel {
    public int mCustomerID, mPhone;
    public String mName, mPaymentInfo;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mCustomerID).append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append(mPhone).append(",");
        sb.append("\"").append(mPaymentInfo).append("\"").append(")");
        return sb.toString();
    }
}