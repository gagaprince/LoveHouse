package wang.gagalulu.lovehouse.luceneindex.services;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer5X;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.DuanZiModel;
import wang.gagalulu.lovehouse.dao.DuanziDao;
import wang.gagalulu.lovehouse.luceneindex.config.LuceneConfig;
import wang.gagalulu.lovehouse.util.LuceneUtil;


@Service
public class DuanziService {
	private static final Logger logger =  Logger.getLogger(DuanziService.class);
	
	@Autowired
	private DuanziDao duanziDao;
	@Autowired
	private LuceneConfig luceneConfig; 
	@Autowired
	private LuceneUtil luceneUtil;
	
	private final String WRITER_NAME = "duanziServiceWriter";
	private final String DUANZI_SEARCHER = "duanziServiceSearcher"; 
	private final String READER_NAME = "duanziServiceReader";
	
	public String createDuanziIndex() throws IOException{
		long allCount = duanziDao.getAllCount();
		long begin = 0;
		int length = 10000;
		 /* 这里放索引文件的位置 */    
//        File indexDir = new File(luceneConfig.getDuanziIndexPath());    
        IndexWriter indexWriter = luceneUtil.getIndexWriter(WRITER_NAME, luceneConfig.getDuanziIndexPath());
		while(begin<allCount){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("fromIndex", begin);
			params.put("toIndex", length);
			List<DuanZiModel> duanziList = duanziDao.getDuanZiList(params);
			try {
				createIndexByList(duanziList,indexWriter);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("begin:"+begin+"建立索引完毕");
			begin+=length;
		}
		indexWriter.close();
		return "建立索引完毕";
	}
	
	public void createIndexByList(List<DuanZiModel> duanziList,IndexWriter indexWriter) throws CorruptIndexException, IOException{
        //增加document到索引去    
        for (int i = 0; i < duanziList.size(); i++) { 
        	DuanZiModel duanzi = duanziList.get(i);  
            Document document = new Document();    
            Field fieldId = new LongField("id", duanzi.getId(),Store.YES);
            Field fieldContent = new TextField("content", duanzi.getContent(),Store.YES);
            Field fieldDate = new StringField("date",duanzi.getDate(),Store.YES);
            document.add(fieldId);    
            document.add(fieldContent);    
            document.add(fieldDate);   
            indexWriter.addDocument(document);    
        }    
	}
	
	public String IWantOneDuanzi(String key) throws CorruptIndexException, IOException, ParseException{
		DirectoryReader reader = luceneUtil.getDirectoryIndexReader(READER_NAME, luceneConfig.getDuanziIndexPath());
        IndexSearcher searcher = luceneUtil.getSearcher(DUANZI_SEARCHER, reader);    
          
        ScoreDoc[] hits = null;    
        String queryString = key;   //搜索的关键词  
        String result = "";
        Query query = null;    
        
        Analyzer luceneAnalyzer = luceneUtil.giveMeAnalyzer();
        
        QueryParser qp = new QueryParser("content", luceneAnalyzer);    
        query = qp.parse(queryString);    
        if (searcher != null) {    
//        	Sort sort = new Sort();
//        	sort.setSort(new SortField("date",SortField.DOC));
            TopDocs results = searcher.search(query,5);    //返回最多为10条记录  
            hits = results.scoreDocs;  
            if (hits.length > 0) {    
            	int index = new Random().nextInt(hits.length);
            	ScoreDoc selectDoc = hits[index];
            	logger.info("选中的结果评分为："+selectDoc.score);
            	if(selectDoc.score>1){
            		Document hitDoc = searcher.doc(selectDoc.doc);
                    result = hitDoc.get("content");
                    logger.info(hitDoc.get("id")+"-------"+hitDoc.get("content")+"----"+hitDoc.get("date")); 
            	}
                logger.info("找到:" + hits.length + " 个结果!");    
                logger.info("可以找到:" + results.totalHits + " 个结果!");  
            }
        }
        return result;
	}
	
	public static void main(String[] args) throws ParseException, IOException {
		DirectoryReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("D:\\temp\\lovehouseIndex")));
        IndexSearcher searcher = new IndexSearcher(reader);    
          
        ScoreDoc[] hits = null;    
        String queryString = "的";   //搜索的关键词  
        Query query = null;    
        
        Analyzer luceneAnalyzer = new JcsegAnalyzer5X(JcsegTaskConfig.COMPLEX_MODE);
        JcsegAnalyzer5X jcseg = (JcsegAnalyzer5X) luceneAnalyzer;
        JcsegTaskConfig jcsegTaskConfig = jcseg.getTaskConfig();  
        logger.info(jcsegTaskConfig.getPropertieFile());
        jcsegTaskConfig.setAppendCJKPinyin(true);  
        jcsegTaskConfig.setAppendCJKSyn(true); 
        
        QueryParser qp = new QueryParser("content", luceneAnalyzer);    
        query = qp.parse(queryString);  
        System.out.println(query);
        if (searcher != null) {    
//        	Sort sort = new Sort();
//        	sort.setSort(new SortField("date",SortField.DOC));
            TopDocs results = searcher.search(query,5);    //返回最多为10条记录  
            hits = results.scoreDocs; 
            System.out.println(results.totalHits);
            System.out.println(hits.length);
            if (hits.length > 0) {    
            	int index = new Random().nextInt(hits.length);
            	
            	ScoreDoc selectDoc = hits[index];
            	System.out.println("选中的结果评分为："+selectDoc.score);
            	if(selectDoc.score>1){
            		Document hitDoc = searcher.doc(selectDoc.doc);
                    System.out.println(hitDoc.get("id")+"-------"+hitDoc.get("content")+"----"+hitDoc.get("date")); 
            	}
                System.out.println("找到:" + hits.length + " 个结果!");    
                System.out.println("可以找到:" + results.totalHits + " 个结果!");  
            }
        }
	}
	
}
