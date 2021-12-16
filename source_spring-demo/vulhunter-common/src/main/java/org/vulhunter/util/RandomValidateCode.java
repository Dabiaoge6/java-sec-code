package org.vulhunter.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

public class RandomValidateCode {
  public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
  
  private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private String randNumber = "0123456789";
  
  private int width = 95;
  
  private int height = 25;
  
  private int lineSize = 40;
  
  private int stringNum = 4;
  
  private Random random = new Random();
  
  private Font getFont() {
    return new Font("Fixedsys", 1, 18);
  }
  
  private Color getRandColor(int fc, int bc) {
    if (fc > 255)
      fc = 255; 
    if (bc > 255)
      bc = 255; 
    int r = fc + this.random.nextInt(bc - fc - 16);
    int g = fc + this.random.nextInt(bc - fc - 14);
    int b = fc + this.random.nextInt(bc - fc - 18);
    return new Color(r, g, b);
  }
  
  public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    BufferedImage image = new BufferedImage(this.width, this.height, 4);
    Graphics g = image.getGraphics();
    g.fillRect(0, 0, this.width, this.height);
    g.setFont(new Font("Times New Roman", 0, 18));
    g.setColor(getRandColor(110, 133));
    for (int i = 0; i <= this.lineSize; i++)
      drowLine(g); 
    String randomString = "";
    for (int j = 1; j <= this.stringNum; j++)
      randomString = drowString(g, randomString, j); 
    session.removeAttribute("RANDOMVALIDATECODEKEY");
    session.setAttribute("RANDOMVALIDATECODEKEY", randomString.toLowerCase());
    g.dispose();
    try {
      ImageIO.write(image, "JPEG", (OutputStream)response.getOutputStream());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private String drowString(Graphics g, String randomString, int i) {
    g.setFont(getFont());
    g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random
          .nextInt(121)));
    String rand = String.valueOf(getRandomString(this.random.nextInt(this.randString
            .length())));
    randomString = randomString + rand;
    g.translate(this.random.nextInt(3), this.random.nextInt(3));
    g.drawString(rand, 13 * i, 16);
    return randomString;
  }
  
  private void drowLine(Graphics g) {
    int x = this.random.nextInt(this.width);
    int y = this.random.nextInt(this.height);
    int xl = this.random.nextInt(13);
    int yl = this.random.nextInt(15);
    g.drawLine(x, y, x + xl, y + yl);
  }
  
  public String getRandomString(int num) {
    return String.valueOf(this.randString.charAt(num));
  }
  
	public void getRandNumbercode(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BufferedImage image = new BufferedImage(this.width, this.height, 4);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, this.width, this.height);
		g.setFont(new Font("Times New Roman", 0, 18));
		g.setColor(getRandColor(110, 133));
		for (int i = 0; i <= this.lineSize; i++)
			drowLine(g);
		String randomString = "";
		for (int j = 1; j <= this.stringNum; j++)
			randomString = drowNumberString(g, randomString, j);
		session.removeAttribute("RANDOMNUMBERVALIDATECODEKEY");
		session.setAttribute("RANDOMNUMBERVALIDATECODEKEY", randomString.toLowerCase());
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", (OutputStream) response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String drowNumberString(Graphics g, String randomString, int i) {
		g.setFont(getFont());
		g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random.nextInt(121)));
		String rand = String.valueOf(getRandomString(this.random.nextInt(this.randNumber.length())));
		randomString = randomString + rand;
		g.translate(this.random.nextInt(3), this.random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}
  
}
