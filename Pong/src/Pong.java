import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


///
class Paleta {
    public int y;
    public int alto;


    public Paleta(int y, int alto) {
        this.y = y;
        this.alto = alto;

    }
}
class Paleta2 {
    public int y;
    public int alto;


    public Paleta2(int y, int alto) {
        this.y = y;
        this.alto = alto;

    }
}

class Pelota {
    public int x;
    public int y;
    public int veloX;
    public int veloY;

    public Pelota(int x, int y, int veloX, int veloY) {
        this.x = x;
        this.y = y;
        this.veloX = veloX;
        this.veloY = veloY;
    }
}


public class Pong extends JFrame implements KeyListener {

    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private Pelota pelota;
    private Paleta paleta;
    private Paleta2 paleta2;

    private int key=0;
    private int key2=0;
    private final long tiempoDemora=8;

    private int jugador1;
    private int jugador2;


    public static void main(String[] args) throws IOException, InterruptedException {
        new Pong();
    }

    public Pong() throws IOException, InterruptedException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);

        this.createBufferStrategy(2);

        this.addKeyListener(this);

        inicializoObjetos();

        while(true) {
            pelota();
        }

    }



    private void inicializoObjetos() {
        //velocidad pelota
        pelota = new Pelota(windowWidth/2, windowHeight/2, 2, -2);
        //altura de la paleta
        paleta = new Paleta(windowHeight/2, 80);
        paleta2 = new Paleta2(windowHeight/2, 80);
    }

    private void pelota() throws IOException, InterruptedException {

        pelota.x = pelota.x + pelota.veloX;
        pelota.y = pelota.y + pelota.veloY;

        chequearColision();

        if(pelota.y <= 20 || pelota.y >= (windowHeight - 40))
            pelota.veloY = -pelota.veloY;

        dibujoPantalla();
    }

    private void chequearColision(){
        if ( (pelota.x <= 60 && pelota.x >= 55) && pelota.y > paleta.y && pelota.y < paleta.y + paleta.alto)
        {
            if (pelota.veloX < 0)

                pelota.veloX = -pelota.veloX;
        }

        if ( (pelota.x >= 695 && pelota.x <= 710) && pelota.y > paleta2.y && pelota.y < paleta2.y + paleta2.alto)
        {
            if (pelota.veloX > 0)

                pelota.veloX = -pelota.veloX;
        }
    }

    private void dibujoPantalla() throws IOException, InterruptedException {

        Image fons = ImageIO.read(new File("Pong\\Fondo.jpg"));
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try {
            g = bf.getDrawGraphics();

            g.fillRect(0,0, windowWidth, windowHeight);

            g.drawImage(fons,0,0,null);

            PuntJugador2(g);
            PuntJugador1(g);
            muestroPuntos(g);
            dibujoPelota(g);
            dibujoPaletas(g);
            dibujoPaletas2(g);

        } finally {
            if (g != null) {
                g.dispose();
            }
        }
        bf.show();

        Toolkit.getDefaultToolkit().sync();
    }

    private void dibujoPelota(Graphics g) throws IOException {

        Image bola = ImageIO.read(new File("Pong\\Tierra-plana.png"));

        g.fillOval(pelota.x, pelota.y, 40, 40);
        g.drawImage(bola,pelota.x,pelota.y,null);
    }

    private void dibujoPaletas(Graphics g) {

        switch (key){
            case KeyEvent.VK_W:
                if (paleta.y>23)
                    paleta.y=paleta.y-6;
                break;
            case KeyEvent.VK_S:
                if (paleta.y<windowHeight-78)
                    paleta.y=paleta.y+6;
                break;
            case KeyEvent.VK_E:
                System.exit(0);

        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(45, paleta.y, 15, paleta.alto);
    }
    private void dibujoPaletas2(Graphics g) {

        switch (key2){
            case KeyEvent.VK_UP:
                if (paleta2.y>23)
                    paleta2.y=paleta2.y-6;
                break;
            case KeyEvent.VK_DOWN:
                if (paleta2.y<windowHeight-78)
                    paleta2.y=paleta2.y+6;
                break;
            case KeyEvent.VK_E:
                System.exit(0);

        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(740, paleta2.y, 15, paleta2.alto);
    }

    private void muestroPuntos(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador 1: " + jugador1, 95, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador 2: " + jugador2, 590, 50);
    }
    private void PuntJugador2(Graphics g) throws InterruptedException {
        if(pelota.x <= 0){
            pelota.x = windowWidth/2;
            pelota.y = windowHeight/2;
            pelota.veloX = -pelota.veloX;
            jugador2++;
            sleep();
        }
        if(jugador2 == 1){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Guanya el jugador 2! ", windowWidth / 2, windowHeight / 2);
        }
    }
    private void PuntJugador1(Graphics g) throws InterruptedException {

        if(pelota.x >= windowWidth-40){
            pelota.x = windowWidth/2;
            pelota.y = windowHeight/2;
            pelota.veloX = -pelota.veloX;
            jugador1++;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Gol de jugador 1! ", windowWidth/2, windowHeight/2);
            sleep();
        }
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Override
    public void keyPressed(KeyEvent e){
        key=e.getKeyCode();
        key2=e.getKeyCode();
    }


    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }
}

