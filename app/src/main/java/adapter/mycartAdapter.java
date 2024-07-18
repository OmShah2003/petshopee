package adapter;

public class mycartAdapter {
    String documentId;
    private String currentDate;
    private String currentTime;
    private String product;
    private int amount;
    private int totalquantity;

    public mycartAdapter() {}

    public mycartAdapter(String documentId, String currentDate, String currentTime, String product, int amount, int totalquantity) {
        this.documentId = documentId;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.product = product;
        this.amount = amount;
        this.totalquantity = totalquantity;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(int totalquantity) {
        this.totalquantity = totalquantity;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
