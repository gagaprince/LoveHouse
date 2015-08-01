package wang.gagalulu.lovehouse.util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer5X;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.springframework.stereotype.Service;

@Service
public class LuceneUtil {
	private Map<String,DirectoryReader> readerMap;
	private Map<String,IndexWriter> writerMap;
	private Map<String,IndexSearcher> searcherMap;
	private Analyzer luceneAnalyzer;
	
	@PostConstruct
	public void init(){
		readerMap = new HashMap<String,DirectoryReader>();
		writerMap = new HashMap<String,IndexWriter>();
		searcherMap = new HashMap<String,IndexSearcher>();
	}
	
	public IndexSearcher getSearcher(String searcherName,IndexReader r){
		IndexSearcher searcher = searcherMap.get(searcherName);
		//如果searcher是空的，或者 searcher中的reader已经关闭
		if(searcher == null || searcher.getIndexReader().getRefCount() <= 0){
			searcher = new IndexSearcher(r);
			searcherMap.put(searcherName,searcher);
		}
		return searcher;
	}
	
	public IndexWriter getIndexWriter(String writerName,String path) throws IOException{
		IndexWriter indexWriter = writerMap.get(writerName);
		if(indexWriter==null){
			Directory dir = FSDirectory.open(Paths.get(path));  
	        Analyzer luceneAnalyzer = giveMeAnalyzer();
	        
	        IndexWriterConfig iwc = new IndexWriterConfig(luceneAnalyzer);  
	        iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);  
	        indexWriter = new IndexWriter(dir,iwc);
		}
		return indexWriter;
	}
	
	public Analyzer giveMeAnalyzer(){
		if(luceneAnalyzer == null){
			luceneAnalyzer = new JcsegAnalyzer5X(JcsegTaskConfig.COMPLEX_MODE);
	        JcsegAnalyzer5X jcseg = (JcsegAnalyzer5X) luceneAnalyzer;
	        JcsegTaskConfig jcsegTaskConfig = jcseg.getTaskConfig();  
	        jcsegTaskConfig.setAppendCJKPinyin(true);  
	        jcsegTaskConfig.setAppendCJKSyn(true); 
		}
//      Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_36);  
        
        return luceneAnalyzer;
	}
	
	public DirectoryReader getDirectoryIndexReader(String readerName,String path) throws CorruptIndexException, IOException{
		DirectoryReader reader = readerMap.get(readerName);
		if(reader==null){
			reader = DirectoryReader.open(FSDirectory.open(Paths.get(path)));
		}else{
			DirectoryReader dr = DirectoryReader.openIfChanged(reader);
			if(dr!=null){
				reader.close();
				reader = dr;
			}
		}
		readerMap.put(readerName, reader);
		return reader;
	}
	
	public void destroy(){
		visitMap(writerMap, new WhenVisit() {
			@Override
			public void onVisit(Object o) {
				IndexWriter indexWriter = (IndexWriter)o;
				try {
					indexWriter.commit();
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void visitMap(Map map , WhenVisit whenVisit){
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			String key = it.next();
			Object o = map.get(key);
			whenVisit.onVisit(o);
		}
	}
	
	private interface WhenVisit{
		public void onVisit(Object o);
	}
}
