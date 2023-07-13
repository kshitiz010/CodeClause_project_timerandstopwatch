import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerAndStopwatch extends JFrame {
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;

    public TimerAndStopwatch() {
        setTitle("Timer and Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(timerLabel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime = System.currentTimeMillis();
                timer = new Timer(1000, new TimerListener());
                timer.start();
            }
        });
        add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        add(stopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timerLabel.setText("00:00:00");
            }
        });
        add(resetButton);
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            int hours = (int) (elapsedTime / 3600000);
            int minutes = (int) ((elapsedTime / 60000) % 60);
            int seconds = (int) ((elapsedTime / 1000) % 60);
            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            timerLabel.setText(time);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerAndStopwatch timerAndStopwatch = new TimerAndStopwatch();
                timerAndStopwatch.setVisible(true);
            }
        });
    }
}
