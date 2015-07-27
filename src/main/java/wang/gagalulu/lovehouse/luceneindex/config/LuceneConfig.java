package wang.gagalulu.lovehouse.luceneindex.config;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

@Service
public class LuceneConfig {
	private Configuration config;
	private static final Logger logger =  Logger.getLogger(LuceneConfig.class);
	
	private String duanziIndexPath = null;
	
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
	
	public IndexReader getIndexReader() throws CorruptIndexException, IOException{
		IndexReader reader = IndexReader.open(FSDirectory.open(new File(getDuanziIndexPath())));
		return reader;
	}
}
