package com.minhaz.serviceImp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.minhaz.entity.Para;
import com.minhaz.entity.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProthomAloServiceImp {

	public List<Post> createPsot() throws IOException {
		List<Post> postList = new ArrayList();
		String publisher = "prothomAlo";
		String newPaperUrl = "https://www.prothomalo.com";
		HashSet<String> postId = findPostIds();
		System.out.println("Number of posts:"+postId.size());

		for (String id : postId) {
			Post post = new Post();
			
			String completeArticleUrl = newPaperUrl + id;
			//Finding post category
//			System.out.println("Category:"+findPostCat(id));
//			System.out.println("Time:"+);
//			System.out.println(completeArticleUrl);
			
			post.setCat(findPostCat(id));
			post.setDateTime(new Date());
			post.setUrl(completeArticleUrl);
			
			Document document = Jsoup.connect(completeArticleUrl).get();
			Element body = document.body();
			

			// Finding header of the post
			Elements heading = body.getElementsByTag("h1");
//			System.out.println("Heading:"+heading.text());
			
			post.setHeading(heading.text());

			// ---------end-------
			
			String postFeatureImgUrl = featureImgUrl(body);
//			System.out.println("Feature Image:"+postFeatureImgUrl);
			post.setFtrImg(postFeatureImgUrl);
			
//			System.out.println("Publisher:"+publisher);
			post.setPublisher(publisher);
			//This portion for article paras
			Elements articleParas = body.getElementsByTag("p");
			articleParas.outerHtml();
			
			post.setPostBody(psotBody(articleParas));
			
//			System.out.println("------------end------------------");
			// break;
			
			postList.add(post);
			
		}
		
		
		return postList;
	}
	
	//This for finding article body.It would be Map type
	public List<Para> psotBody(Elements articleParas) {
		List<Para> allParas = new ArrayList();
		for (int i = 1; i < articleParas.size() - 2; i++) {
			Para para = new Para();
			if(findImgUrl(articleParas.get(i)).equals("")){
				para.setDescription(articleParas.get(i).text());
			}else {
				para.setImgUrl(findImgUrl(articleParas.get(i)));
				para.setImgCaption(findImgCaption(articleParas.get(i)));
				para.setDescription(articleParas.get(i).text());
			}
			
			allParas.add(para);

		}
		return allParas;
	}
	//----------end-----------
	
	
	public String featureImgUrl(Element body) {
		Elements elements = body.getElementsByClass("col_in");
		String imgWithCaption = elements.select("img").first().attr("src");
		return imgWithCaption;
	}

	// method for finding image url
//	private String findImgUrl(Element articleParas) {
//		String imgUrl = null;
//		String caption = null;
//
//		// start and end index for image url
//		int startIndex = articleParas.outerHtml().indexOf("src=") + 5;
//		int endIndex = articleParas.outerHtml().indexOf('>', startIndex) - 1;
//		imgUrl = articleParas.outerHtml().substring(startIndex, endIndex);
//		if (startIndex > 5) {
//			//System.out.println(imgUrl);
//			System.out.println(findImgUrlAndCaption(articleParas));
//		}
//
//		return imgUrl;
//	}
	// ---------------end-----------
	
	//---------------Finding image URL--------
	private String findImgUrl(Element articleParas) {
		HashMap<String, String> imgUrlAndCaptin = new HashMap<String, String>();
		Element imgTag = articleParas.select("img").first();
		if(imgTag != null) {
			return imgTag.attr("src");
		}else {
			return "";
		}
	}
	//----------------end--------------
	
	//---------------Finding image caption--------
		private String findImgCaption(Element articleParas) {
			HashMap<String, String> imgUrlAndCaptin = new HashMap<String, String>();
			Element imgTag = articleParas.select("img").first();
			if(imgTag != null) {
				return imgTag.attr("alt");
			}else {
				return "";
			}
		}
		//----------------end--------------
	
	// method for finding artcile url
	public static HashSet<String> findPostIds() throws IOException {
		HashSet<String> postId = new HashSet<String>();
		Document document = Jsoup.connect("https://www.prothomalo.com/archive").get();
		Element body = document.body();
		
		Element posts = body.getElementById("widget_56292");
		Elements postUrls = posts.getElementsByTag("a");
		for (Element post : postUrls) {
			String link = post.attr("href");
			if (link.length() > 100)
				postId.add(link.substring(0, link.indexOf('%')));
		}

		return postId;
	}
	
	private String findPostCat(String id) {
		
		return id.split("/")[1];
	}

}
