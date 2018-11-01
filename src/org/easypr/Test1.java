package org.easypr;

import static org.junit.Assert.*;

import java.util.Vector;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.easypr.core.CharsRecognise;
import org.easypr.core.PlateDetect;
import org.junit.Test;
import org.opencv.core.Core;

public class Test1 {
	
	static PlateDetect plateDetect = null;
	static CharsRecognise cr = null;
	static {
		plateDetect = new PlateDetect();
		plateDetect.setPDLifemode(true);
		cr = new CharsRecognise();
	}
	//单个车牌识别
	private static String plateOCROne(String path) {
		Mat mat = opencv_imgcodecs.imread(path);
		return plateOCRMain(mat);
		
	}
	private static String plateOCRMain(Mat mat) {
		Vector<Mat> vt= new Vector(1);
		if (0 == plateDetect.plateDetect(mat, vt)) {
			if (vt.size() > 0) {
				return cr.charsRecognise(vt.get(0));
			}
		}
		return null;
	}
	@Test
	public void test1() {
		// System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 String imgPath = "res/image/test_image/plate_judge.jpg";
    	 Mat src = opencv_imgcodecs.imread(imgPath);
		String value = plateOCRMain(src);
    	System.out.println("识别结果为："+value);
	}
	@Test
	public void test2() {
		System.out.println("你好");
	}
}
