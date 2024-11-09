/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package packagenou.projectegalan;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Intents;
import models.Review;
import models.Usuari;
import recursos.DataAcces;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class FrameUsuaris extends javax.swing.JFrame {

    private EmbeddedMediaPlayerComponent mediaPlayer;
    private DataAcces dataAcces;
    private ArrayList<Usuari> usuaris;
    private ArrayList<Intents> intentsUsuari;
    private static final String VIDEO_FOLDER_PATH = "C:\\Users\\Albert\\Documents\\NetBeansProjects\\ActivitatsDVI\\Galan-Albert-DVI\\ProjecteGalan\\src\\main\\java\\recursos\\videos\\";

    public FrameUsuaris() {
        initComponents();
        setResizable(false);
        dataAcces = new DataAcces();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                initializeMediaPlayer();
                loadUserList();
                setupUserSelectionListener();
            }
        });
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
        intentsUsuari = dataAcces.getIntentsByUserId(userId);
        DefaultTableModel intentTableModel = new DefaultTableModel(new String[]{"ID Intent", "Exercici", "Data Inici"}, 0);

        for (Intents intent : intentsUsuari) {
            intentTableModel.addRow(new Object[]{intent.getId(), intent.getExerciciNom(), intent.getInici()});
        }

        intentTable.setModel(intentTableModel);
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

        pnlVideoPlayer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        intentTable = new javax.swing.JTable();
        btnPauseResumeVideoActionPerformed = new javax.swing.JButton();
        jCamptexte = new javax.swing.JTextField();
        jbtnInsertUpdate = new javax.swing.JButton();
        jbtnDeleteIntent = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jPuntuacio = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 290, 350));

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
        getContentPane().add(btnPauseResumeVideoActionPerformed, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 170, 50));

        jCamptexte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCamptexteActionPerformed(evt);
            }
        });
        getContentPane().add(jCamptexte, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 560, 210, 80));

        jbtnInsertUpdate.setText("BOTON");
        getContentPane().add(jbtnInsertUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 140, -1));

        jbtnDeleteIntent.setText("Borrar Intent");
        getContentPane().add(jbtnDeleteIntent, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 570, 150, -1));
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 250, 50));
        getContentPane().add(jPuntuacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 30, 20));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPauseResumeVideoActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseResumeVideoActionPerformedActionPerformed
     if (mediaPlayer.mediaPlayer().status().isPlaying()) {
            mediaPlayer.mediaPlayer().controls().pause();
        } else {
            mediaPlayer.mediaPlayer().controls().play();
    }     
    }//GEN-LAST:event_btnPauseResumeVideoActionPerformedActionPerformed

    private void jCamptexteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCamptexteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCamptexteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameUsuaris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPauseResumeVideoActionPerformed;
    private javax.swing.JTable intentTable;
    private javax.swing.JTextField jCamptexte;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel jPuntuacio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton jbtnDeleteIntent;
    private javax.swing.JButton jbtnInsertUpdate;
    private javax.swing.JPanel pnlVideoPlayer;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
