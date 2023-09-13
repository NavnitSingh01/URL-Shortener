package Navnit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class URLShortenerApp extends JFrame {
    private JTextField originalURLField;
    private JButton shortenButton;
    private JTextArea shortenedURLArea;
    private Map<String, String> urlMap;

    public URLShortenerApp() {
        setTitle("URL Shortener");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240)); // Set a light background color

        urlMap = new HashMap<>();

        originalURLField = new JTextField();
        shortenButton = new JButton("Shorten");
        shortenedURLArea = new JTextArea();

        originalURLField.setFont(new Font("Arial", Font.PLAIN, 16));
        shortenButton.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(originalURLField, BorderLayout.CENTER);
        inputPanel.add(shortenButton, BorderLayout.EAST);
        inputPanel.setBackground(new Color(220, 220, 220)); // Set a slightly darker background color
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to the input panel

        add(inputPanel, BorderLayout.NORTH);

        shortenedURLArea.setFont(new Font("Arial", Font.PLAIN, 14));
        shortenedURLArea.setEditable(false);
        shortenedURLArea.setLineWrap(true);

        add(new JScrollPane(shortenedURLArea), BorderLayout.CENTER);

        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shortenURL();
            }
        });
    }

    private void shortenURL() {
        String originalURL = originalURLField.getText().trim();

        if (originalURL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid URL.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the URL already has a corresponding short URL
        if (urlMap.containsKey(originalURL)) {
            shortenedURLArea.setText(urlMap.get(originalURL));
        } else {
            // Generate a new short URL
            String shortenedURL = generateShortURL();
            urlMap.put(originalURL, shortenedURL);
            shortenedURLArea.setText(shortenedURL);
        }
    }

    private String generateShortURL() {
        // A basic random token generation
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortURL = new StringBuilder("https://short.url/");
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            char randomChar = characters.charAt(rand.nextInt(characters.length()));
            shortURL.append(randomChar);
        }

        return shortURL.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                URLShortenerApp app = new URLShortenerApp();
                app.setVisible(true);
            }
        });
    }
}

