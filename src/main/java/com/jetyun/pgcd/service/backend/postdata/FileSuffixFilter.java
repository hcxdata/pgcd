package com.jetyun.pgcd.service.backend.postdata;

import java.io.File;
import java.io.FileFilter;

public class FileSuffixFilter implements FileFilter{

	private String suffix;
	
	public FileSuffixFilter(String suffix){
		this.suffix = suffix;
	}
	
	@Override
	public boolean accept(File arg0) {
		return arg0.getName().endsWith(suffix);
	}

}
