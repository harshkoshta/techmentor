package com.techmentor.more;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class GenerateCertificate {
	public static String generate(String name, String course, String date) throws IOException {
		String path = Paths.get("").toAbsolutePath().toString();
		System.out.println(path);
	File ff = new File("");
	System.out.println("File path Generator" + ff.getAbsolutePath() +"\\src\\main\\resources\\static\\certificates\\sample.png");
		final BufferedImage image = ImageIO.read(new File(ff.getCanonicalPath() +"\\src\\main\\resources\\static\\certificates\\sample.png"));
		Graphics g = image.getGraphics();
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		FontMetrics metrics = g.getFontMetrics(font);
		int name_w = metrics.stringWidth(name);
		int course_w = metrics.stringWidth(course);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(name, (image.getWidth()*68/100)-(name_w/2), 625);
		g.drawString(course, (image.getWidth()*68/100)-(course_w/2), (image.getHeight()*3/4)-225);
		g.drawString(date, ((image.getWidth()*3)/4) +150, (image.getHeight()*3/4) - 105);
		g.dispose();

		File f = new File("src/main/resources/static/certificates/test.png");
		ImageIO.write(image, "png", f);
		System.out.println(f.getAbsolutePath());
		return f.getAbsolutePath();
	}
}
