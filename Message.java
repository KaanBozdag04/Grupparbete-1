public class Message {
    private String customer;
    private String text;
    private int weight;

    public Message(String customer, String text, int weight) {
        this.customer = customer;
        this.text = text;
        this.weight = weight;
    }

    public String getCustomer() {
        return customer;
    }

    public String getText() {
        return text;
    }

    public int getWeight() {
        return weight;
    }
}

