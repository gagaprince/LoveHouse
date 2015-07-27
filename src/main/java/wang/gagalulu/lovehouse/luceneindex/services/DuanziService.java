package wang.gagalulu.lovehouse.luceneindex.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.DuanZiModel;
import wang.gagalulu.lovehouse.dao.DuanziDao;
import wang.gagalulu.lovehouse.luceneindex.config.LuceneConfig;


@Service
public class DuanziService {
	private static final Logger logger =  Logger.getLogger(DuanziService.class);
	
	@Autowired
	private DuanziDao duanziDao;
	@Autowired
	private LuceneConfig luceneConfig; 
	
	public String createDuanziIndex() throws IOException{
		long allCount = duanziDao.getAllCount();
		long begin = 0;
		int length = 10000;
		 /* 这里放索引文件的位置 */    
        File indexDir = new File(luceneConfig.getDuanziIndexPath());    
        Directory dir = FSDirectory.open(indexDir);  
        Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_36);  
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36,luceneAnalyzer);  
        iwc.setOpenMode(OpenMode.CREATE);  
        IndexWriter indexWriter = new IndexWriter(dir,iwc);    
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
            Field fieldId = new Field("id", duanzi.getId(),    
                    Field.Store.YES, Field.Index.NO);    
            Field fieldContent = new Field("content", duanzi.getContent(), Field.Store.YES,    
                    Field.Index.ANALYZED,    
                    Field.TermVector.WITH_POSITIONS_OFFSETS);
            Field fieldDate = new Field("date",duanzi.getDate(),Field.Store.YES, Field.Index.NO);
            document.add(fieldId);    
            document.add(fieldContent);    
            document.add(fieldDate);   
            indexWriter.addDocument(document);    
        }    
	}
	
	public String IWantOneDuanzi(String key) throws CorruptIndexException, IOException, ParseException{
		IndexReader reader = luceneConfig.getIndexReader();
        IndexSearcher searcher = new IndexSearcher(reader);    
          
        ScoreDoc[] hits = null;    
        String queryString = key;   //搜索的关键词  
        String result = "";
        Query query = null;    
          
    
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);    
        QueryParser qp = new QueryParser(Version.LUCENE_36,"content", analyzer);    
        query = qp.parse(queryString);    
        if (searcher != null) {    
        	Sort sort = new Sort();
        	sort.setSort(new SortField("date",SortField.DOC));
            TopDocs results = searcher.search(query,10,sort);    //返回最多为10条记录  
            hits = results.scoreDocs;  
            if (hits.length > 0) {    
            	int index = 0;
                Document hitDoc = searcher.doc(hits[index].doc);
                result = hitDoc.get("content");
                logger.info("找到:" + hits.length + " 个结果!");    
                logger.info("可以找到:" + results.totalHits + " 个结果!");  
                logger.info(hitDoc.get("id")+"-------"+hitDoc.get("content")+"----"+hitDoc.get("date")); 
            }
            searcher.close();  
        }
        return result;
	}
	
}
