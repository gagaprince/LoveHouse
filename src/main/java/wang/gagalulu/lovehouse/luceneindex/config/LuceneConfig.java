package wang.gagalulu.lovehouse.luceneindex.config;

import java.io.IOException;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

@Service
public class LuceneConfig {
	private Configuration config;
	private static final Logger logger =  Logger.getLogger(LuceneConfig.class);
	
	private String duanziIndexPath = null;
	private String qaIndexPath = null;
	private DirectoryReader reader;
	private DirectoryReader qaReader;
	
	@PostConstruct
	private void init() {
		logger.info("index config init begin");
		try {
			config = new PropertiesConfiguration("lucene.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		logger.info("index config init end");
	}
	
	public String get(String key) {
		if (config == null) {
			init();
		}
		return config.getString(key);
	}
	
	public String getDuanziIndexPath(){
		if(duanziIndexPath==null){
			duanziIndexPath = get("duanzi.path");
		}
		return duanziIndexPath;
	}
	
	public String getQaIndexPath(){
		if(qaIndexPath == null){
			qaIndexPath = get("qa.path");
		}
		return qaIndexPath;
	}
	
	public DirectoryReader getQaIndexReader() throws CorruptIndexException, IOException{
		return getQaIndexReader(getQaIndexPath());
	}
	
	public DirectoryReader getQaIndexReader(String path) throws CorruptIndexException, IOException{
		if(qaReader==null){
			qaReader = DirectoryReader.open(FSDirectory.open(Paths.get(path)));
		}else{
			DirectoryReader dr = DirectoryReader.openIfChanged(qaReader);
			if(dr!=null){
				qaReader.close();
				qaReader = dr;
			}
		}
		return qaReader;
	}
	
	public DirectoryReader getIndexReader(String path) throws CorruptIndexException, IOException{
		if(reader==null){
			reader = DirectoryReader.open(FSDirectory.open(Paths.get(path)));
		}else{
			DirectoryReader dr = DirectoryReader.openIfChanged(reader);
			if(dr!=null){
				reader.close();
				reader = dr;
			}
		}
		return reader;
	}
	
	public DirectoryReader getIndexReader() throws CorruptIndexException, IOException{
		return getIndexReader(getDuanziIndexPath());
	}
}
