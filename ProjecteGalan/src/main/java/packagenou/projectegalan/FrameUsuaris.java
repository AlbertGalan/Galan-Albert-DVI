/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package packagenou.projectegalan;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Intents;
import models.Review;
import models.Usuari;
import recursos.DataAcces;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class FrameUsuaris extends javax.swing.JFrame {

    private NouFramePrincipal pare;
    private EmbeddedMediaPlayerComponent mediaPlayer;
    private DataAcces dataAcces;
    private ArrayList<Usuari> usuaris;
    private ArrayList<Intents> intentsUsuari;
    private static final String VIDEO_FOLDER_PATH = "C:\\Users\\Albert\\Documents\\NetBeansProjects\\ActivitatsDVI\\Galan-Albert-DVI\\ProjecteGalan\\src\\main\\java\\recursos\\videos\\";

    public FrameUsuaris(NouFramePrincipal pare) {
        initComponents();
        setResizable(false);
        dataAcces = new DataAcces();
        this.pare = pare;

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                initializeMediaPlayer();
                loadUserList();
                setupUserSelectionListener();
            }
        });
    }

    private void cargarTable() {
        if (intentTable.getRowCount() > 0) {
            intentTable.setRowSelectionInterval(0, 0);
        }
        commentControl();
    }

    private void initializeMediaPlayer() {
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        pnlVideoPlayer.setLayout(new BorderLayout());
        pnlVideoPlayer.add(mediaPlayer, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void loadUserList() {
        usuaris = dataAcces.getUsuaris();
        DefaultTableModel userTableModel = new DefaultTableModel(new String[]{"ID Usuari", "Nom", "Email"}, 0);

        for (Usuari usuari : usuaris) {
            userTableModel.addRow(new Object[]{usuari.getId(), usuari.getNom(), usuari.getEmail()});
        }

        userTable.setModel(userTableModel);
    }

private void loadUserIntents(int userId) {
    // Obtenim tots els intents amb valoració i filtrem per usuari
    ArrayList<Intents> allIntents = dataAcces.getPendingIntentsValoracio();
    intentsUsuari = new ArrayList<>();

    for (Intents intent : allIntents) {
        if (intent.getIdUsuari() == userId) {
            intentsUsuari.add(intent);
        }
    }

    DefaultTableModel intentTableModel = new DefaultTableModel(new String[]{"ID Intent", "Exercici", "Data Inici", "Estat"}, 0); 

    for (Intents intent : intentsUsuari) {
        intentTableModel.addRow(new Object[]{intent.getId(), intent.getExercici().getNomExercici(), intent.getInici(), intent.getStatus()});
    }

    intentTable.setModel(intentTableModel);
    cargarTable();
}


    private void setupUserSelectionListener() {
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && userTable.getSelectedRow() != -1) {
                    int selectedRow = userTable.getSelectedRow();
                    int userId = (int) userTable.getValueAt(selectedRow, 0);
                    loadUserIntents(userId);
                    setupIntentSelectionListener();
                }
            }
        });
    }

    private void setupIntentSelectionListener() {
        intentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && intentTable.getSelectedRow() != -1) {
                    int selectedRow = intentTable.getSelectedRow();
                    Intents selectedIntent = intentsUsuari.get(selectedRow);
                    playVideo(selectedIntent.getVideo());
                    commentControl();
                }
            }
        });
    }

    private void playVideo(String videoFileName) {
        if (mediaPlayer.mediaPlayer().status().isPlaying()) {
            mediaPlayer.mediaPlayer().controls().stop();
        }

        String videoPath = VIDEO_FOLDER_PATH + videoFileName;
        File videoFile = new File(videoPath);

        if (videoFile.exists()) {
            mediaPlayer.mediaPlayer().media().startPaused(videoFile.getAbsolutePath());
        } else {
            System.out.println("El fitxer de vídeo no existeix: " + videoPath);
        }
    }

    private void commentControl() {
        int selectedRow = intentTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) intentTable.getValueAt(selectedRow, 0); // Obtenim el ID de l'intent
            Review review = dataAcces.getAttemptReview(id); // Recuperem la review de la base de dades
            if (review != null) {
                if (review.getReview() != 0) { // Si hi ha una ressenya existent
                    jCamptexte.setText(review.getComent());
                    jSlider1.setValue(review.getReview());
                    jbtnInsertUpdate.setText("Actualitza Review");
                } else {
                    jCamptexte.setText("");
                    jSlider1.setValue(3); // Valor per defecte de la valoració
                    jbtnInsertUpdate.setText("Insereix Review");
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlVideoPlayer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        intentTable = new javax.swing.JTable();
        btnPauseResumeVideoActionPerformed = new javax.swing.JButton();
        jbtnInsertUpdate = new javax.swing.JButton();
        jbtnDeleteIntent = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jPuntuacio = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jCamptexte = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new ImageIcon("src\\main\\java\\recursos\\imatges\\MiddleLogoMesPetit.jpeg"));
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 570, 50, 50));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        String text = "<html>Llorenç: Has visto Ave,<br>" +
        "lo fuertes que vamos.<br>" +
        "<br>" +
        "Avelino: No hagas comedia Lorenzo,<br>" +
        "que te quedan 2 repeticiones.</html>";
        jLabel3.setText(text);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        pnlVideoPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder("Video Player - xxx.mp4"));
        pnlVideoPlayer.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlVideoPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 480, 360));

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(userTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 290, 350));

        intentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(intentTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 233, 350));

        btnPauseResumeVideoActionPerformed.setText("Pausar/Reproduir");
        btnPauseResumeVideoActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseResumeVideoActionPerformedActionPerformed(evt);
            }
        });
        getContentPane().add(btnPauseResumeVideoActionPerformed, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 170, 50));

        jbtnInsertUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInsertUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnInsertUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 150, 20));

        jbtnDeleteIntent.setText("Borrar Intent");
        jbtnDeleteIntent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteIntentActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnDeleteIntent, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 600, 150, -1));

        jSlider1.setMaximum(5);
        jSlider1.setMinimum(1);
        jSlider1.setValue(3);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 250, 50));

        jPuntuacio.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPuntuacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, 30, 20));

        jButton1.setText("<-SORTIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, -1, -1));

        jCamptexte.setColumns(20);
        jCamptexte.setRows(5);
        jScrollPane3.setViewportView(jCamptexte);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 380, 150));

        jLabel2.setIcon(new ImageIcon("src\\main\\java\\recursos\\imatges\\lorenzoiave.png"));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 190, 190));

        jLabel1.setIcon(new ImageIcon("src/main/java/recursos/imatges/Gondor.jpg")
        );
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-460, 0, 1570, 630));

        jMenu2.setText("File");

        jMenu1.setText("Exit and Help");

        jMenuItem3.setText("About");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenu2.add(jMenu1);

        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPauseResumeVideoActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseResumeVideoActionPerformedActionPerformed
        if (mediaPlayer.mediaPlayer().status().isPlaying()) {
            mediaPlayer.mediaPlayer().controls().pause();
        } else {
            mediaPlayer.mediaPlayer().controls().play();
        }
    }//GEN-LAST:event_btnPauseResumeVideoActionPerformedActionPerformed

    private void jbtnInsertUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInsertUpdateActionPerformed
        // TODO add your handling code here:
        int selectedRow = intentTable.getSelectedRow();

        if (selectedRow != -1) {
            int id = (int) intentTable.getValueAt(selectedRow, 0); // Assuming the ID is in the first column (index 0)
            Review review = new Review();
            review.setId(id);
            review.setComent(jCamptexte.getText());
            review.setIdReviewer(pare.getTrainer_id());
            review.setReview(jSlider1.getValue());
            if (dataAcces.getAttemptReview(id).getId() != 0) {
                review.setId(dataAcces.getAttemptReview(id).getId());
                dataAcces.updateReview(review);
            } else {
                dataAcces.insertReview(review);
            }
            int f = userTable.getSelectedRow();

            loadUserIntents((int) userTable.getValueAt(f, 0));
        }
    }//GEN-LAST:event_jbtnInsertUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jPuntuacio.setText(String.valueOf(jSlider1.getValue()));
// TODO add your handling code here:
    }//GEN-LAST:event_jSlider1StateChanged

    private void jbtnDeleteIntentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteIntentActionPerformed
        int selectedRow = intentTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) intentTable.getValueAt(selectedRow, 0); // Assuming the ID is in the first column (index 0)
            int response = JOptionPane.showConfirmDialog(this, "Estas segur que vols borrar aquest intent?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                dataAcces.deleteIntent(id);
                int f = userTable.getSelectedRow();
                loadUserIntents((int) userTable.getValueAt(f, 0));
            }

        }
    }//GEN-LAST:event_jbtnDeleteIntentActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String contingut = "El disseny de l'aplicació està basada amb \n" +
        "la saga de parodies de Panareta.\n" +
        "Algunes de les imatges vistes estàn generades amb IA:\n" +
        "He emprat: Pixlr, Ideogram, Stablediffusion\n" +
        "He emprat audios de la aplicació oficial de Panareta Films:\n" +
        "https://play.google.com/store/apps/details?id=com.mitir.panereta.pfilms \n" +
        "(Link només funcional a dispositius mòvils)";
        JOptionPane.showMessageDialog(this, contingut);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPauseResumeVideoActionPerformed;
    private javax.swing.JTable intentTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextArea jCamptexte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JLabel jPuntuacio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton jbtnDeleteIntent;
    private javax.swing.JButton jbtnInsertUpdate;
    private javax.swing.JPanel pnlVideoPlayer;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
