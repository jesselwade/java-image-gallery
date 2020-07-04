package edu.au.cc.gallery.data;

import java.util.List;

public interface ImageDAO {


	/**
	 * @return list of user images.
	 */
	List<Image> getImages(User u) throws Exception;

//	/**
//	 * @return a specific image.
//	 */
//	Image getImageByPath(String path) throws Exception;

	
	void addImage(Image i) throws Exception;


}
