package com.chityog.chityogws.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.chityog.chityogws.bean.UserBean;

public class ImageUpload {

	private UserBean user;
	private BufferedImage image = null;
	private byte[] imageByte;

	public ImageUpload(UserBean user) {
		this.user = user;
	}

	public String uploadImage(HttpServletRequest request) {
		String filename = null;
		try {
			imageByte = Base64.getMimeDecoder().decode(user.getProfilePic());
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();

			// write the image to a file
			String phyPath = request.getSession().getServletContext()
					.getRealPath("/");

			filename = user.getUserId() + ".png";
			String path = phyPath + "/resources/" + user.getUserId() + ".png";
			File outputfile = new File(path);
			if (!outputfile.exists()) {
				outputfile.createNewFile();
			}

			ImageIO.write(image, "png", outputfile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filename;

	}
}
