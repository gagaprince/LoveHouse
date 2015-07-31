package wang.gagalulu.lovehouse.util;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileUtils {
	
	public void rmFile(String path){
		File f = new File(path);
		rmFile(f);
	}
	
	public void rmFile(File file){
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files){
				rmFile(f);
			}
		}
		if(file.exists()){
			file.delete();
		}
	}
	
	
	public static void main(String[] args) {
		FileUtils ft = new FileUtils();
		ft.rmFile("d:/temp/lovehouseIndexs/");
	}
}
