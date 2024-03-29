package game;

import java.awt.Graphics2D;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;
import java.awt.Rectangle;

/**This class tests the player about video game culture from the 1980s. It also controls
 * Mario's movement and jumping abilities (despite being dumb downed due to the loss of code
 * during the hackathon.
 * 
 * @author Talha Masood
 */
public class TeenHack_Era2 extends Alpha implements KeyListener, ActionListener {
    
    private static int mariox;
    private static int marioy;
    private static int acceleration;
    private static int jumpCounter;   
    private int brickx;
    private int bricky;    
    private static int sanswer;
    private static int fanswer; 
    private static double up = 26;
    private static boolean right;
    private static boolean left;
    private static boolean down;   
    public static boolean jump;
    private boolean check;
    private static String SelectedAns;
    private static Image Brick;
    private static Image Block;
    private static Image mario;
    private Rectangle floor;
    private static Rectangle marioHitbox;
    private static Rectangle marioHitboxHead;
    private static Rectangle marioFeet;  
    private QuestionBank ques;
    
    public TeenHack_Era2() {
        mariox = 20;
        marioy = 660;
        acceleration = 0;
        jumpCounter = 0;   
        brickx = 0;
        bricky = 760;    
        sanswer = 1;
        fanswer = 0; 
        up = 26;
        right = false;
        left = false;
        down = false;   
        jump = false;
        check = false;
        SelectedAns = "";
        Brick = Toolkit.getDefaultToolkit().getImage("src\\game\\resources\\brick.png");
        Block = Toolkit.getDefaultToolkit().getImage("src\\game\\resources\\block.png");
        mario = Toolkit.getDefaultToolkit().getImage("src\\game\\resources\\mario.png");
        floor = new Rectangle(0, 660, 10000, 10000);
        marioHitbox = new Rectangle(mariox, marioy, 100, 100);
        marioHitboxHead = new Rectangle(mariox, marioy, 100, 30);
        marioFeet = new Rectangle(mariox, marioy + 70, 100, 30);  
        ques = new QuestionBank();
    }
    
    /**Runs main logic of class.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void standard(Graphics2D g2) {
        drawBackground(g2);
        if (right) {
            mariox += 4;
        }
        if (left) {
            mariox -= 4;
        }
        jumping();
        if (marioy >= 660) {
            jumpCounter = 0;
            down = false;
            marioy = 660;
            jump = false;
        }
        Font font = new Font("Serif", 40, 40);
        g2.setFont(font);
        ques = new QuestionBank();
        drawQuestion(g2);
        drawAnswer1(g2);
        drawAnswer2(g2);
        drawAnswer3(g2);
        drawAnswer4(g2);
        if (questionNumber <= 8) {
            g2.drawString(SelectedAns, 300, 300);
        }
        if (questionNumber == 9) {
            questionNumber++;
            era = 3;
        }
    }
    
    /**Draws the background and the environment on the screen.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void drawBackground(Graphics g2) {
        Color blue2 = new Color(60, 188, 252);
        g2.setColor(blue2);
        g2.fillRect(0, 0, 2000, 1080);
        bricky = 760;
        for (int i = 0; i < 30; i++) {
            g2.drawImage(Brick, brickx + (59 * i), bricky, 59, 62, this);
        }
        floor = new Rectangle(0, 660, 10000, 10000);
    }

    /*Looking back, these drawing methods could have been programmed better to avoid repetition.
    Needed to check for every question/answer choice in order to account for the difference in length
    of each question string (the longer the question/answer choice, the more it would have to be drawn to the left)*/
    
    //Removed most of the uneeded painting and actually used drawBackground method
    
    /**Draws the current question on screen.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void drawQuestion(Graphics2D g2) {
        drawBackground(g2); 
        g2.setColor(Color.BLACK);               
        g2.drawString("Question: " + (questionNumber+1), 5, 50);
        if (questionNumber == 5) {
            String question = ques.era2Questions(questionNumber - 5);
            g2.setColor(Color.BLACK);
            g2.drawString(question, 450, 150);
        }
        else if (questionNumber == 6) {         
            String question = ques.era2Questions(questionNumber - 5);
            g2.setColor(Color.BLACK);           
            g2.drawString(question, 150, 150);
        }
        else if (questionNumber == 7) {           
            String question = ques.era2Questions(questionNumber - 5);
            g2.setColor(Color.BLACK);
            g2.drawString(question, 200, 150);
        }
        else if (questionNumber == 8) {
            String question = ques.era2Questions(questionNumber - 5);
            g2.setColor(Color.BLACK);
            g2.drawString(question, 320, 150);
        }
        g2.drawImage(mario, mariox, marioy, 100, 100, this);
    }

    /**Draws the first answer choice for the current question on screen.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void drawAnswer1(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        Font font = new Font("Serif", 40, 40);
        g2.setFont(font);
        if (questionNumber == 5) {
            Rectangle rect1 = new Rectangle(20, 505, 90, 50);
            g2.drawImage(Block, 20, 505, 90, 50, this);
            String question = ques.era2Answer1(questionNumber - 5);
            g2.drawString(question, 25, 545);
            if (mariox >= -500 && mariox <= 120 && marioy <= 555) {
                SelectedAns = ques.era2Answer1(questionNumber - 5);
                marioy = 660;
            }

        }
        else if (questionNumber == 6) {
            Rectangle rect1 = new Rectangle(15, 505, 315, 45);
            g2.drawImage(Block, 15, 505, 315, 45, this);
            String question = ques.era2Answer1(questionNumber - 5);
            g2.drawString(question, 25, 545);
            if (mariox >= -5000 && mariox <= 360 && marioy <= 545) {
                SelectedAns = ques.era2Answer1(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 7) {
            Rectangle rect1 = new Rectangle(15, 505, 100, 40);
            g2.drawImage(Block, 15, 505, 100, 40, this);
            String question = ques.era2Answer1(questionNumber - 5);
            g2.drawString(question, 25, 545);
            if (mariox >= -5000 && mariox <= 180 && marioy <= 585) {
                SelectedAns = ques.era2Answer1(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 8) {
            Rectangle rect1 = new Rectangle(15, 505, 100, 40);
            g2.drawImage(Block, 15, 505, 120, 40, this);
            String question = ques.era2Answer1(questionNumber - 5);
            g2.drawString(question, 25, 545);
            if (mariox >= -50000 && mariox <= 185 && marioy <= 545) {
                SelectedAns = ques.era2Answer1(questionNumber - 5);
                marioy = 660;
            }
        }
    }

    /**Draws the second answer choice for the current question on screen.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void drawAnswer2(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        Font font = new Font("Serif", 40, 40);
        g2.setFont(font);
        if (questionNumber == 5) {
            Rectangle rect2 = new Rectangle(850, 505, 115, 40);
            g2.drawImage(Block, 850, 505, 115, 40, this);
            String question = ques.era2Answer2(questionNumber - 5);
            g2.drawString(question, 860, 535);
            if (mariox >= 800 && mariox <= 1015 && marioy <= 545) {
                SelectedAns = ques.era2Answer2(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 6) {
            Rectangle rect2 = new Rectangle(720, 505, 210, 45);
            g2.drawImage(Block, 720, 505, 210, 45, this);
            String question = ques.era2Answer2(questionNumber - 5);
            g2.drawString(question, 730, 545);
            if (mariox >= 680 && mariox <= 940 && marioy <= 585) {
                SelectedAns = ques.era2Answer2(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 7) {
            Rectangle rect2 = new Rectangle(620, 505, 215, 40);
            g2.drawImage(Block, 620, 505, 215, 40, this);
            String question = ques.era2Answer2(questionNumber - 5);
            g2.drawString(question, 630, 545);
            if (mariox >= 600 && mariox <= 850 && marioy <= 545) {
                SelectedAns = ques.era2Answer2(questionNumber - 5);
                System.out.println(SelectedAns);
                marioy = 660;
            }
        }
        else if (questionNumber == 8) {
            Rectangle rect2 = new Rectangle(520, 505, 185, 40);
            g2.drawImage(Block, 520, 505, 185, 40, this);
            String question = ques.era2Answer2(questionNumber - 5);
            g2.drawString(question, 530, 545);
            if (mariox >= 500 && mariox <= 800 && marioy <= 545) {
                SelectedAns = ques.era2Answer2(questionNumber - 5);
                marioy = 660;
            }
        }
    }

    /**Draws the third answer choice for the current question on screen.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void drawAnswer3(Graphics2D g2) {
        Font font = new Font("Serif", 40, 40);
        g2.setFont(font);
        g2.setColor(Color.BLACK);
        if (questionNumber == 5) {
            Rectangle rect3 = new Rectangle(490, 505, 105, 40);
            g2.drawImage(Block, 490, 505, 105, 40, this);

            String question = ques.era2Answer3(questionNumber - 5);
            g2.drawString(question, 500, 535);
            if (mariox >= 450 && mariox <= 595 && marioy <= 545) {
                SelectedAns = ques.era2Answer3(questionNumber - 5);
                marioy = 660;
            }

        }
        else if (questionNumber == 6) {
            Rectangle rect3 = new Rectangle(460, 505, 240, 40);
            g2.drawImage(Block, 460, 505, 240, 40, this);
            String question = ques.era2Answer3(questionNumber - 5);
            g2.drawString(question, 470, 540);
            if (mariox >= 360 && mariox <= 810 && marioy <= 540) {
                SelectedAns = ques.era2Answer3(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 7) {
            Rectangle rect3 = new Rectangle(310, 505, 200, 40);
            g2.drawImage(Block, 310, 505, 200, 40, this);
            String question = ques.era2Answer3(questionNumber - 5);
            g2.drawString(question, 320, 540);
            if (mariox >= 285 && mariox <= 600 && marioy <= 545) {
                SelectedAns = ques.era2Answer3(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 8) {
            Rectangle rect3 = new Rectangle(290, 505, 200, 40);
            g2.drawImage(Block, 290, 505, 200, 40, this);
            String question = ques.era2Answer3(questionNumber - 5);
            g2.drawString(question, 300, 540);
            if (mariox >= 250 && mariox <= 540 && marioy <= 545) {
                SelectedAns = ques.era2Answer3(questionNumber - 5);
                marioy = 660;
            }
        }
    }

    /**Draws the fourth answer choice for the current question on screen.
     * 
     * @param g2 {@linkplain java.awt.Graphics2D Graphics2D} object used for painting.
     */
    public void drawAnswer4(Graphics2D g2) {
        Font font = new Font("Serif", 40, 40);
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        if (questionNumber == 5) {
            Rectangle rect4 = new Rectangle(1120, 505, 105, 40);
            g2.drawImage(Block, 1120, 505, 105, 40, this);
            String question = ques.era2Answer4(questionNumber - 5);
            g2.drawString(question, 1120, 535);
            if (mariox >= 1060 && mariox <= 2000 && marioy <= 545) {
                SelectedAns = ques.era2Answer4(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 6) {
            Rectangle rect4 = new Rectangle(1000, 505, 205, 40);
            g2.drawImage(Block, 1000, 505, 205, 40, this);
            String question = ques.era2Answer4(questionNumber - 5);
            g2.drawString(question, 1000, 540);
            if (mariox >= 950 && mariox <= 2000 && marioy <= 545) {
                SelectedAns = ques.era2Answer4(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 7) {
            Rectangle rect4 = new Rectangle(1020, 505, 105, 40);
            g2.drawImage(Block, 1020, 505, 105, 40, this);
            String question = ques.era2Answer4(questionNumber - 5);
            g2.drawString(question, 1020, 540);
            if (mariox >= 1000 && mariox <= 2000 && marioy <= 545) {
                SelectedAns = ques.era2Answer4(questionNumber - 5);
                marioy = 660;
            }
        }
        else if (questionNumber == 8) {
            Rectangle rect4 = new Rectangle(950, 505, 355, 40);
            g2.drawImage(Block, 950, 505, 355, 40, this);
            String question = ques.era2Answer4(questionNumber - 5);
            g2.drawString(question, 970, 540);
            if (mariox >= 900 && mariox <= 2000 && marioy <= 545) {
                SelectedAns = ques.era2Answer4(questionNumber - 5);
                marioy = 660;
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == e.VK_RIGHT) {
            right = true;
            left = false;
        }
        if (e.getKeyCode() == e.VK_LEFT) {
            left = true;
            right = false;
        }

        if (e.getKeyCode() == e.VK_SPACE) {
            jumpCounter = 1;
            if (jumpCounter == 1) {
                up = 27;
                jump = true;
            }

        }
        if (e.getKeyCode() == e.VK_ENTER) {
            if (questionNumber == 5) {
                if (SelectedAns.equals("Pinky")) {
                    questionNumber++;

                }
            }
            else if (questionNumber == 6) {
                if (SelectedAns.equals("N.M Desert")) {
                    questionNumber++;
                }
            }
            else if (questionNumber == 7) {
                if (SelectedAns.equals("Nintendo")) {
                    questionNumber++;
                }
            }
            else if (questionNumber == 8) {
                if (SelectedAns.equals("Super Mario Bros.")) {
                    Music.runEra2Theme(0, 0);
                    Music.runEra3Theme(1, 0);
                    questionNumber++;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == e.VK_LEFT) {
            left = false;
        }

    }
    
    /**Moves Mario's y position toward the ground after he jumps.
     * 
     */
    public void gravity() {
        if (marioy < 760) {
            marioy += acceleration;
        }
    }

    /**Moves Mario in the air when jumping.
     * 
     * @param jumping speed at which Mario jumps in the air.
     */
    public void playerJump(double jumping) {
        down = false;
        marioy -= jumping;
        if (jumping == 0) {
            down = true;
            jump = false;
            acceleration = 0;
        }
    }

    /**Controls Mario's movement in the air- if he is jumping or falling.
     * 
     */
    public void jumping() {
        if (jump) {
            up -= 1;
            playerJump(up);
        }

        if (down) {
            if (acceleration < 20) {
                acceleration += 1;
            }
            gravity();
        }
    }

    public void actionPerformed(ActionEvent e) {

    }

}
