package data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// spring boot에서 하기 전 연습
public class Naver {
	public static void main(String[] args) throws Exception {
		Document dc = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%A0%84%EC%8B%9C&oquery=%EC%A0%84%EC%8B%9C%ED%9A%8C&tqi=hEZI0wprvOsssCooGc4ssssssJZ-434105").get();
		Elements titles = dc.select("div.area_text_box strong.this_text a");
		
		for(int i=0; i<titles.size(); i++) {
			String title = titles.get(i).text();			// 제목
			String url = titles.get(i).attr("abs:href");	// 세부페이지 주소
			
			// 세부페이지로 이동
			Connection detailConn = Jsoup.connect(url);
			Document detailDoc = detailConn.get();
			
			Elements info = detailDoc.select("div.detail_info dl div.info_group dd");
			String period = "";
			String time = "";
			String location = "";
			
			if(info.size() == 4) {
				period = info.get(1).text();	// 기간
				time = info.get(2).text();		// 시간
				location = info.get(3).text();	// 장소
			}else {
				period = info.get(1).text();	// 기간
				time = null;					// 시간
				location = info.get(2).text();	// 장소
			}
			String start = period.substring(0, 14);
			String end = period.substring(17);
			System.out.println(title + " / " + start + end + " / " + time + " / " + location);
		}
	}
}