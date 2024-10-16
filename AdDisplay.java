import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class AdDisplay implements ActionListener {
    private Timer timer; 
    private JLabel label;
    private JLabel label2;
    private List<Message> messages;
    private Random random;

    public AdDisplay() {
        random = new Random();
        messages = createMessages();
    }

    public void run() {
        JFrame frame = new JFrame("Reklamtavla");
        frame.setSize(500, 300); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Reklamrad 1", JLabel.CENTER);
        label.setForeground(Color.CYAN);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setBounds(10, 0, 480, 100);
        label.setFont(new Font("Monospaced", Font.PLAIN, 18));
        frame.add(label);

        label2 = new JLabel("Reklamrad 2", JLabel.CENTER);
        label2.setForeground(Color.CYAN);
        label2.setBackground(Color.BLACK);
        label2.setOpaque(true);
        label2.setBounds(10, 100, 480, 100);
        label2.setFont(new Font("Monospaced", Font.PLAIN, 18));
        frame.add(label2);

        timer = new Timer(10 * 1000, this);
        timer.setInitialDelay(0);
        timer.start();                       

        frame.setLayout(null);
        frame.setVisible(true);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            Message message = getRandomMessage();
            label.setText(message.getCustomer());
            label2.setText(message.getText());
        }
    }

    private List<Message> createMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hederlige Harrys Bilar", "En bra bilaffär", 5000));
        messages.add(new Message("Farmor Ankas Pajer AB", "Skynda innan Mårten äter allt!", 2000));
        messages.add(new Message("Svarte Petters Svartbyggen", "Bygga svart? Ring Petter", 1500));
        messages.add(new Message("Långbens Detektivbyrå", "Mysterier? Ring Långben", 4000));
        return messages;
    }

    private Message getRandomMessage() {
        int totalWeight = messages.stream().mapToInt(Message::getWeight).sum();
        int randomValue = random.nextInt(totalWeight);
        int currentWeight = 0;
        for (Message message : messages) {
            currentWeight += message.getWeight();
            if (randomValue < currentWeight) {
                return message;
            }
        }
        return messages.get(0);
    }

    public static void main(String[] args) {
        AdDisplay display = new AdDisplay();
        display.run();
    }
}
