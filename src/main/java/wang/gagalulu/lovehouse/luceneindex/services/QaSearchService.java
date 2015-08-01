package wang.gagalulu.lovehouse.luceneindex.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;
import wang.gagalulu.lovehouse.dao.QaDao;
import wang.gagalulu.lovehouse.luceneindex.config.LuceneConfig;
import wang.gagalulu.lovehouse.util.FileUtils;
import wang.gagalulu.lovehouse.util.LuceneUtil;

@Service
public class QaSearchService {
	private static final Logger logger =  Logger.getLogger(QaSearchService.class);
	
	@Autowired
	private LuceneConfig luceneConfig;
	@Autowired
	private LuceneUtil luceneUtil;
	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private QaDao qaDao;

	private final String WRITER_NAME = "qaSearchIndexWriter";
	private final String QA_SEARCHER = "qaSearchSearcher";
	private final String READER_NAME = "qaSearchIndexReader";
	
	
	@PostConstruct
	public void createIfNotIndex(){
		if(isExsitIndex()){
			//检查索引和数据库的一致性
		}else{
			//根据数据库建立索引
			//将原来的目录删掉
			fileUtils.rmFile(luceneConfig.getQaIndexPath());
			try {
				createQaIndex();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void createQaIndex() throws IOException{
		long allCount = qaDao.getAllCount();
		long begin = 0;
		int length = 10000;
		/*Directory dir = FSDirectory.open(Paths.get(luceneConfig.getQaIndexPath()));  
        Analyzer luceneAnalyzer = giveMeAnalyzer();
        
        IndexWriterConfig iwc = new IndexWriterConfig(luceneAnalyzer);  
        iwc.setOpenMode(OpenMode.CREATE); */ 
        IndexWriter indexWriter = giveMeWriter();
		while(begin<allCount){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("fromIndex", begin);
			params.put("toIndex", length);
			List<AnswerBean> answerBeanList = qaDao.getAnswerList(params);
			try {
				createIndexByList(answerBeanList,indexWriter);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("begin:"+begin+"建立索引完毕");
			begin+=length;
		}
		indexWriter.commit();
	}
	
	private IndexWriter giveMeWriter() throws IOException{
		return luceneUtil.getIndexWriter(WRITER_NAME, luceneConfig.getQaIndexPath());
	}
	
	public void createIndexByList(List<AnswerBean> answerBeanList,IndexWriter indexWriter) throws CorruptIndexException, IOException{
        //增加document到索引去    
        for (int i = 0; i < answerBeanList.size(); i++) { 
        	AnswerBean answerBean = answerBeanList.get(i);  
        	addOneQaInIndex(answerBean, indexWriter);
        }    
	}
	
	//判断是否存在索引
	private boolean isExsitIndex(){
		boolean exsit = true;
		try {
			luceneConfig.getQaIndexReader();
		} catch (CorruptIndexException e) {
			exsit = false;
			e.printStackTrace();
		} catch (IOException e) {
			exsit = false;
			e.printStackTrace();
		}
		return exsit;
	}
	
	private void addOneQaInIndex(AnswerBean answerBean , IndexWriter indexWriter) throws IOException{
		Document document = new Document();    
        Field fieldId = new LongField("id", answerBean.getId(),Store.YES);
        Field fieldQuestion = new TextField("question", answerBean.getQuestion(),Store.YES);
        Field fieldAnswer = new StringField("answer",answerBean.getAnswer(),Store.YES);
        document.add(fieldId);    
        document.add(fieldQuestion);    
        document.add(fieldAnswer);   
        indexWriter.addDocument(document);
	}
	
	public void addOneQaInIndex(AnswerBean answerBean){
		try {
			IndexWriter indexWriter = giveMeWriter();
			addOneQaInIndex(answerBean, indexWriter);
			indexWriter.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AnswerBean IWantOneAnswer(String key) throws CorruptIndexException, IOException, ParseException{
		DirectoryReader reader = luceneUtil.getDirectoryIndexReader(READER_NAME, luceneConfig.getQaIndexPath());
//        IndexSearcher searcher = new IndexSearcher(reader);    
		IndexSearcher searcher = luceneUtil.getSearcher(QA_SEARCHER,reader);
          
        ScoreDoc[] hits = null;    
        String queryString = key;   //搜索的关键词  
        AnswerBean result = null;
        Query query = null;    
        
        Analyzer luceneAnalyzer = luceneUtil.giveMeAnalyzer();
        
        QueryParser qp = new QueryParser("question", luceneAnalyzer);    
        query = qp.parse(queryString);    
        if (searcher != null) {    
//        	Sort sort = new Sort();
//        	sort.setSort(new SortField("date",SortField.DOC));
            TopDocs results = searcher.search(query,5);    //返回最多为10条记录  
            hits = results.scoreDocs;  
            if (hits.length > 0) {    
//            	int index = new Random().nextInt(hits.length);
            	ScoreDoc selectDoc = hits[0];
            	logger.info("选中的结果评分为："+selectDoc.score);
        		Document hitDoc = searcher.doc(selectDoc.doc);
        		String question = hitDoc.get("question");
                String answer = hitDoc.get("answer");
                Long id = Long.parseLong(hitDoc.get("id"));
                result = new AnswerBean(id,question,answer);
                logger.info(hitDoc.get("id")+"-------"+hitDoc.get("question")+"----"+hitDoc.get("answer")); 
                logger.info("找到:" + hits.length + " 个结果!");    
                logger.info("可以找到:" + results.totalHits + " 个结果!");  
            }
        }
        return result;
	}
}
