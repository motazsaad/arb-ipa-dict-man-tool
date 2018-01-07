package io.motaz.ui;

import io.motaz.IPADictUtil;
import kacst.lib.MyKACSTLib;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainScreen {
    private JPanel panelMain;
    private JPanel panelControl;
    private JPanel panelLog;
    private JTextArea logTextArea;
    private JButton BrowsEntriesFilebutton;
    private JLabel labelEntryFile;
    private JLabel labelDictFile;
    private JButton browsDictionaryFileButton;
    private JLabel labelOutDictFile;
    private JButton addEntriesButton;
    private JButton outputDictionaryFileButton;
    private JTextField textFieldEntryFile;
    private JTextField textFieldDictFile;
    private JTextField textFieldOutDictFile;

    // my variables
    private File entryFile, inDictFile, outDictFile;


    public MainScreen() {// constructor
        // init lib and configs
        MyKACSTLib.loadConfig();

        // select entry file button
        BrowsEntriesFilebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Create a file chooser
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panelMain);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    entryFile = fc.getSelectedFile();
                    textFieldEntryFile.setText(entryFile.getName());
                } else {
                    textFieldEntryFile.setText("no file selected");
                }
            }
        });

        // select input dict button
        browsDictionaryFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Create a file chooser
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panelMain);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    inDictFile = fc.getSelectedFile();
                    textFieldDictFile.setText(inDictFile.getName());
                } else {
                    textFieldDictFile.setText("no file selected");
                }

            }
        });

        // select output dictionary file button
        outputDictionaryFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Create a file chooser
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panelMain);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    outDictFile = fc.getSelectedFile();
                    textFieldOutDictFile.setText(outDictFile.getName());
                } else {
                    textFieldOutDictFile.setText("no file selected");
                }
            }
        });

        // add entries button
        addEntriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (entryFile != null && outDictFile != null) {
                    String log = IPADictUtil.updateDict(entryFile, inDictFile, outDictFile, "utf-8");
                    logTextArea.append(log);
                } else
                    JOptionPane.showMessageDialog(null, "Please select files");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Arabic IPA dictionary manipulation tool");
        frame.setContentPane(new MainScreen().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
