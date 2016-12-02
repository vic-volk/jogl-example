package ru.vlk.test.jogl;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.awt.GLCanvas;

public class JavaDia implements Runnable, KeyListener {
    private static Thread displayT = new Thread(new JavaDia());
    private static boolean bQuit = false;

    public static void main(String[] args) {
        displayT.start();
    }

    public void run() {
        Frame frame = new Frame("Jogl 3D Shape/Rotation");
        GLCanvas canvas = new GLCanvas();
        int size = frame.getExtendedState();
        
        canvas.addGLEventListener(new JavaRenderer());
        frame.add(canvas);
        frame.setUndecorated(true);
        size |= Frame.MAXIMIZED_BOTH;
        frame.setExtendedState(size);
        canvas.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                bQuit = true;
                System.exit(0);                
            }
        });

        frame.setVisible(true);
        canvas.requestFocus();
        while( !bQuit ) {
            canvas.display();
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            displayT = null;
       	 bQuit = true;
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}