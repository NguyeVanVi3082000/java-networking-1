package com.company.book1._4_URLs_And_URIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;

public class DialogAuthenticator extends Authenticator {

    PasswordAuthentication response = null;
    private final JDialog passwordDialog;
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final JLabel mainLabel
            = new JLabel("Please enter username and password: ");

    public DialogAuthenticator() {
        this("", new JFrame());
    }

    public DialogAuthenticator(String username, JFrame parent) {
        this.passwordDialog = new JDialog(parent, true);
        Container pane = passwordDialog.getContentPane();
        pane.setLayout(new GridLayout(4, 1));
        JLabel userLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        pane.add(mainLabel);
        JPanel p2 = new JPanel();
        p2.add(userLabel);
        p2.add(usernameField);
        usernameField.setText(username);
        pane.add(p2);
        JPanel p3 = new JPanel();
        p3.add(passwordLabel);
        p3.add(passwordField);
        pane.add(p3);
        JPanel p4 = new JPanel();
        p4.add(okButton);
        p4.add(cancelButton);
        pane.add(p4);
        passwordDialog.pack();
        ActionListener al = e -> {
            passwordDialog.setVisible(false);
            // The password is returned as an array of
            // chars for security reasons.
            char[] password = passwordField.getPassword();
            String username1 = usernameField.getText();
            // Erase the password in case this is used again.
            passwordField.setText("");
            response = new PasswordAuthentication(username1, password);

        };
        okButton.addActionListener(al);
        usernameField.addActionListener(al);
        passwordField.addActionListener(al);
        cancelButton.addActionListener(e -> {
            passwordDialog.setVisible(false);
            // Erase the password in case this is used again.
            passwordField.setText("");
            response = null;
        });
    }

    private void show() {
        String prompt = this.getRequestingPrompt();
        if (prompt == null) {
            String site = this.getRequestingSite().getHostName();
            String protocol = this.getRequestingProtocol();
            int port = this.getRequestingPort();
            if (site != null & protocol != null) {
                prompt = protocol+"://"+site;
                if (port > 0) prompt += ":"+port;
            } else {
                prompt = "";
            }
        }
        mainLabel.setText("Please enter username and password for "
                +prompt+": ");
        passwordDialog.pack();
        passwordDialog.setVisible(true);
    }


    @Override
    public PasswordAuthentication requestPasswordAuthenticationInstance(String host, InetAddress addr, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType) {
        return super.requestPasswordAuthenticationInstance(host, addr, port, protocol, prompt, scheme, url, reqType);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        this.show();
        return this.response;
    }
}
