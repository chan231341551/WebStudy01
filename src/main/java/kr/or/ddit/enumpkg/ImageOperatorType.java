package kr.or.ddit.enumpkg;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import kr.or.ddit.servlet07.ImageOperator;


public enum ImageOperatorType {
	COPY( (src, dest, copy) -> {try {
		Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
		e.printStackTrace();
	}} ),
	MOVE( (src, dest, copy) -> {try {
		Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
		e.printStackTrace();
	}} );
	
	private ImageOperator imageOperator;
	private ImageOperatorType(ImageOperator imageOperator) {
		this.imageOperator = imageOperator;
	}
	public void fileOperate(Path src, Path dest, CopyOption copy) {
		imageOperator.fileOperate(src, dest, copy);
	}
}

