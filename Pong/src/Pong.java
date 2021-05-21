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

    private int windowWidth = 800;
    private int windowHeight = 600;
    private Pelota pelota;
    private Paleta paleta;
    private Paleta2 paleta2;

    private int key=0;
    private int key2=0;
    private long goal;
    private long tiempoDemora=8;

    private int jugador1;
    private int jugador2;


    public static void main(String[] args) throws IOException {
        new Pong();
    }

    public Pong() throws IOException {
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
            sleep();
        }

    }

    private void inicializoObjetos() {
        //velocidad pelota
        pelota = new Pelota(windowWidth/2, windowHeight/2, 2, -2);
        //altura de la paleta
        paleta = new Paleta(windowHeight/2, 80);
        paleta2 = new Paleta2(windowHeight/2, 80);
    }

    private void pelota() throws IOException {

        pelota.x = pelota.x + pelota.veloX;
        pelota.y = pelota.y + pelota.veloY;

        chequearColision();

        if(pelota.x <= 0){
            pelota.x = windowWidth/2;
            pelota.y = windowHeight/2;
            pelota.veloX = -pelota.veloX;
            jugador2++;
        }

        if(pelota.x >= windowWidth-40){
            pelota.x = windowWidth/2;
            pelota.y = windowHeight/2;
            pelota.veloX = -pelota.veloX;
            jugador1++;
        }

        if(pelota.y <= 20 || pelota.y >= (windowHeight - 40))
            pelota.veloY = -pelota.veloY;

        dibujoPantalla();
    }

    private void chequearColision(){
        if ( (pelota.x <= 75 && pelota.x >= 60) && pelota.y > paleta.y && pelota.y < paleta.y + paleta.alto)
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

    private void dibujoPantalla() throws IOException {

        Image fons = ImageIO.read(new File("Pong\\Fondo.jpg"));
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try {
            g = bf.getDrawGraphics();

            g.setColor(Color.BLACK);

            g.fillRect(0,0, windowWidth, windowHeight);

            g.drawImage(fons,0,0,null);


            muestroPuntos(g);
<<<<<<< Updated upstream
            dibujoPelota(g);
            dibujoPaletas(g);
            dibujoPaletas2(g);

        } finally {
            g.dispose();
        }
        bf.show();

        Toolkit.getDefaultToolkit().sync();
    }

    private void dibujoPelota(Graphics g) throws IOException {

        Image bola = ImageIO.read(new File("Pong\\Bola.jpg"));

        g.fillOval(pelota.x, pelota.y, 100, 100);
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
        g.fillRect(75, paleta.y, 15, paleta.alto);
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
        g.fillRect(695, paleta2.y, 15, paleta2.alto);
    }

    private void muestroPuntos(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador 1: " + jugador1, 95, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador 2: " + jugador2, 590, 50);
    }

    private void sleep(){
        goal = ( System.currentTimeMillis() + tiempoDemora );
        while(System.currentTimeMillis() < goal) {

        }
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
=======
            dibujoPelota(g);
>>>>>>> Stashed changes
