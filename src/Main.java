import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.time.Duration;
import java.time.Instant;


public class Main extends JFrame {
    private Stage stage;
    private Canvas canvas;
    
    public static void main(String[] args) {
        Main window = new Main();
        window.run();
    }

<<<<<<< Updated upstream
    class Canvas extends JPanel implements MouseListener {
      Stage stage;
      public Canvas() {
        setPreferredSize(new Dimension(1024, 720));
        this.addMouseListener(this);
        stage = StageReader.readStage("data/stage1.rvb");
      }

      @Override
      public void paint(Graphics g) {
        stage.paint(g, getMousePosition());
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        stage.mouseClicked(e.getX(), e.getY());
      }

      @Override
      public void mousePressed(MouseEvent e) {}

      @Override
      public void mouseReleased(MouseEvent e) {}

      @Override
      public void mouseEntered(MouseEvent e) {}

      @Override
      public void mouseExited(MouseEvent e) {}
=======
    class Canvas extends JPanel {
        public Canvas() {
            setPreferredSize(new Dimension(720, 720));
            stage = new Stage();
            
            // Add mouse listener
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (stage.getGameState() == GameState.GAME_OVER_WIN) {
                        // Check if click is on restart button
                        if (isPointInRestartButton(e.getPoint())) {
                            stage = new Stage(); // Restart the game
                        }
                    } else {
                        stage.handleClick(e.getPoint());
                    }
                }
            });
        }
        
        private boolean isPointInRestartButton(Point p) {
            return p.x >= 300 && p.x <= 420 && p.y >= 400 && p.y <= 440;
        }

        @Override
        public void paint(Graphics g) {
            stage.update();
            stage.paint(g, getMousePosition());
            
            // Draw restart button if game over
            if (stage.getGameState() == GameState.GAME_OVER_WIN) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(300, 400, 120, 40);
                g.setColor(Color.BLACK);
                g.drawRect(300, 400, 120, 40);
                g.drawString("RESTART", 325, 425);
            }
        }
>>>>>>> Stashed changes
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
        this.setTitle("Click the Moving Characters!");
    }

    public void run() {
<<<<<<< Updated upstream
      while(true) {
        // Re-draw the screen 50 times per second
        Instant startTime = Instant.now();
        repaint();
        Instant endTime = Instant.now();
        long howLong = Duration.between(startTime, endTime).toMillis();
        try {
          Thread.sleep(20l - howLong);
        } catch(InterruptedException e) {
          System.out.println("thread was interrupted, nothing to worry about!");
        } catch(IllegalArgumentException e) {
          System.out.println("application can't keep up with framerate");
        }
      }
=======
        while (true) {
            repaint();
            try { Thread.sleep(16); } catch (InterruptedException ignored) {}
        }
>>>>>>> Stashed changes
    }
}